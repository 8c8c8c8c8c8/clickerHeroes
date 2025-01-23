package org.cccccc.clickerheroes;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {

    public Main() {
        super();

    }

    @Override
    public void init() throws Exception {
        super.init();
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("root.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setScene(scene);
        stage.setTitle("ClickerHeroes");
        stage.show();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
    }

    public static void main(String[] args) {
//        Gold gold = new GoldImpl();
//        Monster monster = new MonsterImpl();
//        ClickerHeroesImpl clickerHeroes = new ClickerHeroesImpl(monster, gold);
//        CLI cli = new CLI(clickerHeroes);
//        cli.start();
        launch();
    }
}
