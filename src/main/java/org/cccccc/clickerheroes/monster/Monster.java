package org.cccccc.clickerheroes.monster;

import org.cccccc.clickerheroes.datatype.ExpExprProperty;

public interface Monster {
    void beAttacked(ExpExprProperty damage);
    boolean isAlive();
    void levelUp();
    ExpExprProperty dropGold();
    void goToLevel(int level);
}
