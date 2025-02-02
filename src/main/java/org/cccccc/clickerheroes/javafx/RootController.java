package org.cccccc.clickerheroes.javafx;

import com.google.inject.Inject;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.cccccc.clickerheroes.clickerHeroes.ClickerHeroes;
import org.cccccc.clickerheroes.clickerHeroes.ClickerHeroesTask;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.stream.IntStream;

public class RootController implements Initializable {
    @FXML
    private TextField goldStatus;
    @FXML
    private TextField monsterStatus;
    @FXML
    private VBox heroesList;
    @Inject
    private ClickerHeroes clickerHeroes;
    private ClickerHeroesTask clickerHeroesTask;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.clickerHeroesTask = new ClickerHeroesTask(clickerHeroes);
        runGUI();
        initHeroesList();
        runClickerHeroes();
    }

    private void initHeroesList() {
        IntStream.range(0, 20).forEach(idx -> {
            generateHeroItem();
        });
    }

    private void generateHeroItem() {
        HBox container = new HBox();
        container.setFillHeight(true);
        container.getStyleClass().add("h1");
        Label heroInfo = new Label("Cid");
        heroInfo.getStyleClass().setAll("lbl", "lbl-warning");
        Button levelUpBtn = new Button("LevelUp");
        levelUpBtn.getStyleClass().setAll("btn", "btn-warning");
        container.getChildren().add(heroInfo);
        container.getChildren().add(levelUpBtn);
        heroesList.getChildren().add(container);
    }

    private void runGUI() {

    }

    private void runClickerHeroes() {
        monsterStatus.textProperty().bind(clickerHeroesTask.messageProperty());
        Thread chTask = new Thread(clickerHeroesTask);
        chTask.setDaemon(true);
        chTask.start();
        System.out.println(chTask.getName());
        System.out.println(Thread.currentThread() + "********");
    }
}
