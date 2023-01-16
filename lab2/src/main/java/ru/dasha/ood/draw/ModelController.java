package ru.dasha.ood.draw;

import ru.dasha.ood.draw.nodes.CompositeNode;
import ru.dasha.ood.draw.nodes.GenericNode;
import ru.dasha.ood.draw.nodes.decorators.CircleShapeDecorator;
import ru.dasha.ood.draw.nodes.decorators.PolygonShapeDecorator;
import ru.dasha.ood.draw.nodes.decorators.RectangleShapeDecorator;
import ru.dasha.ood.draw.nodes.visitors.Visitor;
import ru.dasha.ood.draw.serialization.FileType;
import ru.dasha.ood.draw.serialization.open.BinaryFileDeserializationStrategy;
import ru.dasha.ood.draw.serialization.open.IFileDeserializationStrategy;
import ru.dasha.ood.draw.serialization.open.TextFileDeserializationStrategy;
import ru.dasha.ood.draw.serialization.save.BinaryFileSerializationStrategy;
import ru.dasha.ood.draw.serialization.save.IFileSerializationStrategy;
import ru.dasha.ood.draw.serialization.save.TextFileSerializationStrategy;
import ru.dasha.ood.draw.shapes.CanvasShape;
import ru.dasha.ood.draw.shapes.CircleShape;
import ru.dasha.ood.draw.shapes.PolygonShape;
import ru.dasha.ood.draw.shapes.RectangleShape;
import ru.dasha.ood.draw.ui.window.WindowController;

import java.io.File;
import java.io.IOException;
import java.util.*;

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

    public Object saveFile(File file, FileType fileType) {
        try {
            saveMemento(getSerializationStrategy(fileType), file, getCurrentMemento());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public IFileDeserializationStrategy getDeserializationStrategy(FileType type) {
        switch (type) {
            case TEXT:
                return new TextFileDeserializationStrategy();
            case BINARY:
                return new BinaryFileDeserializationStrategy();
        }
        return null;
    }

    public IFileSerializationStrategy getSerializationStrategy(FileType type) {
        switch (type) {
            case TEXT:
                return new TextFileSerializationStrategy();
            case BINARY:
                return new BinaryFileSerializationStrategy();
        }
        return null;
    }

    public NodesMemento loadMemento(IFileDeserializationStrategy strategy, File file) throws IOException {
        Collection<GenericNode> genericNodes = strategy.deserialize(file);
        return NodesMemento.createWithoutCloning(genericNodes);
    }

    public void saveMemento(IFileSerializationStrategy strategy, File file, NodesMemento memento) throws IOException {
        strategy.serialize(List.of(memento.getNodes()), file);
    }

    public Object openFile(File file, FileType fileType) {
        try {
            NodesMemento memento = loadMemento(getDeserializationStrategy(fileType), file);
            restoreState(memento);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void restoreState(NodesMemento memento) {
        nodesUndoMementos.empty();
        nodesRedoMementos.empty();

        nodes.clear();
        nodes.addAll(List.of(memento.getNodes()));
    }
}