package org.cccccc.clickerheroes.gold;

import org.cccccc.clickerheroes.datatype.ExpExprProperty;
import org.cccccc.clickerheroes.monster.Monster;

public interface Gold {
    boolean beSpent(ExpExprProperty cost);
    void beEarned(Monster monster);
}
