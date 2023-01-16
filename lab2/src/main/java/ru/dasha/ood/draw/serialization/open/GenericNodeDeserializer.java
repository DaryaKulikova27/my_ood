package ru.dasha.ood.draw.serialization.open;

import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.paint.Color;
import org.json.JSONArray;
import org.json.JSONObject;
import ru.dasha.ood.draw.nodes.CompositeNode;
import ru.dasha.ood.draw.nodes.GenericNode;
import ru.dasha.ood.draw.nodes.decorators.CircleShapeDecorator;
import ru.dasha.ood.draw.nodes.decorators.PolygonShapeDecorator;
import ru.dasha.ood.draw.nodes.decorators.RectangleShapeDecorator;
import ru.dasha.ood.draw.shapes.CircleShape;
import ru.dasha.ood.draw.shapes.PolygonShape;
import ru.dasha.ood.draw.shapes.RectangleShape;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;

public class GenericNodeDeserializer {
    public static Collection<GenericNode> nodesFromJSON(JSONArray data) {
        ArrayList<GenericNode> nodes = new ArrayList<>();
        for (int i = 0; i < data.length(); i++)
            nodes.add(nodeFromJSON(data.getJSONObject(i)));
        return nodes;
    }

    private static GenericNode nodeFromJSON(JSONObject jsonObject) {
        switch (jsonObject.getString("type")) {
            case CircleShapeDecorator.SERIAL_NAME:
                return circleFromJSON(jsonObject);
            case RectangleShapeDecorator.SERIAL_NAME:
                return rectangleFromJSON(jsonObject);
            case CompositeNode.SERIAL_NAME:
                return compositeFromJSON(jsonObject);
            case PolygonShapeDecorator.SERIAL_NAME:
                return polygonFromJSON(jsonObject);
        }
        return null;
    }

    private static PolygonShapeDecorator polygonFromJSON(JSONObject jsonObject) {
        JSONArray pointsData = jsonObject.getJSONArray("points");
        Point2D[] points = new Point2D[pointsData.length()];
        for (int i = 0; i < pointsData.length(); i++)
            points[i] = point2DFromJSON(pointsData.getJSONArray(i));

        return new PolygonShapeDecorator(new PolygonShape.Builder()
                .withFillColor(Color.web(jsonObject.getString("fillColor")))
                .withStrokeColor(Color.web(jsonObject.getString("strokeColor")))
                .withStrokeThickness(jsonObject.getInt("strokeThickness"))
                .withPoints(points)
                .build());
    }

    private static CompositeNode compositeFromJSON(JSONObject jsonObject) {
        JSONArray children = jsonObject.getJSONArray("children");
        Collection<GenericNode> childrenNodes = nodesFromJSON(children);
        return new CompositeNode(new LinkedHashSet<>(childrenNodes));
    }

    private static RectangleShapeDecorator rectangleFromJSON(JSONObject jsonObject) {
        return new RectangleShapeDecorator(new RectangleShape.Builder()
                .withFillColor(Color.web(jsonObject.getString("fillColor")))
                .withStrokeColor(Color.web(jsonObject.getString("strokeColor")))
                .withStrokeThickness(jsonObject.getInt("strokeThickness"))
                .withRect(rectangle2DFromJSON(jsonObject.getJSONArray("rect")))
                .build());
    }

    private static CircleShapeDecorator circleFromJSON(JSONObject jsonObject) {
        return new CircleShapeDecorator(new CircleShape.Builder()
                .withFillColor(Color.web(jsonObject.getString("fillColor")))
                .withStrokeColor(Color.web(jsonObject.getString("strokeColor")))
                .withStrokeThickness(jsonObject.getInt("strokeThickness"))
                .withRadius(jsonObject.getInt("radius"))
                .withCenter(point2DFromJSON(jsonObject.getJSONArray("center")))
                .build());
    }

    private static Point2D point2DFromJSON(JSONArray data) {
        return new Point2D(data.getDouble(0), data.getDouble(1));
    }

    private static Rectangle2D rectangle2DFromJSON(JSONArray data) {
        return new Rectangle2D(
                data.getDouble(0),
                data.getDouble(1),
                data.getDouble(2),
                data.getDouble(3)
        );
    }
}
