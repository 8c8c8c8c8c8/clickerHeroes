package org.cccccc.clickerheroes.monster;

import javafx.scene.control.Label;
import org.cccccc.clickerheroes.datatype.ExpExprProperty;
import utils.BindToLabel;

public class MonsterImpl implements Monster, BindToLabel {
    private final ExpExprProperty maxHp;
    private final ExpExprProperty hp; // current hp
    private int level; // current level
    private int maxLevel;

    public MonsterImpl() {
        // initial monster
        this.maxHp = new ExpExprProperty("maxHp", MonsterConstInt.baseHp.get());
        this.hp = new ExpExprProperty("hp", MonsterConstInt.baseHp.get());
        this.level = 1;
        this.maxLevel = 1;
    }

    @Override
    public int getHpRatio() {
        // todo
        return 0;
    }

    @Override
    public void beAttacked(ExpExprProperty damage) {
        hp.subtract(damage);
    }

    @Override
    public boolean isAlive() {
        return !hp.isZero();
    }

    @Override
    public void levelUp() {
        level++;
        maxLevel++;
        goToLevel(level);
    }

    @Override
    public long yieldGold() {
        // todo
        return 0;
    }

    @Override
    public void goToLevel(int level) {
        if (level > maxLevel) {
            throw new IllegalArgumentException("desired level can not exceed max-level");
        }
        this.level = level;
        if (level <= MonsterConstInt.levelBaseLine.get()) {
            calcHp(level);
            return;
        }
        calcHp2(level);
    }

    private int isBoss() {
        // boss bonus : hp x 10
        // boss appear if level is multiple of 5 and more than 100
        // o.w multiple of 10
        if (level < 100) {
            return level % 10 == 0 ? MonsterConstInt.bossBonus.get() : 1;
        }
        return level % 5 == 0 ? MonsterConstInt.bossBonus.get() : 1;
    }

    private void calcHp(int level) {
        // level : 1 ~ 140
        // ceil(10 x (level - 1 + 1.55^(level-1)) x isBoss)
        // todo
    }

    private void calcHp2(int level) {
        // level : 141 ~ 500
        // ceil(10 x (139 + 1.55^139 x 1.145^(level-140)) x isBoss)
        // todo
    }
    @Override
    public void bindToLabel(Label label) {
        // todo
    }

    @Override
    public String toString() {
        return String.format("hp: %s, level: %d", hp.toString(), level);
    }
}