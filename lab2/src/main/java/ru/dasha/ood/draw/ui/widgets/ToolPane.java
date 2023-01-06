package ru.dasha.ood.draw.ui.widgets;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.layout.HBox;
import ru.dasha.ood.draw.ui.IconFactory;

public class ToolPane extends HBox {

    public ToolPane() {
        initLayout();
        initStyles();
    }

    private void initStyles() {
        getStyleClass().add("toolbar");
    }

    private void initLayout() {
        setPadding(new Insets(4));
        setSpacing(8);

        ButtonGroup tools = new ButtonGroup(
                makeToolbarButton(IconFactory.INSTANCE.makeIcon("touch")),
                makeToolbarButton(IconFactory.INSTANCE.makeIcon("circle")),
                makeToolbarButton(IconFactory.INSTANCE.makeIcon("rect")),
                makeToolbarButton(IconFactory.INSTANCE.makeIcon("triangle"))
        );
        tools.setSpacing(8);

//        SVGPath rect = IconFactory.INSTANCE.makeIcon("rect");
//        buttonRectangle = makeToolbarButton(rect);
//        buttonRectangle.setOnAction(e -> {
//            System.out.println("buttonRectangle " + " " + rect.getBoundsInParent() + " " + buttonRectangle.getBoundsInLocal());
////            rect.setBackground(null);
////            rect.getShape().setFill(Color.WHITE);
//        });
//        buttonCircle = makeToolbarButton(IconFactory.INSTANCE.makeIcon("circle"));
////        buttonCircle = makeToolbarButton(makeSVGPath(SVGPaths.ICON_CIRCLE));

        getChildren().addAll(tools);

    }

    private void updateButtonStates() {

    }

    private static Button makeToolbarButton(Node svgPath) {
        Button buttonCurrent = new Button("", svgPath);
        buttonCurrent.getStyleClass().add("toolbar-button");
        buttonCurrent.setPadding(new Insets(4));
        buttonCurrent.setPrefSize(32, 32);
        buttonCurrent.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        return buttonCurrent;
    }
}