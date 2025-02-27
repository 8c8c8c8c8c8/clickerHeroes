module org.cccccc.clickerheroes {
    requires javafx.fxml;
    requires javafx.controls;
    requires com.google.guice;
    requires org.kordamp.bootstrapfx.core;

    opens org.cccccc.clickerheroes to javafx.fxml;
    exports org.cccccc.clickerheroes;
    exports org.cccccc.clickerheroes.javafx;
    opens org.cccccc.clickerheroes.javafx to javafx.fxml;
    exports org.cccccc.clickerheroes.clickerHeroes;
    exports org.cccccc.clickerheroes.gold;
    exports org.cccccc.clickerheroes.monster;
    exports org.cccccc.clickerheroes.hero.heroes;
    exports org.cccccc.clickerheroes.datatype;
}
