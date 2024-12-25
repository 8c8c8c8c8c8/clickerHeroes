package gold;

import monster.Monster;

public interface Gold {
    boolean spendGold(long cost);
    void earnGold(Monster monster);
}
