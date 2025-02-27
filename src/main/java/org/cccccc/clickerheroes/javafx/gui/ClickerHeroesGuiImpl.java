package org.cccccc.clickerheroes.javafx.gui;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import org.cccccc.clickerheroes.clickerHeroes.ClickerHeroes;

public class ClickerHeroesGuiImpl implements ClickerHeroesGui {
    private final ClickerHeroes clickerHeroes;

    public ClickerHeroesGuiImpl(ClickerHeroes clickerHeroes) {
        this.clickerHeroes = clickerHeroes;
    }

    private Label generateHeroLabel(String heroName) {
//        String heroName = heroes.getHeroName(heroIndex);
        Label heroLabel = new HeroLabel(heroName);
        // todo
        return heroLabel;
    }

    private Button generateLevelUpBtn(String heroName) {
        int levelUpUnit = 1;
        Button levelUpBtn = new LevelUpBtn();
        levelUpBtn.setOnAction(actionEvent -> {
            clickerHeroes.levelUpHero(heroName, levelUpUnit);
        });
        return levelUpBtn;
    }

    @Override
    public HBox generateHeroContainer(String heroName) {
        HBox heroContainer = new HeroContainer();
        heroContainer.getChildren()
                .addAll(generateLevelUpBtn(heroName), generateHeroLabel(heroName));
        return heroContainer;
    }

    private static class HeroLabel extends Label {
        public HeroLabel(String s) {
            super(s);
            setPrefWidth(GUIConstant.LABEL.getWidth());
            setPrefHeight(GUIConstant.LABEL.getHeight());
            getStyleClass().setAll(GUIStyle.getLabelStyle());
            setPadding(new Insets(5));
        }
    }

    private static class LevelUpBtn extends Button {
        public LevelUpBtn() {
            super("LevelUp");
            setPrefHeight(GUIConstant.BUTTON.getHeight());
            setPrefWidth(GUIConstant.BUTTON.getWidth());
            getStyleClass().setAll(GUIStyle.getBtnStyle());
            setPadding(new Insets(5));
        }
    }

    private static class HeroContainer extends HBox {
        public HeroContainer() {
            super();
            setMinWidth(GUIConstant.HBOX.getWidth());
            setMinHeight(GUIConstant.HBOX.getHeight());
            getStyleClass().add(GUIStyle.FONT_SIZE.getStyle());
        }
    }
}