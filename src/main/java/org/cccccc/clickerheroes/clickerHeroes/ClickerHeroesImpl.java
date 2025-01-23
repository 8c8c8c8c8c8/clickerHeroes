package org.cccccc.clickerheroes.clickerHeroes;

import org.cccccc.clickerheroes.gold.Gold;
import org.cccccc.clickerheroes.hero.heroes.Heroes;
import org.cccccc.clickerheroes.monster.Monster;

public class ClickerHeroesImpl implements ClickerHeroes, Runnable {
    private final int ONE_SECOND = 1000;
    private boolean isRunning = true;
    private final Monster monster;
    private final Gold gold;

    public ClickerHeroesImpl(Monster monster, Gold gold) {
        this.monster = monster;
        this.gold = gold;
    }

    private void printMonsterStatus() {
        System.out.println(monster);
    }
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
    public void run() {
        isRunning = true;
        while (isRunning) {
            printMonsterStatus();
            allHeroesAttackMonster();
            if (!monster.isAlive()) {
                gold.earnGold(monster);
                monster.levelUp();
            }
            try {
                Thread.sleep(ONE_SECOND);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void close() {
        isRunning = false;
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

}
