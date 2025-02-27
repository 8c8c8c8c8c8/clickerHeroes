package org.cccccc.clickerheroes.monster;

import org.cccccc.clickerheroes.datatype.ExpExprProperty;

public interface Monster {
    int getHpRatio();
    void beAttacked(ExpExprProperty damage);
    boolean isAlive();
    void levelUp();
    long yieldGold();
    void goToLevel(int level);
}
