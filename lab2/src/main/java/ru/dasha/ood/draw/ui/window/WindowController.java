package ru.dasha.ood.draw.ui.window;

import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import jfxtras.styles.jmetro.JMetro;
import jfxtras.styles.jmetro.JMetroStyleClass;
import jfxtras.styles.jmetro.Style;
import ru.dasha.ood.draw.Application;
import ru.dasha.ood.draw.ModelController;
import ru.dasha.ood.draw.commands.IModelCommand;
import ru.dasha.ood.draw.nodes.GenericNode;
import ru.dasha.ood.draw.ui.widgets.ToolPane;
import ru.dasha.ood.draw.ui.window.states.SelectionWindowState;
import ru.dasha.ood.draw.ui.window.states.WindowState;

import java.util.HashSet;
import java.util.Set;

public class WindowController implements IWindowContext {
    private Stage stage;
    private Canvas canvas;
    private GraphicsContext gc;
    private AnimationTimer timer;
    private ModelController model;
    private Set<GenericNode> selectedNodes = new HashSet<>();
    private WindowState currentState = new SelectionWindowState();

    public WindowController(ModelController model) {
        this.model = model;
    }

    public void open() {
        Platform.startup(() -> {
            try {
                stage = new Stage();

                BorderPane border = createBorderPane();

                Scene scene = createScene(border);

                scene.setOnKeyPressed(this::onKeyPressed);

                stage.setScene(scene);

                stage.setTitle("Editor");
                stage.setWidth(1024);
                stage.setHeight(820);
                stage.setOnCloseRequest(evt -> stop());
                stage.show();

                gc = canvas.getGraphicsContext2D();
                canvas.setOnMousePressed(this::onCanvasMouseDown);
                canvas.setOnMouseDragged(this::onCanvasMouseDragged);
                canvas.setOnMouseReleased(this::onCanvasMouseUp);
                canvas.setOnMouseDragExited(this::onCanvasMouseUp);
                canvas.setOnMouseExited(this::onCanvasMouseUp);
                startRenderCycles();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private void stop() {
        if (timer != null)
            timer.stop();
    }

    private void onKeyPressed(KeyEvent e) {
        currentState.onKeyDown(this, e);
    }

    private void onCanvasMouseDown(MouseEvent e) {
        currentState.onMouseDown(this, e);
    }

    private void onCanvasMouseDragged(MouseEvent e) {
        currentState.onMouseDrag(this, e);
    }

    private void onCanvasMouseUp(MouseEvent e) {
        currentState.onMouseUp(this, e);
    }

    private void startRenderCycles() {
        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                render(now);
            }
        };
        timer.start();
    }

    private Scene createScene(BorderPane border) {
        Scene scene = new Scene(border);

        JMetro jMetro = new JMetro(Style.DARK);
        jMetro.setScene(scene);
        jMetro.getOverridingStylesheets().add(getClass().getResource("app_ui.css").toExternalForm());
        return scene;
    }

    private BorderPane createBorderPane() {
        BorderPane border = new BorderPane();

        border.getStyleClass().add(JMetroStyleClass.BACKGROUND);
        border.setTop(new ToolPane());
        CanvasPane pane = new CanvasPane(0, 0);
        canvas = pane.getCanvas();
        border.setCenter(pane);
        return border;
    }

    void render(long timestamp) {
//        System.out.println(canvas.getWidth() + " " + canvas.getHeight());
        gc.setFill(Color.WHITE);

        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        for (GenericNode node : model.getShapes())
            node.draw(gc);

        currentState.drawOverlay(this, gc);
    }

    @Override
    public Set<GenericNode> getNodes() {
        return model.getShapes();
    }

    @Override
    public Set<GenericNode> getSelectedNodes() {
        return selectedNodes;
    }

    @Override
    public void clearSelectedNodes() {
        selectedNodes.clear();
    }

    @Override
    public void addSelectedNode(GenericNode node) {
        selectedNodes.add(node);
    }

    @Override
    public void removeSelectedNode(GenericNode node) {
        selectedNodes.remove(node);
    }

    @Override
    public void setCurrentState(WindowState newState) {
        currentState = newState;
        currentState.activate(this);
    }

    @Override
    public Object dispatchCommand(IModelCommand command) {
        return Application.INSTANCE.dispatchCommand(command);
    }


    private static class CanvasPane extends Pane {

        private final Canvas canvas;

        public CanvasPane(double width, double height) {
            canvas = new Canvas(width, height);
            getChildren().add(canvas);
        }

        public Canvas getCanvas() {
            return canvas;
        }

        @Override
        protected void layoutChildren() {
            super.layoutChildren();
            final double x = snappedLeftInset();
            final double y = snappedTopInset();
            final double w = snapSizeX(getWidth()) - x - snappedRightInset();
            final double h = snapSizeY(getHeight()) - y - snappedBottomInset();
            canvas.setLayoutX(x);
            canvas.setLayoutY(y);
            canvas.setWidth(w);
            canvas.setHeight(h);
        }
    }
}