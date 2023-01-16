package ru.dasha.ood.draw.serialization.save;

import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import org.json.JSONArray;
import org.json.JSONObject;
import ru.dasha.ood.draw.nodes.CompositeNode;
import ru.dasha.ood.draw.nodes.GenericNode;
import ru.dasha.ood.draw.nodes.decorators.CircleShapeDecorator;
import ru.dasha.ood.draw.nodes.decorators.PolygonShapeDecorator;
import ru.dasha.ood.draw.nodes.decorators.RectangleShapeDecorator;

import java.util.Collection;

public class GenericNodeSerializer {
    public static JSONArray toJSON(Collection<GenericNode> nodes) {
        JSONArray array = new JSONArray();
        for (GenericNode node : nodes)
            array.put(toJSON(node));
        return array;
    }

    public static JSONObject toJSON(GenericNode node) {
        if (node instanceof CompositeNode)
            return toJSON((CompositeNode) node);
        else if (node instanceof CircleShapeDecorator)
            return toJSON((CircleShapeDecorator) node);
        else if (node instanceof RectangleShapeDecorator)
            return toJSON((RectangleShapeDecorator) node);
        else if (node instanceof PolygonShapeDecorator)
            return toJSON((PolygonShapeDecorator) node);
        else
            throw new RuntimeException("Type not found");
    }

    private static JSONObject toJSON(PolygonShapeDecorator node) {
        JSONObject json = new JSONObject();
        json.put("type", PolygonShapeDecorator.SERIAL_NAME);
        json.put("fillColor", node.getFillColor().toString());
        json.put("strokeColor", node.getStrokeColor().toString());
        json.put("strokeThickness", node.getStrokeThickness());
        JSONArray points = new JSONArray();
        for (Point2D point : node.getPoints())
            points.put(toJSON(point));
        json.put("points", points);
        return json;
    }

    private static JSONObject toJSON(RectangleShapeDecorator node) {
        JSONObject json = new JSONObject();
        json.put("type", RectangleShapeDecorator.SERIAL_NAME);
        json.put("fillColor", node.getFillColor().toString());
        json.put("strokeColor", node.getStrokeColor().toString());
        json.put("strokeThickness", node.getStrokeThickness());
        json.put("rect", toJSON(node.getRect()));
        return json;
    }

    private static JSONObject toJSON(CircleShapeDecorator node) {
        JSONObject json = new JSONObject();
        json.put("type", CircleShapeDecorator.SERIAL_NAME);
        json.put("fillColor", node.getFillColor().toString());
        json.put("strokeColor", node.getStrokeColor().toString());
        json.put("strokeThickness", node.getStrokeThickness());
        json.put("center", toJSON(node.getCenter()));
        json.put("radius", node.getRadius());
        return json;
    }

    public static JSONObject toJSON(CompositeNode node) {
        JSONObject json = new JSONObject();
        json.put("type", CompositeNode.SERIAL_NAME);
        json.put("children", toJSON(node.getChildNodes()));
        return json;
    }

    private static JSONArray toJSON(Rectangle2D rect) {
        JSONArray dimensions = new JSONArray();
        dimensions.put(rect.getMinX());
        dimensions.put(rect.getMinY());
        dimensions.put(rect.getWidth());
        dimensions.put(rect.getHeight());
        return dimensions;
    }

    private static JSONArray toJSON(Point2D point) {
        JSONArray dimensions = new JSONArray();
        dimensions.put(point.getX());
        dimensions.put(point.getY());
        return dimensions;
    }
}
