package ru.dasha.ood.draw.nodes.decorators;

import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import ru.dasha.ood.draw.shapes.RectangleShape;

public class RectangleShapeDecorator extends ShapeDecorator<RectangleShape> {
    public RectangleShapeDecorator(RectangleShape wrapped) {
        super(wrapped);
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
}