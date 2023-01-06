package ru.dasha.ood.draw;


import ru.dasha.ood.draw.commands.IModelCommand;

public class Application {
    public static final Application INSTANCE = new Application();
    ModelController modelController = new ModelController();
    CommandlineReader commandlineReader = new CommandlineReader(System.in, this::dispatchCommand);
    private Application() {
    }

    public void launch() {
        try {
            commandlineReader.cmdHelp(null);
            commandlineReader.readCommands();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Object dispatchCommand(IModelCommand command) {
        return command.execute(modelController);
    }
}