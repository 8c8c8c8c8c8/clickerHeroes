package org.cccccc.clickerheroes.javafx.gui;

enum GUIConstant {
    HBOX(390, 100),
    BUTTON(90, 90),
    LABEL(280, 90);
    private final int width;
    private final int height;

    GUIConstant(int width, int height) {
        this.width = width;
        this.height = height;
    }

    int getWidth() {
        return width;
    }

    int getHeight() {
        return height;
    }
}