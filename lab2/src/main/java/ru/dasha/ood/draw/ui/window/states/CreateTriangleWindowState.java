package ru.dasha.ood.draw.ui.window.states;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import ru.dasha.ood.draw.commands.CreateDecoratorNodeCommand;
import ru.dasha.ood.draw.shapes.CanvasShape;
import ru.dasha.ood.draw.shapes.PolygonShape;
import ru.dasha.ood.draw.ui.window.IWindowContext;

public class CreateTriangleWindowState implements WindowState {
    private static final Color strokeColor = Color.color(Color.BLUEVIOLET.getRed(), Color.BLUEVIOLET.getGreen(), Color.BLUEVIOLET.getBlue(), 0.3);
    private static final Color fillColor = Color.BLUEVIOLET;
    private static final int strokeThickness = 2;
    private Point2D points[] = new Point2D[3];
    private int index = 0;
    private boolean mouseWasDown = false;

    private void drawPoint(GraphicsContext gc, Point2D center, boolean dragging) {
        int radius = dragging ? 30 : 20;
        gc.setFill(strokeColor);
        gc.fillOval(center.getX() - radius, center.getY() - radius, radius * 2, radius * 2);
        radius = 3;
        gc.setFill(fillColor);
        gc.fillOval(center.getX() - radius, center.getY() - radius, radius * 2, radius * 2);
    }

    @Override
    public void onKeyDown(IWindowContext context, KeyEvent event) {

    }

    @Override
    public void onMouseDown(IWindowContext context, MouseEvent e) {
        Point2D currentPoint = new Point2D(e.getX(), e.getY());

        points[index] = currentPoint;
        mouseWasDown = true;
    }

    @Override
    public void onMouseDrag(IWindowContext context, MouseEvent e) {
        Point2D currentPoint = new Point2D(e.getX(), e.getY());

        points[index] = currentPoint;
    }

    @Override
    public void onMouseUp(IWindowContext context, MouseEvent e) {
        if (!mouseWasDown)
            return;
        mouseWasDown = false;
        Point2D currentPoint = new Point2D(e.getX(), e.getY());

        points[index] = currentPoint;
        index++;
        if (index > 2) {
            context.dispatchCommand(new CreateDecoratorNodeCommand(createShape()));
            points = new Point2D[3];
            index = 0;
        }
    }

    private CanvasShape createShape() {
        return new PolygonShape(Color.YELLOW, Color.BLACK, 2, points);
    }

    @Override
    public void drawOverlay(IWindowContext context, GraphicsContext gc) {
        for (int i = 0; i <= index; i++) {
            Point2D point = points[i];
            if (point != null)
                drawPoint(gc, point, mouseWasDown && i == index);
        }
    }
}
