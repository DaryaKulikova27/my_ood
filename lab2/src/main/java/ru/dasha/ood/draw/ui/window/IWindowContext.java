package ru.dasha.ood.draw.ui.window;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import ru.dasha.ood.draw.commands.IModelCommand;
import ru.dasha.ood.draw.nodes.GenericNode;
import ru.dasha.ood.draw.serialization.FileType;
import ru.dasha.ood.draw.ui.window.states.WindowState;

import java.io.File;
import java.util.Set;

public interface IWindowContext {
    Set<GenericNode> getNodes();
    Set<GenericNode> getSelectedNodes();
    void clearSelectedNodes();
    void addSelectedNode(GenericNode node);
    void removeSelectedNode(GenericNode node);
    void setCurrentState(WindowState newState);
    void updateNodesMoveBy(Point2D diff, boolean takeMemento);
    Object dispatchCommand(IModelCommand command);
    void updateStateFromType(WindowStateType type);
    void updateNodesFillColor(Color background);
    void updateNodesBorderColor(Color background);
    void updateNodesBorderWidth(int background);
    void openFile(FileType type);
    void saveFile(FileType type);
}
