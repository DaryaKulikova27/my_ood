package ru.dasha.ood.draw.commands;

import ru.dasha.ood.draw.ModelController;
import ru.dasha.ood.draw.nodes.GenericNode;
import ru.dasha.ood.draw.serialization.FileType;

import java.io.File;
import java.util.Set;

public class SaveFileCommand implements IModelCommand {
    private final File file;
    private final FileType fileType;

    public SaveFileCommand(File file, FileType fileType) {
        this.file = file;
        this.fileType = fileType;
    }

    @Override
    public Object execute(ModelController controller) {
        return controller.saveFile(file, fileType);
    }
}
