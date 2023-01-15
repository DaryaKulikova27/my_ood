package ru.dasha.ood.draw.nodes.visitors;

import ru.dasha.ood.draw.nodes.CompositeNode;
import ru.dasha.ood.draw.nodes.decorators.ShapeDecorator;

public interface Visitor {
    void visitCompositeNode(CompositeNode node);
    void visitShapeDecorator(ShapeDecorator node);
}
