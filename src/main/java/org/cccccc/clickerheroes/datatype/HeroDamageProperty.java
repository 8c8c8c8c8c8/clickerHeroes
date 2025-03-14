package org.cccccc.clickerheroes.datatype;

import javafx.scene.control.Label;
import utils.BindToMultiLabels;

import java.util.Map;

public class HeroDamageProperty extends ExpExprProperty implements BindToMultiLabels {
    private final ExpExprProperty baseDamage = new ExpExprProperty("baseDamage");

    public HeroDamageProperty(String name) throws InstantiationException {
        super(name);
        throw new InstantiationException();
    }

    public HeroDamageProperty(String name, String value) {
        super(String.format("%sDamage", name), value);
        baseDamage.set(this);
        this.reset();
    }

    public HeroDamageProperty(String name, int val) throws InstantiationException {
        super(name, val);
        throw new InstantiationException();
    }

    public void levelUp(int level) {
        ExpExprProperty damageToAdd = customMultiply(baseDamage, level);
        this.customAdd(damageToAdd);
    }

    public void upgrade(double val, int currentLevel) {
        baseDamage.customMultiply(val);
        this.set(baseDamage);
        this.customMultiply(currentLevel);
    }

    @Override
    public void bind(Map<String, Label> labelMap) {
        Label damageLabel = labelMap.get("damage");
        damageLabel.textProperty().bind(this.asString());
        Label baseDamageLabel = labelMap.get("baseDamage");
        baseDamageLabel.textProperty().bind(baseDamage.asString());
    }
}
