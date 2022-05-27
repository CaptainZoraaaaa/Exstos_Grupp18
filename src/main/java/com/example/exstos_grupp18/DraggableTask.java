package com.example.exstos_grupp18;

import javafx.scene.Node;
    public class DraggableTask {
        private double mouseAnchorX;
        private double mouseAnchorY;

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
