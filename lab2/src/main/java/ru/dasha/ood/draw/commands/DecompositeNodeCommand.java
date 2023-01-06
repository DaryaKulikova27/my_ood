package ru.dasha.ood.draw.commands;

import ru.dasha.ood.draw.ModelController;
import ru.dasha.ood.draw.nodes.CompositeNode;

public class DecompositeNodeCommand implements IModelCommand {
    private final CompositeNode node;

    public DecompositeNodeCommand(CompositeNode node) {
        this.node = node;
    }

    @Override
    public Object execute(ModelController controller) {
        return controller.decompositeNode(node);
    }
}
