package ru.dasha.ood.draw.nodes.visitors;

import javafx.scene.paint.Color;
import ru.dasha.ood.draw.nodes.CompositeNode;
import ru.dasha.ood.draw.nodes.GenericNode;
import ru.dasha.ood.draw.nodes.decorators.ShapeDecorator;

public class BorderWidthVisitor implements Visitor {
    public int width;

    public BorderWidthVisitor(int width) {
        this.width = width;
    }

    @Override
    public void visitCompositeNode(CompositeNode node) {
        for (GenericNode nodes : node.getChildNodes())
            nodes.accept(this);
    }

    @Override
    public void visitShapeDecorator(ShapeDecorator node) {
        node.setBorderWidth(width);
    }
}
