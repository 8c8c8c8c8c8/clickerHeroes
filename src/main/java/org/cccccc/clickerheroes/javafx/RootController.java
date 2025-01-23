package org.cccccc.clickerheroes.javafx;

import com.google.inject.Inject;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

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
    private Task cli;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        enter.setOnAction((event) -> enterUserInput(event));
        cliInput.setOnKeyPressed((event) -> setCliInputOnEnterPressed(event));
        runCLI();
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
        new Thread(cli).start();
    }

    private void runClickerHeroes() {

    }
}
