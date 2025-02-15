package org.cccccc.clickerheroes.gold;

import org.cccccc.clickerheroes.monster.Monster;

public interface Gold {
    boolean beSpent(long cost);
    void beEarned(Monster monster);
}
