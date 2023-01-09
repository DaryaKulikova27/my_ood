package ru.dasha.ood.draw.ui.widgets;


import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

import java.util.List;

public class ButtonGroup extends HBox {
    public interface Callback {
        void selectionChanged(int index);
    }

    private final List<Button> buttons;
    private int selected = 0;
    private final Callback callback;

    /**
     * Creates an HBox layout with spacing = 0.
     */
    public ButtonGroup(Callback callback, Button... buttons) {
        this.callback = callback;
        this.buttons = List.of(buttons);
        subscribeClicks();
        initLayout();
        updateSelection();
    }

    private void subscribeClicks() {
        for (int i = 0; i < buttons.size(); i++) {
            Button button = buttons.get(i);
            int buttonIndex = i;
            button.setOnAction(e -> buttonClicked(buttonIndex));
        }
    }

    private void buttonClicked(int index)
    {
        if (selected == index)
            return;
        selected = index;
        if (callback != null)
            callback.selectionChanged(index);
        updateSelection();
    }

    private void initLayout() {
        getChildren().addAll(buttons);
    }

    private void updateSelection()
    {
        for (int i = 0; i < buttons.size(); i++) {
            Button button = buttons.get(i);
            if (i == selected)
                button.getStyleClass().add("active");
            else
                button.getStyleClass().remove("active");
        }
    }
}