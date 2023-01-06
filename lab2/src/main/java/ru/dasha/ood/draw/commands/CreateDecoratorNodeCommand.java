package ru.dasha.ood.draw.commands;

import ru.dasha.ood.draw.ModelController;
import ru.dasha.ood.draw.shapes.CanvasShape;

public class CreateDecoratorNodeCommand implements IModelCommand {
    private final CanvasShape shape;

    public CreateDecoratorNodeCommand(CanvasShape shape) {
        this.shape = shape;
    }

    @Override
    public Object execute(ModelController controller) {
        return controller.createDecoratorNode(shape);
    }
}
