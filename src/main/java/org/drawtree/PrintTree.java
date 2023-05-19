package org.drawtree;

import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;
import org.decompression.Node;
import org.decompression.Points;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class PrintTree extends Application {
    Node tree;  //drzewo do printa
    Map<Integer, Character> signs;
    Points points;
    int level = 1;
    final int MAX_WIDTH = 8000;
    final int WIDTH = 1216;
    final int HEIGHT = 800;
    final int MAX_HEIGHT = 8000;
    final double MIN_SCALE = 0.1;
    final double MAX_SCALE = 10.0;
    private final DoubleProperty scaleProperty = new SimpleDoubleProperty(1.0);
    private double lastX, lastY;

    //parametry Circle
    int sizeCircle;
    int centerX;
    int centerY;
    int u;
    public PrintTree (Node tree) {
        this.tree = tree;
//        this.tree.writeTree(this.tree);
        makeTreeList();
        setCircleParametrs();
    }
    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Tree");//blo
        Pane root = new Pane();//bylo
        prepareTree(root);

        Scene scene = new Scene(root, WIDTH, HEIGHT); //bylo

        scene.setOnMousePressed(event -> {
            lastX = event.getSceneX();
            lastY = event.getSceneY();
        });

        scene.setOnMouseDragged(event -> {
            double deltaX = event.getSceneX() - lastX;
            double deltaY = event.getSceneY() - lastY;

            root.setTranslateX(root.getTranslateX() + deltaX);
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
            root.getTransforms().setAll(scale);

            event.consume();
        });

        stage.setScene(scene);//bylo
        stage.show();//bylo
    }
    private void makeTreeList() {
        if (tree == null) {
            return;
        }
        signs = new HashMap<>();
        Queue<Node> queue = new LinkedList<>();
        queue.add(tree);
        int index = 0;
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            boolean allNulls = true;

            for (int i = 0; i < levelSize; i++) {
                Node node = queue.poll();

                if (node != null) {
                    signs.put(index, (char)node.sign);
                    if (node.left != null) {
                        queue.add(node.left);
                        allNulls = false;
                    } else {
                        queue.add(null);
                    }

                    if (node.right != null) {
                        queue.add(node.right);
                        allNulls = false;
                    } else {
                        queue.add(null);
                    }
                } else {
                    queue.add(null);
                    queue.add(null);
                }
                index++;
            }

            if (allNulls) {
                break;
            }
            this.level++;
        }
    }
    private void setCircleParametrs() {
        double maxNodes = Math.pow(2,level-1);
        this.sizeCircle = (int) (MAX_WIDTH/(2*maxNodes));
        this.centerX = 2*(this.sizeCircle);
        this.centerY = MAX_HEIGHT/level;

    }
    private void prepareTree(Pane root) {
        points = new Points();
        int tmpX;
        int tmpY = centerY/2;
        int tmpMath;
        u = 0;
        for (int j = 0; j < level-1; j++) {
            tmpMath = (int) Math.pow(2,j);
            tmpX = MAX_WIDTH/(tmpMath + 1);
            for (int k = 0; k < tmpMath; k++) {
                points.addPoint(tmpX,tmpY,u, (char)0);
                u++;
                root.getChildren().add(DrawCircle.newCircle(sizeCircle, tmpX, tmpY, (char)0, 0));

                tmpX += MAX_WIDTH/(tmpMath + 1);;
            }
            tmpY += centerY;
        }
        tmpX = centerX/2;
        System.out.println(tmpX);
        System.out.println(centerX);
        tmpMath = (int) Math.pow(2,level-1);
        System.out.println(tmpMath);
        for (int i = 0; i < tmpMath; i++) {
            points.addPoint(tmpX,tmpY,u, (char)0);
            u++;
            root.getChildren().add(DrawCircle.newCircle(sizeCircle, tmpX, tmpY, (char)0, 0));
            tmpX += centerX;
        }
        insertTree(root);
        DrawLine.treeNet(root,points, signs);
        insertTree(root);
    }
    private void insertTree(Pane root) {
        for(int i = 0;i < u; i++) {
            if(signs.get(i) != null) {
                root.getChildren().add(DrawCircle.newCircle(sizeCircle,points.coordinate.get(i).X, points.coordinate.get(i).Y, signs.get(i), 1));
                points.coordinate.get(i).sign = signs.get(i); //jak nie dziala to przez to
            }
        }
    }

}
