package org.cccccc.clickerheroes.hero;

import org.cccccc.clickerheroes.gold.Gold;
import org.cccccc.clickerheroes.monster.Monster;

public interface Hero {
    void attack(Monster monster);
    void levelUp(Gold gold, int level);
    void learnSkill(int skillIndex);
    boolean isActive();
    long getCost(int level);
}
