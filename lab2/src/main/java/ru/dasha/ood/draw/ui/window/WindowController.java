package ru.dasha.ood.draw.ui.window;

import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import jfxtras.styles.jmetro.JMetro;
import jfxtras.styles.jmetro.JMetroStyleClass;
import jfxtras.styles.jmetro.Style;
import ru.dasha.ood.draw.Application;
import ru.dasha.ood.draw.ModelController;
import ru.dasha.ood.draw.commands.*;
import ru.dasha.ood.draw.nodes.GenericNode;
import ru.dasha.ood.draw.nodes.visitors.BorderColorVisitor;
import ru.dasha.ood.draw.nodes.visitors.BorderWidthVisitor;
import ru.dasha.ood.draw.nodes.visitors.FillColorVisitor;
import ru.dasha.ood.draw.nodes.visitors.MoveVisitor;
import ru.dasha.ood.draw.serialization.FileType;
import ru.dasha.ood.draw.ui.widgets.ToolPane;
import ru.dasha.ood.draw.ui.window.states.*;

import java.io.File;
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
        if (e.getCode() == KeyCode.Z && e.isShortcutDown() && e.isShiftDown())
            dispatchCommand(new RedoCommand());
        else if (e.getCode() == KeyCode.Z && e.isShortcutDown())
            dispatchCommand(new UndoCommand());
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
        border.setTop(new ToolPane(this));
        CanvasPane pane = new CanvasPane(0, 0);
        canvas = pane.getCanvas();
        border.setCenter(pane);
        return border;
    }

    void render(long timestamp) {
//        System.out.println(canvas.getWidth() + " " + canvas.getHeight());
        gc.setFill(Color.WHITE);

        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        for (GenericNode node : model.getNodes())
            node.draw(gc);

        currentState.drawOverlay(this, gc);
    }

    @Override
    public Set<GenericNode> getNodes() {
        return model.getNodes();
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
    }

    public void updateStateFromType(WindowStateType type) {
        switch (type) {
            case SELECTION:
                setCurrentState(new SelectionWindowState());
                break;
            case ADD_CIRCLE:
                setCurrentState(new CreateCircleWindowState());
                break;
            case ADD_RECTANGLE:
                setCurrentState(new CreateRectWindowState());
                break;
            case ADD_TRIANGLE:
                setCurrentState(new CreateTriangleWindowState());
                break;
        }
    }

    @Override
    public void updateNodesFillColor(Color background) {
        FillColorVisitor visitor = new FillColorVisitor(background);

        dispatchCommand(new RunVisitorNodeCommand(visitor, selectedNodes));
    }

    @Override
    public void updateNodesBorderColor(Color background) {
        BorderColorVisitor visitor = new BorderColorVisitor(background);

        dispatchCommand(new RunVisitorNodeCommand(visitor, selectedNodes));
    }

    @Override
    public void updateNodesBorderWidth(int width) {
        BorderWidthVisitor visitor = new BorderWidthVisitor(width);

        dispatchCommand(new RunVisitorNodeCommand(visitor, selectedNodes));
    }

    @Override
    public void openFile(FileType type) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Import file...");
        fileChooser.setSelectedExtensionFilter(getExtensionFilter(type));
        File selected = fileChooser.showOpenDialog(stage);
        dispatchCommand(new OpenFileCommand(selected, type));
    }

    @Override
    public void saveFile(FileType type) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Export file...");
        fileChooser.setSelectedExtensionFilter(getExtensionFilter(type));
        File selected = fileChooser.showSaveDialog(stage);
        dispatchCommand(new SaveFileCommand(selected, type));
    }

    private static FileChooser.ExtensionFilter getExtensionFilter(FileType type) {
        return type == FileType.TEXT ?
                new FileChooser.ExtensionFilter("Text format", "txt") :
                new FileChooser.ExtensionFilter("Binary format", "bin");
    }

    @Override
    public void updateNodesMoveBy(Point2D diff, boolean takeMemento) {
        MoveVisitor visitor = new MoveVisitor(diff);

        dispatchCommand(new RunVisitorNodeCommand(visitor, selectedNodes, takeMemento));
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