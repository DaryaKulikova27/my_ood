package ru.dasha.ood.draw.nodes.visitors;

import javafx.geometry.Point2D;
import ru.dasha.ood.draw.nodes.CompositeNode;
import ru.dasha.ood.draw.nodes.GenericNode;
import ru.dasha.ood.draw.nodes.decorators.ShapeDecorator;

public class MoveVisitor implements Visitor {
    public Point2D point;

    public MoveVisitor(Point2D point) {
        this.point = point;
    }

    @Override
    public void visitCompositeNode(CompositeNode node) {
        for (GenericNode nodes : node.getChildNodes())
            nodes.accept(this);
    }

    @Override
    public void visitShapeDecorator(ShapeDecorator node) {
        node.moveBy(point);
    }
}
