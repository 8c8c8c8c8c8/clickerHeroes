package org.cccccc.clickerheroes.javafx;

import com.google.inject.Inject;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
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
    private Label goldStatus;
    @FXML
    private Label monsterStatus;
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
        container.getStyleClass().add("h1");
        container.setMinWidth(390);
        container.setMinHeight(100);
        Button levelUpBtn = new Button("LevelUp");
        levelUpBtn.getStyleClass().setAll("btn", "btn-warning");
        levelUpBtn.setPrefHeight(100);
        levelUpBtn.setPrefWidth(100);
        Label heroInfo = new Label("Cid");
        heroInfo.getStyleClass().setAll("lbl", "lbl-warning");
        heroInfo.setPrefWidth(290);
        heroInfo.setPrefHeight(100);
        container.getChildren().add(heroInfo);
        container.getChildren().add(levelUpBtn);
        heroesList.getChildren().add(container);
    }

    private void runGUI() {

    }

    private void sendMessage(String concatenatedMsg) {
        String[] splitMsgs = concatenatedMsg.split("\\$");
        String monsterStatusMsg = splitMsgs[0];
        String goldStatusMsg = splitMsgs[1];
        monsterStatus.setText(monsterStatusMsg);
        goldStatus.setText(goldStatusMsg);
    }

    private void runClickerHeroes() {
        clickerHeroesTask.messageProperty().addListener((obs, oldMsg, newMsg) -> {
            sendMessage(obs.getValue());
        });
        Thread chTask = new Thread(clickerHeroesTask);
        chTask.setDaemon(true);
        chTask.start();

    }
}