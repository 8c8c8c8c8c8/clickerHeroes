package org.cccccc.clickerheroes.hero;

import org.cccccc.clickerheroes.gold.Gold;
import org.cccccc.clickerheroes.monster.Monster;

public abstract class AbstractHero implements Hero {
    protected final long ORIGIN_DAMAGE;
    protected long damage;
    protected int level;
    protected long cost;
    protected boolean isActive;
    protected float localDamageIncRate;
    protected float globalDamageIncRate;

    public AbstractHero(long damage, long cost) {
        this.ORIGIN_DAMAGE = damage;
        this.level = 1;
        this.damage = damage;
        this.cost = cost;
        this.isActive = false;
        this.localDamageIncRate = 1.0f;
        this.globalDamageIncRate = 1.0f;
    }

    @Override
    public void attack(Monster monster) {
        if (isActive) {
            monster.beAttacked(damage);
        }
    }

    @Override
    public void levelUp(Gold gold) {
        if (gold.spendGold(cost)) {
            level++;
            updateDamage();
        }
    }

    private void updateDamage() {
        this.damage = (long) Math.ceil(this.ORIGIN_DAMAGE *
                localDamageIncRate *
                globalDamageIncRate) * level;
    }

    @Override
    public String toString() {
        return String.format("damage: %d, level: %d, cost: %d",
                damage, level, cost);
    }

    @Override
    public boolean isActive() {
        return isActive;
    }

    @Override
    public boolean canBeHired(Gold gold) {
        if (gold.spendGold(cost)) {
            isActive = true;
            return true;
        }
        return false;
    }
}
