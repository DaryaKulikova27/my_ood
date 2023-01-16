package ru.dasha.ood.draw.ui.widgets;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import ru.dasha.ood.draw.serialization.FileType;
import ru.dasha.ood.draw.ui.IconFactory;
import ru.dasha.ood.draw.ui.window.IWindowContext;
import ru.dasha.ood.draw.ui.window.WindowStateType;

public class ToolPane extends HBox {
    private IWindowContext windowContext;

    public ToolPane(IWindowContext windowContext) {
        this.windowContext = windowContext;
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

    private static void createChoiceBox(HBox rightBlock, String name, String[] choices, final UpdateChoiceCallback callback) {
        ChoiceBox cb = new ChoiceBox(FXCollections.observableArrayList(choices));
        cb.setMaxWidth(50);
        cb.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                callback.update((String) newValue);
                cb.getSelectionModel().clearSelection();
            }
        });
        Label label = new Label(name);
        label.getStyleClass().add("label-normal");
        rightBlock.getChildren().add(label);
        rightBlock.getChildren().add(cb);
    }

    private static Button makeTextButton(String name, ButtonClickCallback callback) {
        Button button = new Button(name);
        button.setPadding(new Insets(4));
        button.setOnAction(e -> callback.onClick());
        return button;
    }

    private static Region makeSpacer() {
        final Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        return spacer;
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

        HBox centerBlock = new HBox();
        centerBlock.setAlignment(Pos.CENTER);

        centerBlock.getChildren().addAll(
                makeTextButton("Open binary", this::openBinaryFile),
                makeTextButton("Open text", this::openTextFile),
                makeTextButton("Save binary", this::saveBinaryFile),
                makeTextButton("Save text", this::saveTextFile)
        );


        HBox rightBlock = new HBox();
        rightBlock.setAlignment(Pos.CENTER);

        createChoiceBox(rightBlock, "   Fill color:  ", new String[]{"Red", "Green", "Cyan", "Black", "White"}, this::selectNewFillColor);
        createChoiceBox(rightBlock, "   Border color:  ", new String[]{"Red", "Green", "Cyan", "Black", "White"}, this::selectNewBorderColor);
        createChoiceBox(rightBlock, "   Border width:  ", new String[]{"1", "2", "3", "4", "5"}, this::selectNewBorderWidth);

        getChildren().addAll(tools, makeSpacer(), centerBlock, makeSpacer(), rightBlock);
    }

    private void openBinaryFile() {
        windowContext.openFile(FileType.BINARY);
    }

    private void openTextFile() {
        windowContext.openFile(FileType.TEXT);
    }

    private void saveBinaryFile() {
        windowContext.saveFile(FileType.BINARY);
    }

    private void saveTextFile() {
        windowContext.saveFile(FileType.TEXT);
    }

    private void selectNewFillColor(String newColor) {
        Color color = Color.web(newColor);

        windowContext.updateNodesFillColor(color);
    }

    private void selectNewBorderColor(String newColor) {
        Color color = Color.web(newColor);

        windowContext.updateNodesBorderColor(color);
    }

    private void selectNewBorderWidth(String newBorderWidth) {
        int width = Integer.parseInt(newBorderWidth);

        windowContext.updateNodesBorderWidth(width);
    }

    private void buttonSelected(int index) {
        if (windowContext != null)
            switch (index) {
                case 0:
                    windowContext.updateStateFromType(WindowStateType.SELECTION);
                    break;
                case 1:
                    windowContext.updateStateFromType(WindowStateType.ADD_CIRCLE);
                    break;
                case 2:
                    windowContext.updateStateFromType(WindowStateType.ADD_RECTANGLE);
                    break;
                case 3:
                    windowContext.updateStateFromType(WindowStateType.ADD_TRIANGLE);
                    break;
            }
    }

    public interface UpdateWindowStateCallback {
        void update(WindowStateType type);
    }

    public interface UpdateChoiceCallback {
        void update(String type);
    }

    public interface ButtonClickCallback {
        void onClick();
    }
}