package org.cccccc.clickerheroes.clickerHeroes;

import com.google.inject.Inject;
import org.cccccc.clickerheroes.gold.Gold;
import org.cccccc.clickerheroes.hero.heroes.Heroes;
import org.cccccc.clickerheroes.monster.Monster;

public class ClickerHeroesImpl implements ClickerHeroes {
    private boolean isRunning = true;
    @Inject
    private Monster monster;
    @Inject
    private Gold gold;

    private void allHeroesAttackMonster() {
        for (Heroes hero : Heroes.values()) {
            hero.attack(monster);
        }
    }

    @Override
    public void printGoldStatus() {
        System.out.println(gold);
    }

    @Override
    public void start() {
        isRunning = true;
    }

    @Override
    public void close() {
        isRunning = false;
    }

    @Override
    public boolean isRunning() {
        return isRunning;
    }

    @Override
    public void hireHero(String name) {

    }

    @Override
    public void levelUpHero(String name) {

    }

    @Override
    public void goToMonsterLevel(long level) {
        monster.goToLevel(level);
    }

    @Override
    public String getMonsterStatus() {
        return monster.toString();
    }

    @Override
    public void runCycle() {
        allHeroesAttackMonster();
        if (!monster.isAlive()) {
            gold.earnGold(monster);
            monster.levelUp();
        }
    }
}
