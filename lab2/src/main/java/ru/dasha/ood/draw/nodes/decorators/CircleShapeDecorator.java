package ru.dasha.ood.draw.nodes.decorators;

import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import ru.dasha.ood.draw.shapes.CircleShape;

public class CircleShapeDecorator extends ShapeDecorator<CircleShape> {
    public CircleShapeDecorator(CircleShape wrapped) {
        super(wrapped);
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