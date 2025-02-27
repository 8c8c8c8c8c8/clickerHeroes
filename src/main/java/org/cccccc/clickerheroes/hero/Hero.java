package org.cccccc.clickerheroes.hero;

import org.cccccc.clickerheroes.datatype.ExpExprProperty;
import org.cccccc.clickerheroes.gold.Gold;

public interface Hero {
    void levelUp(Gold gold, int level);
    void learnSkill(int skillIndex);
    boolean isActive();
    long getCost(int level);
    void addDamageTo(ExpExprProperty obj);
}
