package org.cccccc.clickerheroes.datatype;

import javafx.scene.control.Label;
import org.cccccc.clickerheroes.utils.BindToFXML;

import java.util.Map;

public class HeroDamageProperty extends ExpExprProperty implements BindToFXML {
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
    public void bind(Map<String, Object> nameSpace) {
        Label damageLabel = (Label) nameSpace.get(getName());
        damageLabel.textProperty().bind(this.asString());
//        Label baseDamageLabel = (Label) nameSpace.get("baseDamage");
//        baseDamageLabel.textProperty().bind(baseDamage.asString());
    }
}
