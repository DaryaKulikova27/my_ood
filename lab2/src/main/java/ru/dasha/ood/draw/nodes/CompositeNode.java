package ru.dasha.ood.draw.nodes;

import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import ru.dasha.ood.draw.nodes.visitors.Visitor;
import ru.dasha.ood.draw.utils.GeometryHelper;

import java.util.LinkedHashSet;
import java.util.Set;

public class CompositeNode extends GenericNode {
    Set<GenericNode> childNodes;

    public CompositeNode(Set<GenericNode> childNodes) {
        assert childNodes.size() > 0;
        this.childNodes = childNodes;
    }

    @Override
    public void draw(GraphicsContext context) {
        for (GenericNode child : childNodes)
            child.draw(context);
    }

    @Override
    public Rectangle2D getBounds() {
        Rectangle2D bounds = null;
        for (GenericNode child : childNodes)
            if (bounds == null)
                bounds = child.getBounds();
            else
                bounds = GeometryHelper.combineRects(child.getBounds(), bounds);
        return bounds;
    }

    @Override
    public void moveBy(Point2D vector) {
        for (GenericNode child : childNodes)
            child.moveBy(vector);
    }

    public Set<GenericNode> getChildNodes() {
        return childNodes;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visitCompositeNode(this);
    }

    @Override
    public Object cloneIt() {
        LinkedHashSet<GenericNode> newNodes = new LinkedHashSet<>(childNodes.size());
        for (GenericNode child : childNodes)
            newNodes.add((GenericNode) child.cloneIt());
        return new CompositeNode(newNodes);
    }
}
