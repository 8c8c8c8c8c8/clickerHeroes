package org.cccccc.clickerheroes.clickerHeroes;

import java.util.List;

public interface ClickerHeroes {
    void levelUpHero(String heroName, int level);

    void goToMonsterLevel(int level);

    void runCycle();

    List<String> getAllHeroNames();
}
