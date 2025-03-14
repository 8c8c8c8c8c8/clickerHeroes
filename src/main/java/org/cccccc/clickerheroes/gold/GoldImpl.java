package org.cccccc.clickerheroes.gold;

import com.google.inject.Inject;
import javafx.scene.control.Label;
import org.cccccc.clickerheroes.datatype.ExpExprProperty;
import org.cccccc.clickerheroes.monster.Monster;
import utils.BindToMultiLabels;

import java.util.Map;

public class GoldImpl implements Gold, BindToMultiLabels {
    private final ExpExprProperty gold;

    @Inject
    public GoldImpl(ExpExprProperty gold) {
        this.gold = gold;
    }

    @Override
    public boolean beSpent(ExpExprProperty cost) {
        if (isMoreThanCost(cost)) {
            gold.customSubtract(cost);
            return true;
        }
        return false;
    }

    @Override
    public void beEarned(Monster monster) {
        ExpExprProperty income = monster.yieldGold();
        gold.customAdd(income);
    }

    private boolean isMoreThanCost(ExpExprProperty cost) {
        return cost.isLessThan(gold);
    }

    @Override
    public void bind(Map<String, Label> labelMap) {
        Label label = labelMap.getOrDefault("gold", null);
        if (label == null) {
            throw new NullPointerException("gold label not exist");
        }
        label.textProperty().bind(gold.asString());
    }

    @Override
    public String toString() {
        return gold.toString();
    }
}
