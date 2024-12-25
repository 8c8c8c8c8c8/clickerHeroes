package hero;

import gold.Gold;
import monster.Monster;

public abstract class AbstractHero implements Hero {
    protected final long ORIGIN_DAMAGE;
    protected long damage;
    protected int level;
    protected long cost;
    protected boolean isActive;

    protected AbstractHero(long damage, long cost) {
        this.ORIGIN_DAMAGE = damage;
        this.level = 1;
        this.damage = damage;
        this.cost = cost;
        this.isActive = false;
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
            upgradeDamage();
        }
    }

    private void upgradeDamage() {
        this.damage = this.ORIGIN_DAMAGE * level;
    }

    @Override
    public String toString() {
        return String.format("damage: %d, level: %d, cost: %d, active: %s",
                damage, level, cost, isActive);
    }
}
