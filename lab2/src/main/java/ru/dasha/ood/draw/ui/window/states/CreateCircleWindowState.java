package ru.dasha.ood.draw.ui.window.states;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import ru.dasha.ood.draw.commands.CreateDecoratorNodeCommand;
import ru.dasha.ood.draw.shapes.CanvasShape;
import ru.dasha.ood.draw.shapes.CircleShape;
import ru.dasha.ood.draw.ui.window.IWindowContext;

public class CreateCircleWindowState implements WindowState {
    private static final Color fillColor = Color.color(Color.BLUEVIOLET.getRed(), Color.BLUEVIOLET.getGreen(), Color.BLUEVIOLET.getBlue(), 0.3);
    private static final Color strokeColor = Color.BLUEVIOLET;
    private static final int strokeThickness = 2;
    private Point2D pinnedPoint, lastPoint;

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
        return new CircleShape(Color.YELLOW, Color.BLACK, 2, (int) lastPoint.distance(pinnedPoint), pinnedPoint);
    }

    @Override
    public void drawOverlay(IWindowContext context, GraphicsContext gc) {
        if (pinnedPoint == null || lastPoint == null)
            return;
        int radius = (int) lastPoint.distance(pinnedPoint);
        if (radius == 0)
            return;
        Point2D center = pinnedPoint;
        gc.setFill(fillColor);
        gc.fillOval(center.getX() - radius, center.getY() - radius, radius * 2, radius * 2);

        gc.setLineDashes(4);
        gc.setStroke(strokeColor);
        gc.setLineWidth(strokeThickness);
        gc.strokeOval(center.getX() - radius, center.getY() - radius, radius * 2, radius * 2);
        gc.setLineDashes();
    }
}
