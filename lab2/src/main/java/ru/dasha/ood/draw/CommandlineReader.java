package ru.dasha.ood.draw;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import ru.dasha.ood.draw.commands.CommandDispatcher;
import ru.dasha.ood.draw.commands.CreateDecoratorNodeCommand;
import ru.dasha.ood.draw.commands.OpenWindowCommand;
import ru.dasha.ood.draw.shapes.CircleShape;
import ru.dasha.ood.draw.shapes.PolygonShape;
import ru.dasha.ood.draw.shapes.RectangleShape;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.function.Function;

public class CommandlineReader {
    private final InputStream in;
    private final CommandDispatcher dispatcher;

    private final Map<String, Function<String[], Boolean>> commandsMap = Map.of(
            "DRAW", this::cmdDraw,
            "RECTANGLE", this::cmdCreateRect,
            "RECT", this::cmdCreateRect,
            "TRIANGLE", this::cmdCreateTriangle,
            "TRI", this::cmdCreateTriangle,
            "CIRCLE", this::cmdCreateCircle,
            "HELP", this::cmdHelp,
            "PRINT_SHAPES_INFO", this::cmdDraw
    );

    public CommandlineReader(InputStream in, CommandDispatcher dispatcher) {
        this.in = in;
        this.dispatcher = dispatcher;
    }

    public void readCommands() throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(in))) {
            String line;
            System.out.print("Enter command below:\n> ");
            while (null != (line = br.readLine())) {
                String[] args = line.split(" ");
                String command = args[0].toUpperCase();
                if (!commandsMap.containsKey(command) || !commandsMap.get(command).apply(args)) {
                    System.out.println("Invalid command.");
                }
                System.out.print("> ");
            }
        }
    }

    public boolean cmdDraw(String[] args) {
        dispatcher.dispatchCommand(new OpenWindowCommand());
        return true;
    }

    public boolean cmdCreateCircle(String[] args) {
        if (args.length != 6)
            return false;
        int i = 1;
        Point2D center = new Point2D(Integer.parseInt(args[i++]), Integer.parseInt(args[i++]));
        int radius = Integer.parseInt(args[i++]);
        javafx.scene.paint.Color strokeColor = getColor(args[i++]);
        javafx.scene.paint.Color fillColor = getColor(args[i++]);
        int strokeThickness = 2;
        CircleShape shape = new CircleShape(fillColor, strokeColor, strokeThickness, radius, center);
        dispatcher.dispatchCommand(new CreateDecoratorNodeCommand(shape));
        return true;
    }

    public boolean cmdCreateRect(String[] args) {
        if (args.length != 7)
            return false;
        int i = 1;
        Point2D leftTop = new Point2D(Integer.parseInt(args[i++]), Integer.parseInt(args[i++]));
        Point2D rightBottom = new Point2D(Integer.parseInt(args[i++]), Integer.parseInt(args[i++]));
        javafx.scene.paint.Color strokeColor = getColor(args[i++]);
        javafx.scene.paint.Color fillColor = getColor(args[i++]);
        int strokeThickness = 2;
        RectangleShape shape = new RectangleShape(fillColor, strokeColor, strokeThickness, leftTop, rightBottom.subtract(leftTop));
        dispatcher.dispatchCommand(new CreateDecoratorNodeCommand(shape));
        return true;
    }

    public boolean cmdCreateTriangle(String[] args) {
        if (args.length != 9)
            return false;
        int i = 1;
        Point2D[] points = new Point2D[3];
        points[0] = new Point2D(Integer.parseInt(args[i++]), Integer.parseInt(args[i++]));
        points[1] = new Point2D(Integer.parseInt(args[i++]), Integer.parseInt(args[i++]));
        points[2] = new Point2D(Integer.parseInt(args[i++]), Integer.parseInt(args[i++]));
        javafx.scene.paint.Color strokeColor = getColor(args[i++]);
        javafx.scene.paint.Color fillColor = getColor(args[i++]);
        int strokeThickness = 2;
        PolygonShape shape = new PolygonShape(fillColor, strokeColor, strokeThickness, points);
        dispatcher.dispatchCommand(new CreateDecoratorNodeCommand(shape));
        return true;
    }

    private Color getColor(String from) {
        try {
            return Color.valueOf(from);
        } catch (Exception e) {
            int color = Integer.parseInt(from, 16);
            int b = color & 0xFF;
            color >>= 8;
            int g = color & 0xFF;
            color >>= 8;
            int r = color & 0xFF;
            return Color.rgb(r, g, b);
        }
    }

    public boolean cmdHelp(String[] args) {
        System.out.println("If you want to add rectangle, write \"RECTANGLE <left top (x, y)> <right bottom (x, y)> <outline color> <fill color>\"");
        System.out.println("If you want to add triangle, write \"TRIANGLE <x1> <y1> <x2> <y2> <x3> <y3> <outline color> <fill color>\"");
        System.out.println("If you want to add circle, write \"CIRCLE <x> <y> <radius> <outline color> <fill color>\"");
//        System.out.println("If you want to get info about shapes, write \"PrintInfoShapes\"");
        System.out.println("If you want to know this info, write \"Info\"");
        System.out.println("If you want to draw shapes, write \"Draw\"");
        return true;
    }

    public boolean cmdPrintShapesInfo(String[] args) {
//        modelController.ge
        return true;
    }
}