package ru.dasha.ood.draw;

import ru.dasha.ood.draw.nodes.CompositeNode;
import ru.dasha.ood.draw.nodes.GenericNode;
import ru.dasha.ood.draw.nodes.decorators.CircleShapeDecorator;
import ru.dasha.ood.draw.nodes.decorators.PolygonShapeDecorator;
import ru.dasha.ood.draw.nodes.decorators.RectangleShapeDecorator;
import ru.dasha.ood.draw.nodes.visitors.Visitor;
import ru.dasha.ood.draw.shapes.CanvasShape;
import ru.dasha.ood.draw.shapes.CircleShape;
import ru.dasha.ood.draw.shapes.PolygonShape;
import ru.dasha.ood.draw.shapes.RectangleShape;
import ru.dasha.ood.draw.ui.window.WindowController;

import java.util.LinkedHashSet;
import java.util.Set;

public class ModelController {
    private Set<GenericNode> shapes = new LinkedHashSet<>();

    public void openWindow() {
        WindowController wc = new WindowController(this);
        wc.open();
    }

    public GenericNode createDecoratorNode(CanvasShape shape) {
        GenericNode node;
        if (shape instanceof CircleShape)
            node = new CircleShapeDecorator((CircleShape) shape);
        else if (shape instanceof RectangleShape)
            node = new RectangleShapeDecorator((RectangleShape) shape);
        else if (shape instanceof PolygonShape)
            node = new PolygonShapeDecorator((PolygonShape) shape);
        else
            throw new RuntimeException("Unexpected type of shape");
        shapes.add(node);
        return node;
    }

    public Set<GenericNode> getShapes() {
        return shapes;
    }

    public CompositeNode createCompositeNode(Set<GenericNode> nodes) {
        this.shapes.removeAll(nodes);
        CompositeNode compositeNode = new CompositeNode(nodes);
        shapes.add(compositeNode);
        return compositeNode;
    }

    public Set<GenericNode> decompositeNode(CompositeNode node) {
        shapes.addAll(node.getChildNodes());
        shapes.remove(node);
        return node.getChildNodes();
    }

    public Object runVisitor(Visitor visitor, Set<GenericNode> nodes) {
        for (GenericNode node : nodes)
            node.accept(visitor);
        return null;
    }
}