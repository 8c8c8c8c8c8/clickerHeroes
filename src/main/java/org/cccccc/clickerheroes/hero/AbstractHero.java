package org.cccccc.clickerheroes.hero;

import javafx.scene.control.Label;
import org.cccccc.clickerheroes.datatype.ExpExprProperty;
import org.cccccc.clickerheroes.gold.Gold;
import utils.BindToLabel;

public abstract class AbstractHero implements Hero, BindToLabel {
    private final ExpExprProperty baseCost;
    private final ExpExprProperty baseDamage;

    protected final double LEVEL_UP_COST_INC_RATIO = 1.07f;
    protected int level;
    protected ExpExprProperty cost;

    public AbstractHero(String damage, String cost) {
        String heroName = getClass().getSimpleName();
        this.baseCost = new ExpExprProperty(String.format("%sCost", heroName), cost);
        this.baseDamage = new ExpExprProperty(String.format("%sDamage", heroName), damage);
        this.level = 0;
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
//        damage = baseDamage * level;
        // todo
    }

    @Override
    public String toString() {
        // todo
        return super.toString();
//        return String.format("damage: %d, level: %d", damage, level);
    }

    @Override
    public boolean isActive() {
        return level > 0;
    }

    protected long levelUpCostFormula(int level) {
        // C = BASE_COST * (LEVEL_UP_COST_INC_RATIO ^ level - 1) / (LEVEL_UP_COST_INC_RATIO - 1)
//        return baseCost * (long) Math.floor(
//                (Math.pow(LEVEL_UP_COST_INC_RATIO, level) - 1) / (LEVEL_UP_COST_INC_RATIO - 1)
//        );
        // todo
        return 0;
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

    @Override
    public void bindToLabel(Label label) {
        // todo
    }

    @Override
    public void addDamageTo(ExpExprProperty obj) {
        // todo
    }
}
