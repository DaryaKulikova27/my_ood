package ru.dasha.ood.draw.commands;

import ru.dasha.ood.draw.ModelController;
import ru.dasha.ood.draw.serialization.FileType;

import java.io.File;

public class OpenFileCommand implements IModelCommand {
    private final File file;
    private final FileType fileType;

    public OpenFileCommand(File file, FileType fileType) {
        this.file = file;
        this.fileType = fileType;
    }

    @Override
    public Object execute(ModelController controller) {
        return controller.openFile(file, fileType);
    }
}
