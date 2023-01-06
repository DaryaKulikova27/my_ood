package ru.dasha.ood.draw.commands;

import ru.dasha.ood.draw.ModelController;

public class OpenWindowCommand implements IModelCommand {
    @Override
    public Object execute(ModelController controller) {
        controller.openWindow();
        return null;
    }
}
