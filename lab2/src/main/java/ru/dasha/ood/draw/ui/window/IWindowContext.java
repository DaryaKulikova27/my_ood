package ru.dasha.ood.draw.ui.window;

import ru.dasha.ood.draw.commands.IModelCommand;
import ru.dasha.ood.draw.nodes.GenericNode;
import ru.dasha.ood.draw.ui.window.states.WindowState;

import java.util.Set;

public interface IWindowContext {
    Set<GenericNode> getNodes();
    Set<GenericNode> getSelectedNodes();
    void clearSelectedNodes();
    void addSelectedNode(GenericNode node);
    void removeSelectedNode(GenericNode node);
    void setCurrentState(WindowState newState);
    Object dispatchCommand(IModelCommand command);
}
