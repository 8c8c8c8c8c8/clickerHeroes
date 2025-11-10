package org.cccccc.clickerheroes.clickerHeroes;

import com.google.inject.Inject;
import org.cccccc.clickerheroes.gold.Gold;
import org.cccccc.clickerheroes.hero.heroes.Heroes;
import org.cccccc.clickerheroes.monster.Monster;
import org.cccccc.clickerheroes.utils.BindToFXML;

import java.util.List;
import java.util.Map;

public class ClickerHeroesImpl implements ClickerHeroes, BindToFXML {
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
    public void levelUpHero(String heroName, int level) {
        heroes.levelUp(heroName, gold, level);
    }

    @Override
    public void goToMonsterLevel(int level) {
        monster.goToLevel(level);
    }

    @Override
    public void runCycle() {
        heroes.attack(monster);
        if (!monster.isAlive()) {
            gold.addDroppedGold(monster);
            monster.levelUp();
        }
    }

    private void bind(BindToFXML obj, Map<String, Object> nameSpace) {
        obj.bind(nameSpace);
    }

    @Override
    public void bind(Map<String, Object> nameSpace) {
        bind((BindToFXML) monster, nameSpace);
        bind((BindToFXML) gold, nameSpace);
        bind((BindToFXML) heroes, nameSpace);
    }

    @Override
    public List<String> getAllHeroNames() {
        return heroes.getAllHeroNames();
    }
}
