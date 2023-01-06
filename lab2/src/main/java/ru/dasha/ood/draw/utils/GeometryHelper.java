package ru.dasha.ood.draw.utils;

import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;

public class GeometryHelper {
    public static Rectangle2D combineRects(Rectangle2D rect1, Rectangle2D rect2) {
        double minX = Math.min(rect1.getMinX(), rect2.getMinX());
        double minY = Math.min(rect1.getMinY(), rect2.getMinY());
        double maxX = Math.max(rect1.getMaxX(), rect2.getMaxX());
        double maxY = Math.max(rect1.getMaxY(), rect2.getMaxY());

        return new Rectangle2D(minX, minY, maxX - minX, maxY - minY);
    }

    public static Rectangle2D getRectFromPoints(Point2D... points) {
        Point2D pt = points[0];

        double minX = pt.getX();
        double minY = pt.getY();
        double maxX = pt.getX();
        double maxY = pt.getY();

        for (Point2D point : points) {
            double x = point.getX();
            double y = point.getY();
            minX = Math.min(x, minX);
            minY = Math.min(y, minY);
            maxX = Math.max(x, maxX);
            maxY = Math.max(y, maxY);
        }

        return new Rectangle2D(minX, minY, maxX - minX, maxY - minY);
    }
}
