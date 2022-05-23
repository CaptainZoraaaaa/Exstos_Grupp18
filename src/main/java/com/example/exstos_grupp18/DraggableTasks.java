package com.example.exstos_grupp18;

import javafx.scene.Node;

public class DraggableTasks {
    private double mouseAnchorX;
    private double mouseAnchorY;

    public void makeDraggable(Node node) {
        node.setOnMousePressed(mouseEvent -> {
            mouseAnchorX = mouseEvent.getSceneX() - node.getTranslateX();
            mouseAnchorY = mouseEvent.getSceneY() - node.getTranslateY();
        });

        node.setOnMouseDragged(mouseEvent -> {
            node.setTranslateX(mouseEvent.getSceneX() - mouseAnchorX);
            node.setTranslateY(mouseEvent.getSceneY() - mouseAnchorY);
        });
    }
}

