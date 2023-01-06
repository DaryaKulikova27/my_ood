package ru.dasha.ood.draw.nodes;

import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;

public abstract class GenericNode {
    public abstract void draw(GraphicsContext context);
    public abstract Rectangle2D getBounds();
    public abstract void moveBy(Point2D vector);
}
