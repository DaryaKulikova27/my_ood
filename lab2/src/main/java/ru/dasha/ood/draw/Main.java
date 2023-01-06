package ru.dasha.ood.draw;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import ru.dasha.ood.draw.commands.CreateDecoratorNodeCommand;
import ru.dasha.ood.draw.shapes.RectangleShape;

public class Main {
    public static void main(String[] args) {
        createRect(new Point2D(10,10), new Point2D(100,100), Color.BLACK, Color.AZURE);
        createRect(new Point2D(110,110), new Point2D(200,200), Color.BLACK, Color.AQUA);

        Application.INSTANCE.launch();
    }

    private static void createRect(Point2D leftTop, Point2D rightBottom, Color strokeColor, Color fillColor) {
        int strokeThickness = 2;
        RectangleShape shape = new RectangleShape(fillColor, strokeColor, strokeThickness, leftTop, rightBottom.subtract(leftTop));
        Application.INSTANCE.dispatchCommand(new CreateDecoratorNodeCommand(shape));
    }
}