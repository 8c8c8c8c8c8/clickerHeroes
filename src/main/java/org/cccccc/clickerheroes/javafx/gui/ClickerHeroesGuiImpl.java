package org.cccccc.clickerheroes.javafx.gui;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import org.cccccc.clickerheroes.clickerHeroes.ClickerHeroes;
import org.cccccc.clickerheroes.utils.BindToFXML;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

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
        HBox.setMargin(stackPane, new Insets(0, 10, 0, 0));
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

    private CustomPane generateDpsUpgradePane(String heroName) {
        CustomPane dpsUpgradePane = new CustomPane();
        CustomLabel dpsLabel = new CustomLabel();
        addToNameSpace(String.format("%sDamage", heroName), dpsLabel);
        CustomGridPane upgradeGridPane = new CustomGridPane();
        addUpgradeImageToGridPane(upgradeGridPane, heroName);
        dpsUpgradePane.getChildren().addAll(dpsLabel, upgradeGridPane);
        return dpsUpgradePane;
    }

    private List<String> getHeroUpgradePath(String heroName) {
        String path = Objects.requireNonNull(
                getClass().getResource(
                        String.format("/images/hero/%s/upgrades", heroName.toLowerCase())))
                .getPath();
        File folder = new File(path);
        return Arrays.stream(Objects.requireNonNull(folder.listFiles()))
                .map(File::getPath).toList();
    }

    public void addUpgradeImageToGridPane(GridPane gridPane, String heroName) {
        List<String> upgradePath = getHeroUpgradePath(heroName);
        IntStream.range(0, upgradePath.size())
                .mapToObj(i -> Map.entry(i, upgradePath.get(i)))
                .forEach(entry -> {
                    int colIndex = entry.getKey();
                    String path = entry.getValue();
                    System.out.println(path);
                    System.out.println(getClass().getResource(path));
                    CustomImage upgradeImage = new CustomImage();
                    upgradeImage.setUpgradeImage(path);
                    gridPane.add(upgradeImage, colIndex, 0);
                });
    }

    private CustomPane generateNameLevelPane(String heroName) {
        CustomPane nameLevelPane = new CustomPane();
        CustomLabel nameLabel = new CustomLabel(heroName);
        CustomLabel levelLabel = new CustomLabel();
        addToNameSpace(String.format("%sLevel", heroName), levelLabel);
        nameLevelPane.getChildren().addAll(nameLabel, levelLabel);
        return nameLevelPane;
    }

    private void addAllHeroesToContainer() {
        // todo
        List<String> heroNameList = clickerHeroes.getAllHeroNames();
        VBox heroContainers = (VBox) nameSpace.get("heroList");
        heroNameList.forEach(heroName -> {
            HBox heroContainer = new HeroContainer();
            StackPane btnStackPane = generateLevelUpBtnStackPane(heroName);
            CustomPane dpsUpgradePane = generateDpsUpgradePane(heroName);
            CustomPane nameLevelPane = generateNameLevelPane(heroName);
            CustomImage heroImage = new CustomImage();
            heroContainer.getChildren().addAll(btnStackPane, dpsUpgradePane, nameLevelPane, heroImage);
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

    private static class CustomPane extends Pane {
        public CustomPane() {
            super();
        }

        private void setDPSUpgradeStyle() {
            // todo
        }

        private void setNameLevelStyle() {
            // todo
        }
    }

    private static class CustomGridPane extends GridPane {
        public CustomGridPane() {
            super();
            setStyle();
        }

        private void setStyle() {
            // todo
        }
    }

    private static class CustomImage extends ImageView {
        public CustomImage() {
            super();
        }

        private void setHeroImage(String path) {
            // todo set Hero Image style
            this.setImage(new Image(path));
        }

        private void setUpgradeImage(String path) {
            // todo set hero upgrade image style
            this.setImage(new Image(Objects.requireNonNull(getClass().getResource(path)).getPath()));
        }
    }

    private static class CustomLabel extends Label {
        // dps, hero name, level
        public CustomLabel() {
            super();
            setStyle();
        }

        public CustomLabel(String s) {
            super(s);
            setStyle();
        }

        private void setStyle() {
            // todo
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