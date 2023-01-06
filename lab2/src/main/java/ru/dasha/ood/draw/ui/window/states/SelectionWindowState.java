package ru.dasha.ood.draw.ui.window.states;

import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import ru.dasha.ood.draw.commands.CreateCompositeNodeCommand;
import ru.dasha.ood.draw.commands.DecompositeNodeCommand;
import ru.dasha.ood.draw.nodes.CompositeNode;
import ru.dasha.ood.draw.nodes.GenericNode;
import ru.dasha.ood.draw.ui.window.IWindowContext;
import ru.dasha.ood.draw.utils.GeometryHelper;

import java.util.HashSet;
import java.util.Set;

public class SelectionWindowState implements WindowState {
    private static final Color SELECTION_COLOR = Color.web("#3574f0");
    private static final Color SELECTION_COLOR_FILL = Color.web("#3574f00f");
    private MouseState state;
    private Point2D pinnedPoint;
    private Point2D lastPoint;

    @Override
    public void activate(IWindowContext context) {

    }

    @Override
    public void onKeyDown(IWindowContext context, KeyEvent event) {
        if (event.getCode() == KeyCode.G && event.isControlDown() && context.getSelectedNodes().size() > 1) {
            HashSet<GenericNode> nodesList = new HashSet<>();
            nodesList.addAll(context.getSelectedNodes());
            GenericNode createdNode = (GenericNode) context.dispatchCommand(new CreateCompositeNodeCommand(nodesList));
            context.clearSelectedNodes();
        } else if (event.getCode() == KeyCode.U && event.isControlDown() && context.getSelectedNodes().size() == 1) {
            for (GenericNode node : context.getSelectedNodes())
                if (node instanceof CompositeNode) {
                    context.dispatchCommand(new DecompositeNodeCommand((CompositeNode) node));
                    context.clearSelectedNodes();
                }
        }
    }

    @Override
    public void onMouseDown(IWindowContext context, MouseEvent e) {
        Point2D currentPoint = new Point2D(e.getX(), e.getY());

        GenericNode pointedNode = findPointedNode(context.getNodes(), currentPoint);
        if (pointedNode != null) {
            if (context.getSelectedNodes().contains(pointedNode)) {
                if (e.isShiftDown())
                    context.removeSelectedNode(pointedNode);
            } else {
                if (e.isShiftDown())
                    context.addSelectedNode(pointedNode);
                else {
                    context.clearSelectedNodes();
                    context.addSelectedNode(pointedNode);
                }
            }
            state = MouseState.TOUCH_DOWN_ON_SELECTION;
        } else {
            context.clearSelectedNodes();
            state = MouseState.TOUCH_DOWN_ON_VOID;
        }
        System.out.println("SELECTION " + context.getSelectedNodes());
        lastPoint = currentPoint;
        pinnedPoint = null;
    }

    private GenericNode findPointedNode(Set<GenericNode> nodes, Point2D point) {
        for (GenericNode node : nodes)
            if (node.getBounds().contains(point))
                return node;
        return null;
    }

    @Override
    public void onMouseDrag(IWindowContext context, MouseEvent e) {
        Point2D currentPoint = new Point2D(e.getX(), e.getY());
        if (state == MouseState.TOUCH_DOWN_ON_SELECTION || state == MouseState.DRAGGING_SELECTION) {
            for (GenericNode node : context.getSelectedNodes())
                node.moveBy(currentPoint.subtract(lastPoint));
            state = MouseState.DRAGGING_SELECTION;
        } else {
            if (pinnedPoint == null)
                pinnedPoint = lastPoint;
        }
        lastPoint = currentPoint;
    }

    @Override
    public void onMouseUp(IWindowContext context, MouseEvent e) {
        Point2D currentPoint = new Point2D(e.getX(), e.getY());
        if (state == MouseState.TOUCH_DOWN_ON_SELECTION) {
            if (!e.isShiftDown()) {
                context.clearSelectedNodes();
                GenericNode pointedNode = findPointedNode(context.getNodes(), currentPoint);
                if (pointedNode != null)
                    context.addSelectedNode(pointedNode);
            }
        } else if (state == MouseState.TOUCH_DOWN_ON_VOID && pinnedPoint != null) {
            Rectangle2D bounds = GeometryHelper.getRectFromPoints(pinnedPoint, lastPoint);
            for (GenericNode node : context.getNodes())
                if (bounds.contains(node.getBounds()))
                    context.addSelectedNode(node);
        }
        state = MouseState.IDLE;
    }

    @Override
    public void drawOverlay(IWindowContext context, GraphicsContext gc) {
        if (state == MouseState.TOUCH_DOWN_ON_VOID && pinnedPoint != null) {
            Rectangle2D bounds = GeometryHelper.getRectFromPoints(pinnedPoint, lastPoint);

            gc.setStroke(SELECTION_COLOR);
            gc.setFill(SELECTION_COLOR_FILL);
            gc.setLineWidth(2);
            gc.setLineDashes();
            gc.fillRect(bounds.getMinX(), bounds.getMinY(), bounds.getWidth(), bounds.getHeight());
            gc.strokeRect(bounds.getMinX(), bounds.getMinY(), bounds.getWidth(), bounds.getHeight());
        } else if (context.getSelectedNodes().size() > 0) {
            Rectangle2D bounds = getSelectionBoundaries(context);

            gc.setStroke(SELECTION_COLOR);
            gc.setLineWidth(2);
            gc.setLineDashes(4);
            gc.strokeRect(bounds.getMinX(), bounds.getMinY(), bounds.getWidth(), bounds.getHeight());
            gc.setLineDashes();
        }
    }

    private Rectangle2D getSelectionBoundaries(IWindowContext context) {
        Rectangle2D bounds = null;
        for (GenericNode child : context.getSelectedNodes())
            if (bounds == null)
                bounds = child.getBounds();
            else
                bounds = GeometryHelper.combineRects(child.getBounds(), bounds);
        return bounds;
    }

    private enum MouseState {
        IDLE,
        TOUCH_DOWN_ON_VOID,
        TOUCH_DOWN_ON_SELECTION,
        DRAGGING_SELECTION,
    }
}
