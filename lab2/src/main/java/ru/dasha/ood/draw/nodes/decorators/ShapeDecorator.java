package ru.dasha.ood.draw.nodes.decorators;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import ru.dasha.ood.draw.nodes.GenericNode;
import ru.dasha.ood.draw.nodes.visitors.Visitor;
import ru.dasha.ood.draw.shapes.CanvasShape;

public abstract class ShapeDecorator<T extends CanvasShape> extends GenericNode implements CanvasShape {
    protected final T wrapped;

    public ShapeDecorator(T wrapped)
    {
        this.wrapped = wrapped;
    }

    @Override
    public void draw(GraphicsContext context) {
        wrapped.draw(context);
    }

    public abstract void setFillColor(Color color);
    public abstract void setBorderColor(Color color);
    public abstract void setBorderWidth(int color);

    @Override
    public void accept(Visitor visitor) {
        visitor.visitShapeDecorator(this);
    }
}
