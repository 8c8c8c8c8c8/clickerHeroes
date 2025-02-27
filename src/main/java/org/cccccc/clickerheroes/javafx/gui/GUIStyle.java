package org.cccccc.clickerheroes.javafx.gui;

enum GUIStyle {
    FONT_SIZE("h1"),
    BTN_STYLE("btn$btn-warning"),
    LABEL_STYLE("lbl$lbl-warning");

    private final String style;

    GUIStyle(String style) {
        this.style = style;
    }

    public String getStyle() {
        return style;
    }

    public static String[] getBtnStyle() {
        return BTN_STYLE.getStyle().split("\\$");
    }

    public static String[] getLabelStyle() {
        return LABEL_STYLE.getStyle().split("\\$");
    }
}