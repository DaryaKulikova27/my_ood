package ru.dasha.ood.draw.shapes;

import javafx.scene.canvas.GraphicsContext;
import ru.dasha.ood.draw.Clonable;

public interface CanvasShape extends Clonable {
    void draw(GraphicsContext context);
}
