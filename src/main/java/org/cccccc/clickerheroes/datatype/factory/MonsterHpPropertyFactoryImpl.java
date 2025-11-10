package org.cccccc.clickerheroes.datatype.factory;

import org.cccccc.clickerheroes.datatype.MonsterHpProperty;

public class MonsterHpPropertyFactoryImpl implements MonsterHpPropertyFactory {
    @Override
    public MonsterHpProperty create(String name, int value) {
        return new MonsterHpProperty(name, value);
    }
}
