package ru.dasha.ood.draw.commands;

public interface CommandDispatcher {
    Object dispatchCommand(IModelCommand command);
}
