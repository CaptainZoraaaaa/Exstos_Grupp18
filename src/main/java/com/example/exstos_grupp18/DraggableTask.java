package com.example.exstos_grupp18;

import javafx.scene.Node;

/**
 * This class is used to make task objects draggable on the Kanban board.
 * @author Christian Edvall & Max Tiderman
 */
    public class DraggableTask {
        private double mouseAnchorX;
        private double mouseAnchorY;

    /**
     * this method is what makes a task draggable by setting it's position.
     * @param node it takes a node as parameter.
     */
    public void makeDraggable(Node node) {
            node.setOnMousePressed(mouseEvent -> {
                mouseAnchorX = mouseEvent.getSceneX() - node.getTranslateX();
                System.out.println(node.getTranslateX());
                mouseAnchorY = mouseEvent.getSceneY() - node.getTranslateY();
                System.out.println(node.getTranslateY());
            });
            node.setOnMouseDragged(mouseEvent -> {
                System.out.println(node.getParent());
                node.setTranslateX(mouseEvent.getSceneX() - mouseAnchorX);
                node.setTranslateY(mouseEvent.getSceneY() - mouseAnchorY);
            });
        }
    }
