package gold;

import monster.Monster;

public class GoldImpl implements Gold{
    private long gold;
    public GoldImpl() {
        this.gold = 0;
    }
    @Override
    public boolean spendGold(long cost) {
        if (isMoreThanGold(cost)) {
            gold -= cost;
            return true;
        }
        return false;
    }

    @Override
    public void earnGold(Monster monster) {
        long income = monster.yieldGold();
        this.gold += income;
    }

    private boolean isMoreThanGold(long cost) {
        return gold >= cost;
    }

    @Override
    public String toString() {
        return String.format("Gold: %d", gold);
    }
}
