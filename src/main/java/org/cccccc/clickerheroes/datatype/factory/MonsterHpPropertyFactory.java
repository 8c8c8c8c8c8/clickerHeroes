package org.cccccc.clickerheroes.datatype.factory;

import org.cccccc.clickerheroes.datatype.MonsterHpProperty;

public interface MonsterHpPropertyFactory {
    MonsterHpProperty create(String name, int value);
}
