package ru.dasha.ood.draw.ui.window.states;

import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import ru.dasha.ood.draw.commands.CreateDecoratorNodeCommand;
import ru.dasha.ood.draw.shapes.CanvasShape;
import ru.dasha.ood.draw.shapes.CircleShape;
import ru.dasha.ood.draw.shapes.RectangleShape;
import ru.dasha.ood.draw.ui.window.IWindowContext;
import ru.dasha.ood.draw.utils.GeometryHelper;

public class CreateRectWindowState implements WindowState {
    private static final Color fillColor = Color.color(Color.BLUEVIOLET.getRed(), Color.BLUEVIOLET.getGreen(), Color.BLUEVIOLET.getBlue(), 0.3);
    private static final Color strokeColor = Color.BLUEVIOLET;
    private static final int strokeThickness = 2;
    private Point2D pinnedPoint, lastPoint;

    @Override
    public void activate(IWindowContext context) {

    }

    @Override
    public void onKeyDown(IWindowContext context, KeyEvent event) {

    }

    @Override
    public void onMouseDown(IWindowContext context, MouseEvent e) {
        Point2D currentPoint = new Point2D(e.getX(), e.getY());

        lastPoint = pinnedPoint = currentPoint;
    }

    @Override
    public void onMouseDrag(IWindowContext context, MouseEvent e) {
        Point2D currentPoint = new Point2D(e.getX(), e.getY());

        lastPoint = currentPoint;
    }

    @Override
    public void onMouseUp(IWindowContext context, MouseEvent e) {
        if (pinnedPoint != null && lastPoint != null) {
            Point2D currentPoint = new Point2D(e.getX(), e.getY());
            lastPoint = currentPoint;

            context.dispatchCommand(new CreateDecoratorNodeCommand(createShape()));
        }
        lastPoint = pinnedPoint = null;
    }

    private CanvasShape createShape()
    {
        Rectangle2D bounds = GeometryHelper.getRectFromPoints(pinnedPoint, lastPoint);
        return new RectangleShape(Color.YELLOW, Color.BLACK, 2, new Point2D(bounds.getMinX(), bounds.getMinY()), new Point2D(bounds.getWidth(), bounds.getHeight()));
    }

    @Override
    public void drawOverlay(IWindowContext context, GraphicsContext gc) {
        if (pinnedPoint == null || lastPoint == null)
            return;
        int radius = (int) lastPoint.distance(pinnedPoint);
        if (radius == 0)
            return;
        Rectangle2D bounds = GeometryHelper.getRectFromPoints(pinnedPoint, lastPoint);

        gc.setStroke(strokeColor);
        gc.setFill(fillColor);
        gc.setLineWidth(2);
        gc.setLineDashes(4);
        gc.fillRect(bounds.getMinX(), bounds.getMinY(), bounds.getWidth(), bounds.getHeight());
        gc.strokeRect(bounds.getMinX(), bounds.getMinY(), bounds.getWidth(), bounds.getHeight());
        gc.setLineDashes();
    }
}
