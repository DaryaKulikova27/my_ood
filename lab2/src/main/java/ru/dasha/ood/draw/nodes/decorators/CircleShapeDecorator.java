package ru.dasha.ood.draw.nodes.decorators;

import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.paint.Color;
import ru.dasha.ood.draw.shapes.CircleShape;

public class CircleShapeDecorator extends ShapeDecorator<CircleShape> {
    public CircleShapeDecorator(CircleShape wrapped) {
        super(wrapped);
    }

    @Override
    public void setFillColor(Color color) {
        wrapped.setFillColor(color);
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
        Point2D center = wrapped.getCenter();
        return new Rectangle2D(center.getX() - wrapped.getRadius(), center.getY() - wrapped.getRadius(), wrapped.getRadius() * 2, wrapped.getRadius() * 2);
    }

    @Override
    public void moveBy(Point2D vector) {
        wrapped.setCenter(wrapped.getCenter().add(vector));
    }
}