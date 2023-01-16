package ru.dasha.ood.draw.ui.window.states;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import ru.dasha.ood.draw.ui.window.IWindowContext;

public interface WindowState {
    void onKeyDown(IWindowContext context, KeyEvent event);
    void onMouseDown(IWindowContext context, MouseEvent event);
    void onMouseDrag(IWindowContext context, MouseEvent event);
    void onMouseUp(IWindowContext context, MouseEvent event);
    void drawOverlay(IWindowContext context, GraphicsContext gc);
}
