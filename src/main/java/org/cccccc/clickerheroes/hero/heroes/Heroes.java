package org.cccccc.clickerheroes.hero.heroes;

import org.cccccc.clickerheroes.gold.Gold;
import org.cccccc.clickerheroes.monster.Monster;

import java.util.List;

public interface Heroes {
    void levelUp(String name, Gold gold, int level);

    void attack(Monster monster);

    void updateDamage();

    void learnSkill(String name, int skillIndex);

    List<String> getAllHeroNames();
}
