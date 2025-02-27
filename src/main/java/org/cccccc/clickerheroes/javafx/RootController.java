package org.cccccc.clickerheroes.javafx;

import com.google.inject.Inject;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.cccccc.clickerheroes.clickerHeroes.ClickerHeroes;
import org.cccccc.clickerheroes.clickerHeroes.ClickerHeroesTask;
import org.cccccc.clickerheroes.javafx.gui.ClickerHeroesGui;
import org.cccccc.clickerheroes.javafx.gui.ClickerHeroesGuiImpl;

import java.net.URL;
import java.util.ResourceBundle;

public class RootController implements Initializable {
    @FXML
    private Label goldStatus;
    @FXML
    private Label monsterStatus;
    @FXML
    private VBox heroesList;
    private final ClickerHeroesGui clickerHeroesGui;
    private final ClickerHeroesTask clickerHeroesTask;

    @Inject
    public RootController(ClickerHeroes clickerHeroes) {
        this.clickerHeroesGui = new ClickerHeroesGuiImpl(clickerHeroes);
        this.clickerHeroesTask = new ClickerHeroesTask(clickerHeroes);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        runGUI();
        initHeroesList();
        runClickerHeroes();
    }

    private void initHeroesList() {
        // todo 구현된 모든 Hero 다 생성하고 blind 처리할 것
        String startingHeroName = "Cid";
        generateHeroItem(startingHeroName);
    }

    private void generateHeroItem(String heroName) {
        // todo heroContainer 숨기고 heroesList 를 인자로 건네기
        HBox heroContainer = clickerHeroesGui.generateHeroContainer(heroName);
        heroesList.getChildren().add(heroContainer);
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