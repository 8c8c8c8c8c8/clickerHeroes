package org.cccccc.clickerheroes.javafx.cli;

import javafx.concurrent.Task;
import org.cccccc.clickerheroes.clickerHeroes.ClickerHeroes;

import java.util.Arrays;
import java.util.List;

public class CLI extends Task<Void> {
    private final ClickerHeroes clickerHeroes;

    public CLI(ClickerHeroes clickerHeroes) {
        this.clickerHeroes = clickerHeroes;
    }

    private void sendGameMenuMessage() {
        updateMessage(CLIGameMenuMessage.getGameMenuMessage());
    }

    private void printGameMenu() {
        int menu = 0;
//            printMenuMessage();
        if (CLIGameMenu.HeroStatus.equals(menu)) {
//                printHeroStatus();
        } else if (CLIGameMenu.GoldStatus.equals(menu)) {
//                printGoldStatus();
        } else if (CLIGameMenu.HireHero.equals(menu)) {
//                hireHero();
        } else if (CLIGameMenu.LevelUpHero.equals(menu)) {
//                levelUpHero();
        } else if (CLIGameMenu.ActivateHeroSkill.equals(menu)) {
//                activateHeroSkill();
        } else if (CLIGameMenu.GoToMonsterLevel.equals(menu)) {
//                goToMonsterLevel();
        } else if (CLIGameMenu.ChangeMode.equals(menu)) {
//                changeMode();
        } else if (CLIGameMenu.ExitGame.equals(menu)) {
//                exitGame();
        }
    }

    @Override
    protected Void call() throws Exception {
        System.out.println("start cli");
        for (int i=0;i<10;i++) {
            sendGameMenuMessage();
            Thread.sleep(1000);
        }
        return null;
    }
}

enum CLIGameMenu {
    HeroStatus(1), GoldStatus(2), HireHero(3), LevelUpHero(4),
    ActivateHeroSkill(5), GoToMonsterLevel(6),
    ChangeMode(7), ExitGame(0);
    private final int MENU;

    CLIGameMenu(int menu) {
        this.MENU = menu;
    }

    public boolean equals(int menu) {
        return this.MENU == menu;
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