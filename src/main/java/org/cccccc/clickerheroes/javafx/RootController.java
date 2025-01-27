package org.cccccc.clickerheroes.javafx;

import com.google.inject.Inject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import org.cccccc.clickerheroes.clickerHeroes.ClickerHeroes;
import org.cccccc.clickerheroes.clickerHeroes.ClickerHeroesTask;
import org.cccccc.clickerheroes.javafx.cli.CLI;

import java.net.URL;
import java.util.ResourceBundle;

public class RootController implements Initializable {
    @FXML
    private Button enter;
    @FXML
    private TextArea gameOutput, cliOutput;
    @FXML
    private TextField cliInput;
    @Inject
    private ClickerHeroes clickerHeroes;
    private CLI cli;
    private ClickerHeroesTask clickerHeroesTask;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.cli = new CLI(clickerHeroes);
        this.clickerHeroesTask = new ClickerHeroesTask(clickerHeroes);
        enter.setOnAction((event) -> enterUserInput(event));
        cliInput.setOnKeyPressed((event) -> setCliInputOnEnterPressed(event));
        runCLI();
        runClickerHeroes();
    }

    private void enterUserInput(ActionEvent event) {
        cliOutput.appendText("\n" + cliInput.getText());
        cliInput.clear();
        cliInput.requestFocus();
    }

    private void setCliInputOnEnterPressed(KeyEvent event) {
        if (KeyCode.ENTER.equals(event.getCode())) {
            enter.requestFocus();
        }
    }

    private void runCLI() {
        cliOutput.textProperty().bind(cli.messageProperty());
        Thread cliTask = new Thread(cli);
        cliTask.setDaemon(true);
        cliTask.start();
        System.out.println(cliTask.getName());
        System.out.println(Thread.currentThread() + "#########");
    }

    private void runClickerHeroes() {
        gameOutput.textProperty().bind(clickerHeroesTask.messageProperty());
        Thread chTask = new Thread(clickerHeroesTask);
        chTask.setDaemon(true);
        chTask.start();
        System.out.println(chTask.getName());
        System.out.println(Thread.currentThread() + "********");
    }
}
