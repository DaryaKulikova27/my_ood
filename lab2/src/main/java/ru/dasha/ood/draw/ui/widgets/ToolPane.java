package ru.dasha.ood.draw.ui.widgets;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import ru.dasha.ood.draw.ui.IconFactory;
import ru.dasha.ood.draw.ui.window.WindowStateType;

public class ToolPane extends HBox {
    private final UpdateWindowStateCallback windowStateUpdate;

    public ToolPane(UpdateWindowStateCallback windowStateUpdate) {
        this.windowStateUpdate = windowStateUpdate;
        initLayout();
        initStyles();
    }

    private static Button makeToolbarButton(Node svgPath) {
        Button buttonCurrent = new Button("", svgPath);
        buttonCurrent.getStyleClass().add("toolbar-button");
        buttonCurrent.setPadding(new Insets(4));
        buttonCurrent.setPrefSize(32, 32);
        buttonCurrent.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        return buttonCurrent;
    }

    private void initStyles() {
        getStyleClass().add("toolbar");
    }

    private void initLayout() {
        setPadding(new Insets(4));
//        setSpacing(8);

        ButtonGroup tools = new ButtonGroup(
                this::buttonSelected,
                makeToolbarButton(IconFactory.INSTANCE.makeIcon("touch")),
                makeToolbarButton(IconFactory.INSTANCE.makeIcon("circle")),
                makeToolbarButton(IconFactory.INSTANCE.makeIcon("rect")),
                makeToolbarButton(IconFactory.INSTANCE.makeIcon("triangle"))
        );
        tools.setSpacing(8);

        final Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        HBox rightBlock = new HBox();
        ChoiceBox cb = new ChoiceBox(FXCollections.observableArrayList(
                "Red", "Black", "Pink")
        );
        rightBlock.getChildren().add(cb);
        getChildren().addAll(tools, spacer, rightBlock);

    }

    private void buttonSelected(int index) {
        if (windowStateUpdate != null)
            switch (index) {
                case 0:
                    windowStateUpdate.update(WindowStateType.SELECTION);
                    break;
                case 1:
                    windowStateUpdate.update(WindowStateType.ADD_CIRCLE);
                    break;
                case 2:
                    windowStateUpdate.update(WindowStateType.ADD_RECTANGLE);
                    break;
                case 3:
                    windowStateUpdate.update(WindowStateType.ADD_TRIANGLE);
                    break;
            }
    }

    public interface UpdateWindowStateCallback {
        void update(WindowStateType type);
    }
}