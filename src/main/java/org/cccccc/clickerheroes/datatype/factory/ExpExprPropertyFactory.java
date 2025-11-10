package org.cccccc.clickerheroes.datatype.factory;

import org.cccccc.clickerheroes.datatype.ExpExprProperty;

public interface ExpExprPropertyFactory {
    ExpExprProperty create(String name);

    ExpExprProperty create(String name, int value);

    ExpExprProperty create(String name, String value);

}
