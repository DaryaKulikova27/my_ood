package ru.dasha.ood.draw.ui.widgets;


import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

import java.util.List;

public class ButtonGroup extends HBox {
    private List<Button> buttons;
    private int selected = 0;

    /**
     * Creates an HBox layout with spacing = 0.
     */
    public ButtonGroup(Button... buttons) {
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
        selected = index;
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