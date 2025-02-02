package org.cccccc.clickerheroes.javafx.gui;

import javafx.concurrent.Task;
import org.cccccc.clickerheroes.clickerHeroes.ClickerHeroes;
import org.cccccc.clickerheroes.hero.heroes.Heroes;

import java.util.Arrays;
import java.util.List;

public class ClickerHeroesGui extends Task<Void> {
    private final ClickerHeroes clickerHeroes;

    public ClickerHeroesGui(ClickerHeroes clickerHeroes) {
        this.clickerHeroes = clickerHeroes;
    }

    public void runCommand(String input) {
        if (CLIGameMenu.HeroStatus.equals(input)) {
            updateMessage(Heroes.getHeroStatus());
        } else if (CLIGameMenu.GoldStatus.equals(input)) {
            updateMessage(clickerHeroes.getGoldStatus());
        } else if (CLIGameMenu.HireHero.equals(input)) {
//                hireHero();
        } else if (CLIGameMenu.LevelUpHero.equals(input)) {
//                levelUpHero();
        } else if (CLIGameMenu.ActivateHeroSkill.equals(input)) {
//                activateHeroSkill();
        } else if (CLIGameMenu.GoToMonsterLevel.equals(input)) {
//                goToMonsterLevel();
        } else if (CLIGameMenu.ChangeMode.equals(input)) {
//                changeMode();
        } else if (CLIGameMenu.ExitGame.equals(input)) {
//                exitGame();
        }
    }

    private void sendGameMenuMessage() {
        updateMessage(CLIGameMenuMessage.getGameMenuMessage());
    }

    @Override
    protected Void call() throws Exception {
        sendGameMenuMessage();
        return null;
    }
}

enum CLIGameMenu {
    HeroStatus(1), GoldStatus(2), HireHero(3), LevelUpHero(4),
    ActivateHeroSkill(5), GoToMonsterLevel(6),
    ChangeMode(7), ExitGame(0);
    private final int MENU;
    private final String MENU2;

    CLIGameMenu(int menu) {
        this.MENU = menu;
        this.MENU2 = String.valueOf(menu);
    }

    public boolean equals(int menu) {
        return this.MENU == menu;
    }

    public boolean equals(String menu) {
        return this.MENU2.equals(menu);
    }
}

enum CLIGameMenuMessage {
    HeroStatus("1. Hero Status"),
    GoldStatus("2. Gold Status"),
    HireHero("3. Hire Hero"),
    LevelUpHero("4. LevelUp Hero"),
    ActivateHeroSkill("5. Activate Hero Skill"),
    GoToMonsterLevel("6. Go to Monster Level"),
    ChangeMode("7. Change Mode"),
    ExitGame("0. Exit Game");
    private final String MESSAGE;

    CLIGameMenuMessage(String MESSAGE) {
        this.MESSAGE = MESSAGE;
    }

    @Override
    public String toString() {
        return MESSAGE;
    }

    public static String getGameMenuMessage() {
        List<String> gameMenuList = Arrays.stream(CLIGameMenuMessage.values())
                .map(Enum::toString).toList();
        return String.join("\n", gameMenuList);
    }

}