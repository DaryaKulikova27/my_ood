package ru.dasha.ood.draw.nodes.decorators;

import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.paint.Color;
import ru.dasha.ood.draw.shapes.CircleShape;
import ru.dasha.ood.draw.shapes.PolygonShape;
import ru.dasha.ood.draw.utils.GeometryHelper;

public class PolygonShapeDecorator extends ShapeDecorator<PolygonShape> {
    public PolygonShapeDecorator(PolygonShape wrapped) {
        super(wrapped);
    }

    @Override
    public Rectangle2D getBounds() {
        return GeometryHelper.getRectFromPoints(wrapped.getPoints());
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
    public void moveBy(Point2D vector) {
        Point2D[] points = wrapped.getPoints();
        for (int i = 0; i < points.length; i++)
            points[i] = points[i].add(vector);
    }

    @Override
    public Object cloneIt() {
        return new PolygonShapeDecorator((PolygonShape) wrapped.cloneIt());
    }
}