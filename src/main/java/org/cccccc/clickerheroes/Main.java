package org.cccccc.clickerheroes;

import com.google.inject.Guice;
import com.google.inject.Injector;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.cccccc.clickerheroes.di.DIModule;
import org.kordamp.bootstrapfx.BootstrapFX;


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
        Injector injector = Guice.createInjector(new DIModule());
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("root.fxml"));
        fxmlLoader.setControllerFactory(injector::getInstance);
        Scene scene = new Scene(fxmlLoader.load());
        scene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
        stage.setScene(scene);
        stage.setTitle("ClickerHeroes");
        stage.show();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
    }

    public static void main(String[] args) {
        launch();
    }
}
