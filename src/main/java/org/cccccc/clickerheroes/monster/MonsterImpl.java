package org.cccccc.clickerheroes.monster;

import com.google.inject.Inject;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.control.Label;
import org.cccccc.clickerheroes.datatype.ExpExprProperty;
import org.cccccc.clickerheroes.datatype.MonsterHpProperty;
import org.cccccc.clickerheroes.utils.BindToFXML;

import java.util.Map;

public class MonsterImpl implements Monster, BindToFXML {
    private final MonsterHpProperty hp; // current hp
    private final IntegerProperty level;
    private final IntegerProperty maxLevel;

    @Inject
    public MonsterImpl(MonsterHpProperty monsterHpProperty) {
        // initial monster
        this.hp = monsterHpProperty;
        this.level = new SimpleIntegerProperty(1, "monsterLevel");
        this.maxLevel = new SimpleIntegerProperty(1, "monsterMaxLevel");
    }

    @Override
    public void beAttacked(ExpExprProperty damage) {
        hp.customSubtract(damage);
    }

    @Override
    public boolean isAlive() {
        return !hp.isZero();
    }

    @Override
    public void levelUp() {
        if (level.get() == maxLevel.get()) {
            maxLevel.add(1);
        }
        level.add(1);
        goToLevel(level.get());
    }

    @Override
    public ExpExprProperty dropGold() {
        if (level.get() < MonsterConstInt.levelBaseLine.get()) {
            return calculateGoldFormula();
        }
        return calculateGoldFormula2();
    }

    private ExpExprProperty calculateGoldFormula() {
        // level : 1 ~ 139
        // Gold is 1 at Zone 1 and is generally cumulatively multiplied by 1.6 every zone until Zone 140
        double goldConst = 1.6;
        ExpExprProperty monsterGold = new ExpExprProperty("monsterGold", goldConst);
        monsterGold.customPower(level.get() - 1);
        if (isBoss()) {
            monsterGold.customMultiply(MonsterConstInt.bossBonus.get());
        }
        return monsterGold;
    }

    private ExpExprProperty calculateGoldFormula2() {
        // level : 140 ~
        // 4.717e28 * 1.15^(level-140) if level is greater than 139
        double goldConst2 = 1.15;
        String goldConst3 = "4.717e28";
        ExpExprProperty monsterGold = new ExpExprProperty("monsterGold", goldConst2);
        ExpExprProperty monsterGoldConst = new ExpExprProperty("monsterGoldConst", goldConst3);
        monsterGold.customPower(level.get() - MonsterConstInt.levelBaseLine.get());
        monsterGold.customMultiply(monsterGoldConst);
        if (isBoss()) {
            monsterGold.customMultiply(MonsterConstInt.bossBonus.get());
        }
        return monsterGold;
    }

    @Override
    public void goToLevel(int level) {
        if (level > maxLevel.get()) {
            throw new IllegalArgumentException("desired level can not exceed max-level");
        }
        this.level.set(level);
        hp.calculateHp(level, isBoss());
    }

    private boolean isBoss() {
        // boss bonus : hp x 10
        // boss appear if level is multiple of 5 and more than 100
        // o.w multiple of 10
        int level = this.level.get();
        if (level < 100) {
            return level % 10 == 0;
        }
        return level % 5 == 0;
    }

    @Override
    public void bind(Map<String, Object> nameSpace) {
        hp.bind(nameSpace);
        Label monsterLevelLabel = (Label) nameSpace.get("monsterLevel");
        monsterLevelLabel.textProperty().bind(level.asString());
        Label monsterMaxLevelLabel = (Label) nameSpace.get("monsterMaxLevel");
        monsterLevelLabel.textProperty().bind(maxLevel.asString());
    }
}