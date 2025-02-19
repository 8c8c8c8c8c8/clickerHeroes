package org.cccccc.clickerheroes.datatype;

public class ExpExprPropertyFactoryImpl implements ExpExprPropertyFactory {
    @Override
    public ExpExprProperty create(String name) {
        return new ExpExprProperty(name);
    }
}
