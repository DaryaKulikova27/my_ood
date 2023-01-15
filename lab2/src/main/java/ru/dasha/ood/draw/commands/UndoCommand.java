package ru.dasha.ood.draw.commands;

import ru.dasha.ood.draw.ModelController;
import ru.dasha.ood.draw.nodes.GenericNode;

import java.util.Set;

public class UndoCommand implements IModelCommand {
    @Override
    public Object execute(ModelController controller) {
        controller.undoMemento();
        return null;
    }
}
