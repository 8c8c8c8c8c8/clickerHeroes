package org.cccccc.clickerheroes.datatype.factory;

import org.cccccc.clickerheroes.datatype.ExpExprProperty;

public class ExpExprPropertyFactoryImpl implements ExpExprPropertyFactory {
    @Override
    public ExpExprProperty create(String name) {
        return new ExpExprProperty(name);
    }

    @Override
    public ExpExprProperty create(String name, int value) {
        return new ExpExprProperty(name, value);
    }

    @Override
    public ExpExprProperty create(String name, String value) {
        return new ExpExprProperty(name, value);
    }
}
