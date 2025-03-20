package org.cccccc.clickerheroes.gold;

import org.cccccc.clickerheroes.datatype.ExpExprProperty;
import org.cccccc.clickerheroes.monster.Monster;

public interface Gold {
    boolean spend(ExpExprProperty cost);
    void addDroppedGold(Monster monster);
}
