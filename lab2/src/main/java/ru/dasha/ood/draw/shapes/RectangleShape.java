package ru.dasha.ood.draw.shapes;

import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class RectangleShape implements CanvasShape {
    private Color fillColor;
    private Color strokeColor;
    private int strokeThickness;
    private Rectangle2D rect;

    public RectangleShape(Color fillColor, Color strokeColor, int strokeThickness, Point2D startPoint, Point2D size) {
        this.fillColor = fillColor;
        this.strokeColor = strokeColor;
        this.strokeThickness = strokeThickness;
        this.rect = new Rectangle2D(startPoint.getX(), startPoint.getY(), size.getX(), size.getY());
    }

    public RectangleShape(Color fillColor, Color strokeColor, int strokeThickness, Rectangle2D rect) {
        this.fillColor = fillColor;
        this.strokeColor = strokeColor;
        this.strokeThickness = strokeThickness;
        this.rect = rect;
    }

    @Override
    public void draw(GraphicsContext context) {
        context.setFill(fillColor);
        context.fillRect(rect.getMinX(), rect.getMinY(), rect.getWidth(), rect.getHeight());

        context.setStroke(strokeColor);
        context.setLineWidth(strokeThickness);
        context.strokeRect(rect.getMinX(), rect.getMinY(), rect.getWidth(), rect.getHeight());
    }

    public int getStrokeThickness() {
        return strokeThickness;
    }

    public void setStrokeThickness(int strokeThickness) {
        this.strokeThickness = strokeThickness;
    }

    public Rectangle2D getRect() {
        return rect;
    }

    public void setRect(Rectangle2D rect) {
        this.rect = rect;
    }

    public Color getStrokeColor() {
        return strokeColor;
    }

    public void setStrokeColor(Color strokeColor) {
        this.strokeColor = strokeColor;
    }

    public Color getFillColor() {
        return fillColor;
    }

    public void setFillColor(Color fillColor) {
        this.fillColor = fillColor;
    }

    @Override
    public Object cloneIt() {
        return new RectangleShape(fillColor, strokeColor, strokeThickness, rect);
    }
}