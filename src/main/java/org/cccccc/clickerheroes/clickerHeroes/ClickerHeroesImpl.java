package org.cccccc.clickerheroes.clickerHeroes;

import com.google.inject.Inject;
import org.cccccc.clickerheroes.gold.Gold;
import org.cccccc.clickerheroes.hero.heroes.Heroes;
import org.cccccc.clickerheroes.monster.Monster;

public class ClickerHeroesImpl implements ClickerHeroes {
    private boolean isRunning = true;
    private final Monster monster;
    private final Gold gold;
    private final Heroes heroes;

    @Inject
    public ClickerHeroesImpl(Monster monster, Gold gold, Heroes heroes) {
        this.monster = monster;
        this.gold = gold;
        this.heroes = heroes;
    }

    @Override
    public String getGoldStatus() {
        return gold.toString();
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
    public void levelUpHero(String heroName, int level) {
        heroes.levelUp(heroName, gold, level);
    }

    @Override
    public void goToMonsterLevel(int level) {
        monster.goToLevel(level);
    }

    @Override
    public String getMonsterStatus() {
        return monster.toString();
    }

    @Override
    public void runCycle() {
        heroes.attack(monster);
        if (!monster.isAlive()) {
            gold.beEarned(monster);
            monster.levelUp();
        }
    }
}
