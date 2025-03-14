package org.cccccc.clickerheroes.hero;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.control.Label;
import org.cccccc.clickerheroes.datatype.ExpExprProperty;
import org.cccccc.clickerheroes.datatype.HeroCostProperty;
import org.cccccc.clickerheroes.datatype.HeroDamageProperty;
import org.cccccc.clickerheroes.gold.Gold;
import utils.BindToMultiLabels;

import java.util.Map;

public abstract class AbstractHero implements Hero, BindToMultiLabels {
    private final IntegerProperty level;
    private final HeroCostProperty cost;
    private final HeroDamageProperty damage;

    public AbstractHero(String damage, String cost) {
        String heroName = getClass().getSimpleName();
        this.damage = new HeroDamageProperty(heroName, damage);
        this.cost = new HeroCostProperty(heroName, cost);
        this.level = new SimpleIntegerProperty(0, String.format("%sLevel", heroName));
        // starting hero
        if (heroName.contains("Cid")) {
            this.level.set(1);
        }
    }

    @Override
    public void levelUp(Gold gold, int level) {
        ExpExprProperty heroCost = getCost(level);
        if (gold.beSpent(heroCost)) {
            this.level.add(level);
            this.damage.levelUp(level);
            this.cost.levelUp(level);
        }
    }

    @Override
    public boolean isActive() {
        return level.get() > 0;
    }

    @Override
    public ExpExprProperty getCost(int level) {
        int currentLevel = this.level.get();
        int targetLevel = currentLevel + level;
        return cost.getCost(currentLevel, targetLevel);
    }

    @Override
    public void bind(Map<String, Label> labelMap) {
        damage.bind(labelMap);
        Label costLabel = labelMap.get("cost");
        costLabel.textProperty().bind(cost.asString());
        Label levelLabel = labelMap.get("level");
        levelLabel.textProperty().bind(level.asString());
    }

    @Override
    public void addDamageTo(ExpExprProperty allDamage) {
        allDamage.customAdd(damage);
    }
}