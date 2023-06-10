package org.drawtree;

import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;
import org.decompression.Node;

import java.util.Vector;
public class PrintTree extends Application {
    int heightRow = 300;
    final int circleSize = 100;
    int WIDTH = 1216;
    int HEIGHT = 800;
    final double MIN_SCALE = 0.1;
    final double MAX_SCALE = 20.0;
    final GridPane root;
    final Pane netRoot;

    private final DoubleProperty scaleProperty = new SimpleDoubleProperty(1.0);
    private double lastX, lastY;
    final Node tree;
    final Vector<ArrayXY> signs = new Vector<>();
    final int allNodes;
    int allLevels;

// klasa-typ punkt do węzłów
    private class ArrayXY {
        private final int level;
        private final char sign;
        public ArrayXY(int level, char sign) {
            this.level = level;
            this.sign = sign;
        }
        public int getLevel() {
            return level;
        }
        public char getSign() {
            return sign;
        }
    }

    public PrintTree(Node tree) {
        this.root = new GridPane();
        this.netRoot = new Pane();
        this.tree = tree;
        this.writeNodeAndLevel(this.tree, 0, 0);
        this.allNodes = signs.size();
        this.printNodeAndLevel();
        this.checkIfChildren(this.tree);
    }
    private int writeNodeAndLevel(Node root, int indexX, int level) {
        if(root != null) {
            indexX = writeNodeAndLevel(root.left, indexX, level + 1);
            if(level > allLevels) {
                allLevels = level;
            }
            ArrayXY arrayXY = new ArrayXY(level, (char) root.sign);
            signs.add(arrayXY);
            root.number = indexX++;
            indexX = writeNodeAndLevel(root.right, indexX, level + 1);
        }
        return indexX;
    }
    private void printNodeAndLevel() {
        for(int i=0; i<signs.size();i++) {
            System.out.println("node: "+ i + " level: " + signs.get(i).getLevel() + " sign: " + signs.get(i).getSign());
        }
        System.out.println("allNodes: " + allNodes + " allLevels: " + allLevels);
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("Huffdecomp");
        prepareGrid();

        Pane sliderMenu = new Pane();
        sliderMenu.setStyle("-fx-background-color: transparent");
        Slider sliderHeightRow = new Slider(200, 1000, heightRow);
        sliderHeightRow.valueProperty().addListener((observable, oldValue, newValue) -> {
            heightRow = newValue.intValue();
            prepareGrid();
            this.netRoot.getChildren().clear();
            checkIfChildren(this.tree);
        });

        sliderHeightRow.setPrefWidth(400);
        sliderHeightRow.setLayoutX((WIDTH - 400)/2);
        sliderHeightRow.setLayoutY(HEIGHT - 50);
        sliderHeightRow.getStyleClass().add("slider");
        sliderMenu.getChildren().add(sliderHeightRow);
        stage.setMinWidth(sliderHeightRow.getPrefWidth() + 100);
        stage.setMinHeight(110);
        Group g2 = new Group();
        g2.getChildren().add(netRoot);
        g2.getChildren().add(root);

        Group g = new Group();
        g.getChildren().add(netRoot);
        g.getChildren().add(root);
        g.getChildren().add(sliderMenu);
        Scene scene = new Scene(g, WIDTH, HEIGHT);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());

        scene.widthProperty().addListener((obs, oldWidth, newWidth) -> {
            sliderMenu.getChildren().clear();
            WIDTH = newWidth.intValue();
            System.out.println(WIDTH + ";" + HEIGHT);
            sliderHeightRow.setLayoutX((WIDTH - 400)/2);
            sliderHeightRow.setLayoutY(HEIGHT - 50);
            System.out.println(sliderHeightRow.getLayoutX() + "-" + sliderHeightRow.getLayoutY());
            sliderMenu.getChildren().add(sliderHeightRow);
        });
        scene.heightProperty().addListener((obs, oldHeight, newHeight) -> {
            sliderMenu.getChildren().clear();
            HEIGHT = newHeight.intValue();
            System.out.println(WIDTH + ";" + HEIGHT);
            sliderHeightRow.setLayoutX((WIDTH - 400)/2);
            sliderHeightRow.setLayoutY(HEIGHT - 50);
            System.out.println(sliderHeightRow.getLayoutX() + "-" + sliderHeightRow.getLayoutY());
            sliderMenu.getChildren().add(sliderHeightRow);
        });

