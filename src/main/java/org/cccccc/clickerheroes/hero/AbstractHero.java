package org.cccccc.clickerheroes.hero;

import org.cccccc.clickerheroes.gold.Gold;
import org.cccccc.clickerheroes.monster.Monster;

public abstract class AbstractHero implements Hero {
    private final long BASE_COST;
    private long baseDamage;
    private long damage;

    protected final double LEVEL_UP_COST_INC_RATIO = 1.07f;
    protected int level;

    public AbstractHero(long damage, long cost) {
        this.baseDamage = damage;
        this.level = 0;
        this.damage = damage;
        this.BASE_COST = cost;
    }

    @Override
    public void attack(Monster monster) {
        if (isActive()) {
            monster.beAttacked(damage);
        }
    }

    @Override
    public void levelUp(Gold gold, int level) {
        long cost = getCost(level);
        if (gold.beSpent(cost)) {
            this.level += level;
            updateDamage();
        }
    }

    private void updateDamage() {
        damage = baseDamage * level;
    }

    @Override
    public String toString() {
        return String.format("damage: %d, level: %d", damage, level);
    }

    @Override
    public boolean isActive() {
        return level > 0;
    }

    protected long levelUpCostFormula(int level) {
        // C = BASE_COST * (LEVEL_UP_COST_INC_RATIO ^ level - 1) / (LEVEL_UP_COST_INC_RATIO - 1)
        return BASE_COST * (long) Math.floor(
                (Math.pow(LEVEL_UP_COST_INC_RATIO, level) - 1) / (LEVEL_UP_COST_INC_RATIO - 1)
        );
    }

    @Override
    public long getCost(int level) {
        long zeroToDesiredLvCost = levelUpCostFormula(this.level + level);
        if (this.level > 0) {
            long zeroToCurrentLvCost = levelUpCostFormula(this.level);
            long currentToDesiredLvCost = zeroToDesiredLvCost - zeroToCurrentLvCost;
            return currentToDesiredLvCost;
        }
        return zeroToDesiredLvCost;
    }
}
