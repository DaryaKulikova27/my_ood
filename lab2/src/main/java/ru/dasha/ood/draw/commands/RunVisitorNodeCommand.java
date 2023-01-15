package ru.dasha.ood.draw.commands;

import ru.dasha.ood.draw.ModelController;
import ru.dasha.ood.draw.nodes.GenericNode;
import ru.dasha.ood.draw.nodes.visitors.Visitor;

import java.util.Set;

public class RunVisitorNodeCommand implements IModelCommand {
    private final Visitor visitor;
    private final Set<GenericNode> nodes;
    private final boolean takeMemento;

    public RunVisitorNodeCommand(Visitor visitor, Set<GenericNode> nodes, boolean takeMemento) {
        this.visitor = visitor;
        this.nodes = nodes;
        this.takeMemento = takeMemento;
    }

    public RunVisitorNodeCommand(Visitor visitor, Set<GenericNode> nodes) {
        this(visitor, nodes, true);
    }

    @Override
    public Object execute(ModelController controller) {
        return controller.runVisitor(visitor, nodes, takeMemento);
    }
}
