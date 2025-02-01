package org.cccccc.clickerheroes.clickerHeroes;

public interface ClickerHeroes {
    String getGoldStatus();
    void start();
    void close();
    boolean isRunning();
    void hireHero(String name);
    void levelUpHero(String name);
    void goToMonsterLevel(long level);
    String getMonsterStatus();
    void runCycle();
}
