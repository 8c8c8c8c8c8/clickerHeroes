package org.cccccc.clickerheroes.gold;

import com.google.inject.Inject;
import javafx.scene.control.Label;
import org.cccccc.clickerheroes.datatype.ExpExprProperty;
import org.cccccc.clickerheroes.monster.Monster;
import utils.BindToFXML;

import java.util.Map;

public class GoldImpl implements Gold, BindToFXML {
    private final ExpExprProperty gold;

    @Inject
    public GoldImpl(ExpExprProperty gold) {
        this.gold = gold;
    }

    @Override
    public boolean spend(ExpExprProperty cost) {
        if (isMoreThanCost(cost)) {
            gold.customSubtract(cost);
            return true;
        }
        return false;
    }

    @Override
    public void addDroppedGold(Monster monster) {
        ExpExprProperty income = monster.dropGold();
        gold.customAdd(income);
    }

    private boolean isMoreThanCost(ExpExprProperty cost) {
        return cost.isLessThan(gold);
    }

    @Override
    public void bind(Map<String, Object> nameSpace) {
        Label label = (Label) nameSpace.getOrDefault("gold", null);
        if (label == null) {
            throw new NullPointerException("gold label not exist");
        }
        label.textProperty().bind(gold.asString());
    }
}
