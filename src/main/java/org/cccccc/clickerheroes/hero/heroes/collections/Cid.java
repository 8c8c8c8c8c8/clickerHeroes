package org.cccccc.clickerheroes.hero.heroes.collections;

import org.cccccc.clickerheroes.hero.AbstractHero;

public class Cid extends AbstractHero {
    // starting hero
    public Cid(long damage, long cost) {
        super(damage, cost);
        this.level = 1;
    }

    @Override
    protected long levelUpCostFormula(int level) {
        // C = min(5+level, 20) * (lv_cost_inc_ratio ^ level - 1) / (lv_cost_inc_ratio - 1)
        return Math.min(5 + level, 20) * (long) Math.floor(
                (Math.pow(LEVEL_UP_COST_INC_RATIO, level) - 1) / (LEVEL_UP_COST_INC_RATIO - 1)
        );
    }

    @Override
    public void learnSkill(int skillIndex) {

    }
}