package org.cccccc.clickerheroes.datatype;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import org.cccccc.clickerheroes.monster.MonsterConstDouble;
import org.cccccc.clickerheroes.monster.MonsterConstInt;
import org.cccccc.clickerheroes.utils.BindToFXML;

import java.util.Map;

public class MonsterHpProperty extends ExpExprProperty implements BindToFXML {
    // starting 100%
    private final DoubleProperty hpRatio = new SimpleDoubleProperty(1.0, "hpRatio");
    private final ExpExprProperty maxHp = new ExpExprProperty("maxHp", MonsterConstInt.baseHp.get());
    private ExpExprProperty hpCache = null;

    public MonsterHpProperty() {
        super("hp", MonsterConstInt.baseHp.get());
    }

    public MonsterHpProperty(String name) {
        super(name);
    }

    public MonsterHpProperty(String name, String value) {
        super(name, value);
    }

    public MonsterHpProperty(String name, int val) {
        super(name, val);
    }

    private double calculateHpRatio() {
        ExpExprProperty dividedResult = ExpExprProperty.customDivide(this, this.maxHp);
        double result = dividedResult.real * dividedResult.exp;
        return roundDown(result);
    }

    private double roundDown(double val) {
        // round down val to two decimal places
        double twoDecimal = 1e2;
        return Math.floor(val * twoDecimal) / twoDecimal;
    }

    public void calculateHp(int level, boolean isBoss) {
        if (level <= MonsterConstInt.levelBaseLine.get()) {
            calculateHp1(level, isBoss);
            return;
        }
        calculateHp2(level, isBoss);
    }

    @Override
    public void customSubtract(ExpExprProperty obj) {
        super.customSubtract(obj);
        double hpRatio = calculateHpRatio();
        assert hpRatio <= 1.0: "hp ratio over 1";
        this.hpRatio.set(hpRatio);
    }

    private void calculateHp1(int level, boolean isBoss) {
        // level : 1 ~ 140
        // ceil(10 x (level - 1 + 1.55^(level - 1)) x [isBoss x 10])
        this.reset();
        this.real = MonsterConstDouble.hpInc.get();
        this.customPower(level - 1);
        this.add(level - 1);
        this.customMultiply(MonsterConstInt.baseHp.get());
        if (isBoss) {
            this.customMultiply(MonsterConstInt.bossBonus.get());
        }
        maxHp.set(this);
    }

    private void calculateHp2(int level, boolean isBoss) {
        // level : 141 ~ 500
        // ceil(10 x (139 + 1.55^139 x 1.145^(level-140)) x [isBoss x 10])
        this.reset();
        if (this.hpCache == null) {
            this.hpCache = new ExpExprProperty("hpCache", MonsterConstDouble.hpInc.get());
            this.hpCache.customPower(MonsterConstInt.levelBaseLine.get() - 1);
        }
        this.real = MonsterConstDouble.hpInc2.get();
        this.customPower(level - MonsterConstInt.levelBaseLine.get());
        this.customMultiply(hpCache);
        this.customAdd(MonsterConstInt.levelBaseLine.get() - 1);
        this.customMultiply(MonsterConstInt.baseHp.get());
        if (isBoss) {
            this.customMultiply(MonsterConstInt.bossBonus.get());
        }
        maxHp.set(this);
    }

    @Override
    public void bind(Map<String, Object> nameSpace) {
        ProgressBar hpRatioBar = (ProgressBar) nameSpace.get("hpRatio");
        hpRatioBar.progressProperty().bind(hpRatio);
        Label currentHpLabel = (Label) nameSpace.get("hp");
        currentHpLabel.textProperty().bind(this.asString());
    }
}
