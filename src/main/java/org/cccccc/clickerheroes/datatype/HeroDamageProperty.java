package org.cccccc.clickerheroes.datatype;

import javafx.scene.control.Label;
import utils.BindToMultiLabels;

import java.util.Map;

public class HeroDamageProperty extends ExpExprProperty implements BindToMultiLabels {
    private final ExpExprProperty damage;
    public HeroDamageProperty(String name) {
        super(name);
        damage = new ExpExprProperty(damagePropertyNaming(name));
    }

    public HeroDamageProperty(String name, String value) {
        super(name, value);
        damage = new ExpExprProperty(damagePropertyNaming(name), value);
    }

    public HeroDamageProperty(String name, int val) {
        super(name, val);
        damage = new ExpExprProperty(damagePropertyNaming(name), val);
    }

    private String damagePropertyNaming(String name) {
        return name.replace("baseDamage", "Damage");
    }

    public void update(int level) {
        damage.customMultiply(level);
    }

    public void increase(double val) {
        this.customMultiply(val);
    }

    @Override
    public void bind(Map<String, Label> labelMap) {
        Label baseDamageLabel = labelMap.get("baseDamage");
        baseDamageLabel.textProperty().bind(this.asString());
        Label damageLabel = labelMap.get("damage");
        damageLabel.textProperty().bind(damage.asString());
    }
}
