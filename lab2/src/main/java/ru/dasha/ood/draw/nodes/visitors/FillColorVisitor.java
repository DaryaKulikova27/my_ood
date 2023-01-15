package ru.dasha.ood.draw.nodes.visitors;

import javafx.scene.paint.Color;
import ru.dasha.ood.draw.nodes.CompositeNode;
import ru.dasha.ood.draw.nodes.GenericNode;
import ru.dasha.ood.draw.nodes.decorators.ShapeDecorator;

public class FillColorVisitor implements Visitor {
    public Color newColor;

    public FillColorVisitor(Color newColor) {
        this.newColor = newColor;
    }

    @Override
    public void visitCompositeNode(CompositeNode node) {
        for (GenericNode nodes : node.getChildNodes())
            nodes.accept(this);
    }

    @Override
    public void visitShapeDecorator(ShapeDecorator node) {
        node.setFillColor(newColor);
    }
}
