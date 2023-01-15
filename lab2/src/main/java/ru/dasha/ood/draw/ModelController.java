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
import java.util.List;
import java.util.Set;
import java.util.Stack;

public class ModelController {
    private Stack<NodesMemento> nodesUndoMementos = new Stack<>();
    private Stack<NodesMemento> nodesRedoMementos = new Stack<>();
    private Set<GenericNode> nodes = new LinkedHashSet<>();

    public void openWindow() {
        WindowController wc = new WindowController(this);
        wc.open();
    }

    public GenericNode createDecoratorNode(CanvasShape shape) {
        takeMemento();
        GenericNode node;
        if (shape instanceof CircleShape)
            node = new CircleShapeDecorator((CircleShape) shape);
        else if (shape instanceof RectangleShape)
            node = new RectangleShapeDecorator((RectangleShape) shape);
        else if (shape instanceof PolygonShape)
            node = new PolygonShapeDecorator((PolygonShape) shape);
        else
            throw new RuntimeException("Unexpected type of shape");
        nodes.add(node);
        return node;
    }

    public Set<GenericNode> getNodes() {
        return nodes;
    }

    public CompositeNode createCompositeNode(Set<GenericNode> nodes) {
        takeMemento();
        this.nodes.removeAll(nodes);
        CompositeNode compositeNode = new CompositeNode(nodes);
        this.nodes.add(compositeNode);
        return compositeNode;
    }

    public Set<GenericNode> decompositeNode(CompositeNode node) {
        takeMemento();
        nodes.addAll(node.getChildNodes());
        nodes.remove(node);
        return node.getChildNodes();
    }

    public Object runVisitor(Visitor visitor, Set<GenericNode> nodes, boolean takeMemento) {
        if (takeMemento)
            takeMemento();
        for (GenericNode node : nodes)
            node.accept(visitor);
        return null;
    }

    private void takeMemento() {
        nodesRedoMementos.clear();
        nodesUndoMementos.push(getCurrentMemento());
    }

    private NodesMemento getCurrentMemento() {
        return NodesMemento.createUsingClones(nodes);
    }

    public void undoMemento() {
        if (nodesUndoMementos.empty())
            return;
        NodesMemento nodesMemento = nodesUndoMementos.pop();
        nodesRedoMementos.push(getCurrentMemento());
        nodes.clear();
        nodes.addAll(List.of(nodesMemento.getNodes()));
    }

    public void redoMemento() {
        if (nodesRedoMementos.empty())
            return;
        NodesMemento nodesMemento = nodesRedoMementos.pop();
        nodesUndoMementos.push(getCurrentMemento());
        nodes.clear();
        nodes.addAll(List.of(nodesMemento.getNodes()));
    }
}