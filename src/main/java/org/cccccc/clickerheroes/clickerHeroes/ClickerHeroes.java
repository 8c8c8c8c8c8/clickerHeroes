package org.cccccc.clickerheroes.clickerHeroes;

public interface ClickerHeroes {
    String getGoldStatus();
    void start();
    void close();
    boolean isRunning();
    void levelUpHero(String heroName, int level);
    void goToMonsterLevel(int level);
    String getMonsterStatus();
    void runCycle();
}
