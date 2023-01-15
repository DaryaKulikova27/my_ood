package ru.dasha.ood.draw.commands;

import ru.dasha.ood.draw.ModelController;

public class RedoCommand implements IModelCommand {
    @Override
    public Object execute(ModelController controller) {
        controller.redoMemento();
        return null;
    }
}
