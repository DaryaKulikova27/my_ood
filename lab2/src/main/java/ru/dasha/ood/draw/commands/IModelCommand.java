package ru.dasha.ood.draw.commands;

import ru.dasha.ood.draw.ModelController;

public interface IModelCommand {
    Object execute(ModelController controller);
}
