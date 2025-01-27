module org.cccccc.clickerheroes {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.guice;

    opens org.cccccc.clickerheroes to javafx.fxml;
    exports org.cccccc.clickerheroes;
    exports org.cccccc.clickerheroes.javafx;
    opens org.cccccc.clickerheroes.javafx to javafx.fxml;
}