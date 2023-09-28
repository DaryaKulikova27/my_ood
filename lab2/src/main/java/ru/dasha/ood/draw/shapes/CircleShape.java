package ru.dasha.ood.draw.shapes;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class CircleShape implements CanvasShape {
    private Color fillColor;
    private Color strokeColor;
    private int strokeThickness;
    private int radius;
    private Point2D center;

    public CircleShape(Color fillColor, Color strokeColor, int strokeThickness, int radius, Point2D center) {
        this.fillColor = fillColor;
        this.strokeColor = strokeColor;
        this.strokeThickness = strokeThickness;
        this.radius = radius;
        this.center = center;
    }

    @Override
    public void draw(GraphicsContext context) {

        context.setFill(fillColor);
        context.fillOval(center.getX() - radius, center.getY() - radius, radius * 2, radius * 2);

        context.setStroke(strokeColor);
        context.setLineWidth(strokeThickness);
        context.strokeOval(center.getX() - radius, center.getY() - radius, radius * 2, radius * 2);
    }

    public Color getFillColor() {
        return fillColor;
    }

    public void setFillColor(Color fillColor) {
        this.fillColor = fillColor;
    }

    public Color getStrokeColor() {
        return strokeColor;
    }

    public void setStrokeColor(Color strokeColor) {
        this.strokeColor = strokeColor;
    }

    public int getStrokeThickness() {
        return strokeThickness;
    }

    public void setStrokeThickness(int strokeThickness) {
        this.strokeThickness = strokeThickness;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public Point2D getCenter() {
        return center;
    }

    public void setCenter(Point2D center) {
        this.center = center;
    }

    @Override
    public Object cloneIt() {
        return new CircleShape(fillColor, strokeColor, strokeThickness, radius, center);
    }

    public static final class Builder {
        private Color fillColor;
        private Color strokeColor;
        private int strokeThickness;
        private int radius;
        private Point2D center;
        public Builder() {
        }
        public Builder withFillColor(Color fillColor) {
            this.fillColor = fillColor;
            return this;
        }
        public Builder withStrokeColor(Color strokeColor) {
            this.strokeColor = strokeColor;
            return this;
        }
        public Builder withStrokeThickness(int strokeThickness) {
            this.strokeThickness = strokeThickness;
            return this;
        }
        public Builder withRadius(int radius) {
            this.radius = radius;
            return this;
        }
        public Builder withCenter(Point2D center) {
            this.center = center;
            return this;
        }
        public CircleShape build() {
            return new CircleShape(fillColor, strokeColor, strokeThickness, radius, center);
        }
    }
}

//public static Builder aCircleShape() {
//            return new Builder();
//        }