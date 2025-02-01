package org.cccccc.clickerheroes.gold;

import org.cccccc.clickerheroes.monster.Monster;

public interface Gold {
    boolean spendGold(long cost);
    void beEarned(Monster monster);
}
