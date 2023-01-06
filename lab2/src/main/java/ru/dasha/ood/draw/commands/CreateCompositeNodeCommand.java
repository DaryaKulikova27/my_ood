package ru.dasha.ood.draw.commands;

import ru.dasha.ood.draw.ModelController;
import ru.dasha.ood.draw.nodes.GenericNode;

import java.util.Set;

public class CreateCompositeNodeCommand implements IModelCommand {
    private final Set<GenericNode> nodes;

    public CreateCompositeNodeCommand(Set<GenericNode> nodes) {
        this.nodes = nodes;
    }

    @Override
    public Object execute(ModelController controller) {
        return controller.createCompositeNode(nodes);
    }
}