        scene.setOnMousePressed(event -> {
            lastX = event.getSceneX();
            lastY = event.getSceneY();
        });

        scene.setOnMouseDragged(event -> {
            double deltaX = event.getSceneX() - lastX;
            double deltaY = event.getSceneY() - lastY;

            netRoot.setTranslateX(netRoot.getTranslateX() + deltaX);
            root.setTranslateX(root.getTranslateX() + deltaX);
            netRoot.setTranslateY(netRoot.getTranslateY() + deltaY);
            root.setTranslateY(root.getTranslateY() + deltaY);

            lastX = event.getSceneX();
            lastY = event.getSceneY();
        });

        scene.setOnScroll((ScrollEvent event) -> {
            double deltaY = event.getDeltaY();
            double scaleFactor = deltaY > 0 ? 1.1 : 0.9;

            double newScale = scaleProperty.get() * scaleFactor;
            if (newScale < MIN_SCALE || newScale > MAX_SCALE) {
                return;
            }

            scaleProperty.set(newScale);

            Scale scale = new Scale(scaleProperty.get(), scaleProperty.get());
            netRoot.getTransforms().setAll(scale);
            root.getTransforms().setAll(scale);

            event.consume();
        });
        scene.setFill(Color.web("#0C021B"));
        addToGridPane();
        stage.setScene(scene);
        stage.show();
    }
    private void prepareGrid() {
        root.getColumnConstraints().clear();
        root.getRowConstraints().clear();
        int columnCount = allNodes;
        int rowCount = allLevels;
        root.setVgap(heightRow);
        System.out.println("h: " + heightRow);
        System.out.println("FIXED: " + columnCount + ";" + rowCount);
        for(int i=0;i<columnCount;i++) {
            root.getColumnConstraints().add(new ColumnConstraints());
        }
        for(int i=0;i<rowCount;i++) {
            root.getRowConstraints().add(new RowConstraints());
        }
        root.setHgap(0);
    }
    private void addToGridPane() {
        for(int i=0;i<signs.size();i++) {
            root.add(DrawCircle.newCircle(circleSize,signs.get(i).getSign()), i, signs.get(i).getLevel());
        }
    }
    int k = 0;
    private void checkIfChildren(Node root) {
        if(root != null) {
            if(Node.hasLeftChildren(root)) {
                Line line = new Line();
                line.setStartX((root.number+1) * (2*circleSize) - circleSize);
                line.setStartY(signs.get(root.number).getLevel() * (heightRow+(2*circleSize)) + circleSize);
                line.setEndX((root.left.number + 1) * (2*circleSize) - circleSize);
                line.setEndY(signs.get(root.left.number).getLevel() * (heightRow+(2*circleSize)) + circleSize);
                line.setStroke(Color.web("white"));
                line.setStrokeWidth(5);
                netRoot.getChildren().add(line);
                System.out.println("Node: " + root.number + "; leftChild: " + root.left.number);
            }
            if(Node.hasRightChildren(root)) {
                Line line = new Line();
                line.setStartX((root.number+1) * (2*circleSize) - circleSize);
                line.setStartY(signs.get(root.number).getLevel() * (heightRow+(2*circleSize)) + circleSize);
                line.setEndX((root.right.number + 1) * (2*circleSize) - circleSize);
                line.setEndY(signs.get(root.right.number).getLevel() * (heightRow+(2*circleSize)) + circleSize);
                line.setStroke(Color.web("white"));
                line.setStrokeWidth(5);
                netRoot.getChildren().add(line);
                System.out.println("Node: " + root.number + "; rightChild: " + root.right.number);
            }
            k++;
            checkIfChildren(root.left);
            checkIfChildren(root.right);
        }
    }

}