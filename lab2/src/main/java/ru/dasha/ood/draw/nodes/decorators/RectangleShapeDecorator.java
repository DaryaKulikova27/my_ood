package ru.dasha.ood.draw.nodes.decorators;

import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.paint.Color;
import ru.dasha.ood.draw.shapes.RectangleShape;

public class RectangleShapeDecorator extends ShapeDecorator<RectangleShape> {
    public static final String SERIAL_NAME = "rectangle";

    public RectangleShapeDecorator(RectangleShape wrapped) {
        super(wrapped);
    }

    public Color getStrokeColor() {
        return wrapped.getStrokeColor();
    }

    public Color getFillColor() {
        return wrapped.getFillColor();
    }

    @Override
    public void setFillColor(Color color) {
        wrapped.setFillColor(color);
    }

    public int getStrokeThickness() {
        return wrapped.getStrokeThickness();
    }

    public Rectangle2D getRect() {
        return wrapped.getRect();
    }

    @Override
    public void setBorderColor(Color color) {
        wrapped.setStrokeColor(color);
    }

    @Override
    public void setBorderWidth(int color) {
        wrapped.setStrokeThickness(color);
    }

    @Override
    public Rectangle2D getBounds() {
        return wrapped.getRect();
    }

    @Override
    public void moveBy(Point2D vector) {
        Rectangle2D rect = wrapped.getRect();
        wrapped.setRect(new Rectangle2D(rect.getMinX() + vector.getX(), rect.getMinY() + vector.getY(), rect.getWidth(), rect.getHeight()));
    }

    @Override
    public Object cloneIt() {
        return new RectangleShapeDecorator((RectangleShape) wrapped.cloneIt());
    }
}