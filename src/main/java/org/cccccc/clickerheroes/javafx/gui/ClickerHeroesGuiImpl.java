package org.cccccc.clickerheroes.javafx.gui;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import org.cccccc.clickerheroes.clickerHeroes.ClickerHeroes;
import utils.BindToFXML;

import java.util.List;
import java.util.Map;

public class ClickerHeroesGuiImpl implements ClickerHeroesGui {
    private final ClickerHeroes clickerHeroes;
    private Map<String, Object> nameSpace;

    public ClickerHeroesGuiImpl(ClickerHeroes clickerHeroes) {
        this.clickerHeroes = clickerHeroes;
    }

    private StackPane generateLevelUpBtnStackPane(String heroName) {
        StackPane stackPane = new StackPane();
        List<Integer> levelUpUnits = List.of(1, 5, 10, 25, 100);
        levelUpUnits.forEach(levelUpUnit -> {
            Button btn = generateLevelUpBtn(heroName, levelUpUnit);
            btn.setVisible(false);
            stackPane.getChildren().add(btn);
        });
        return stackPane;
    }

    private void convertLevelUpBtnToActivate(int levelUpUnit) {
        // todo 원하는 레벨업 버튼 활성화
    }

    private Button generateLevelUpBtn(String heroName, int levelUpUnit) {
        Button levelUpBtn = new LevelUpBtn();
        levelUpBtn.setOnAction(actionEvent -> {
            clickerHeroes.levelUpHero(heroName, levelUpUnit);
        });
        return levelUpBtn;
    }

    private void addToNameSpace(String name, Object obj) {
        this.nameSpace.put(name, obj);
    }

    private void addAllHeroesToContainer() {
        // todo
        List<String> heroNameList = clickerHeroes.getAllHeroNames();
        VBox heroContainers = (VBox) nameSpace.get("heroList");
        heroNameList.forEach(heroName -> {
            HBox heroContainer = new HeroContainer();
            CustomLabel heroNameLabel = new CustomLabel(heroName);
            heroNameLabel.setHeroContainerStyle();
            StackPane btnStackPane = generateLevelUpBtnStackPane(heroName);

            heroContainer.getChildren().addAll(heroNameLabel, btnStackPane);
            heroContainers.getChildren().add(heroContainer);
        });
    }

    @Override
    public void run() {
        // todo
        addAllHeroesToContainer();
        ((BindToFXML) clickerHeroes).bind(nameSpace);
    }

    @Override
    public void setNameSpace(Map<String, Object> nameSpace) {
        this.nameSpace = nameSpace;
    }

    private static class CustomGridPane extends GridPane {
        public CustomGridPane() {
            super();
        }

        private void setDamageUpgradeContainerStyle() {
            // todo
        }

        private void setNameLevelContainerStyle() {
            // todo
        }

        private void setUpgradeContainerStyle() {
            // todo
        }
    }

    private static class CustomImage extends ImageView {
        public CustomImage() {
            super();
        }

        private void setImage(String fileName) {
            Image heroImage = new Image(String.valueOf(getClass().getResource(fileName)));
            this.setImage(heroImage);
        }

        private void setHeroImage(String heroName) {
            // todo set Hero Image style
            String fileName = String.format("%s.png", heroName);
            setImage(fileName);
        }

        private void setUpgradeImage(String heroName) {
            // todo set hero upgrade image style
            String fileName = String.format("%s.jpg", heroName);
            setImage(fileName);
        }
    }

    private static class CustomLabel extends Label {
        public CustomLabel(String s) {
            super(s);
        }

        private void setHeroContainerStyle() {
            // todo hero container 안에 label style 정의
//            setPrefWidth(GUIConstant.LABEL.getWidth());
//            setPrefHeight(GUIConstant.LABEL.getHeight());
//            getStyleClass().setAll(GUIStyle.getLabelStyle());
//            setPadding(new Insets(5));
        }
    }

    private static class LevelUpBtn extends Button {
        // todo: set Text to cost
        public LevelUpBtn() {
            super();
            setStyle();
        }

        private void setStyle() {
            setPrefHeight(GUIConstant.BUTTON.getHeight());
            setPrefWidth(GUIConstant.BUTTON.getWidth());
            getStyleClass().setAll(GUIStyle.getBtnStyle());
            setPadding(new Insets(5));
        }
    }

    private static class HeroContainer extends HBox {
        public HeroContainer() {
            super();
            setStyle();
        }

        private void setStyle() {
            setMinWidth(GUIConstant.HBOX.getWidth());
            setMinHeight(GUIConstant.HBOX.getHeight());
            getStyleClass().add(GUIStyle.FONT_SIZE.getStyle());
        }
    }
}