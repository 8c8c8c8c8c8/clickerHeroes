package org.cccccc.clickerheroes.javafx;

import com.google.inject.Inject;
import javafx.fxml.Initializable;
import org.cccccc.clickerheroes.clickerHeroes.ClickerHeroes;
import org.cccccc.clickerheroes.clickerHeroes.ClickerHeroesTask;
import org.cccccc.clickerheroes.javafx.gui.ClickerHeroesGui;
import org.cccccc.clickerheroes.javafx.gui.ClickerHeroesGuiImpl;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

public class RootController implements Initializable {
    private Map<String, Object> nameSpace;
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
        runClickerHeroes();
    }

    public void setNameSpace(Map<String, Object> nameSpace) {
        this.nameSpace = nameSpace;
    }

    private void runGUI() {
        clickerHeroesGui.setNameSpace(nameSpace);
        clickerHeroesGui.run();
    }

    private void runClickerHeroes() {
        Thread chTask = new Thread(clickerHeroesTask);
        chTask.setDaemon(true);
        chTask.start();
    }
}