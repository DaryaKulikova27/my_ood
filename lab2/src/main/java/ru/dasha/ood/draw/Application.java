package ru.dasha.ood.draw;


import ru.dasha.ood.draw.commands.IModelCommand;

import java.io.FileInputStream;

public class Application {
    public static final Application INSTANCE = new Application();
    ModelController modelController = new ModelController();
    CommandlineReader commandlineReader;
    private Application() {
    }

    public void launch() {
        try {
            commandlineReader = new CommandlineReader(new FileInputStream("input.txt"), this::dispatchCommand);
            //commandlineReader.cmdHelp(null);
            commandlineReader.readCommands();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Object dispatchCommand(IModelCommand command) {
        return command.execute(modelController);
    }
}