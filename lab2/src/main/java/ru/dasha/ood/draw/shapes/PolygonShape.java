package ru.dasha.ood.draw.shapes;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class PolygonShape implements CanvasShape {
    private Color fillColor;
    private Color strokeColor;
    private int strokeThickness;
    private Point2D[] points;

    public PolygonShape(Color fillColor, Color strokeColor, int strokeThickness, Point2D[] points) {
        this.fillColor = fillColor;
        this.strokeColor = strokeColor;
        this.strokeThickness = strokeThickness;
        this.points = points;
    }

    @Override
    public void draw(GraphicsContext context) {
        double[] xPoints = new double[points.length];
        double[] yPoints = new double[points.length];

        for (int i = 0; i < points.length; i++) {
            xPoints[i] = points[i].getX();
            yPoints[i] = points[i].getY();
        }

        context.setFill(fillColor);
        context.fillPolygon(xPoints, yPoints, points.length);

        context.setStroke(strokeColor);
        context.setLineWidth(strokeThickness);
        context.strokePolygon(xPoints, yPoints, points.length);
    }

    public Color getFillColor() {
        return fillColor;
    }

    public void setFillColor(Color fillColor) {
        this.fillColor = fillColor;
    }

    public Color getStrokeColor() {
        return strokeColor;
    }

    public void setStrokeColor(Color strokeColor) {
        this.strokeColor = strokeColor;
    }

    public int getStrokeThickness() {
        return strokeThickness;
    }

    public void setStrokeThickness(int strokeThickness) {
        this.strokeThickness = strokeThickness;
    }

    public Point2D[] getPoints() {
        return points;
    }

    public void setPoints(Point2D[] points) {
        this.points = points;
    }
}