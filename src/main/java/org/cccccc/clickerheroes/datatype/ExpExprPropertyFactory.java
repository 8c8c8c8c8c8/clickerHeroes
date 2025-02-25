package org.cccccc.clickerheroes.datatype;

public interface ExpExprPropertyFactory {
    ExpExprProperty create(String name);
    ExpExprProperty create(String name, int value);
    ExpExprProperty create(String name, String value);
}
