package org.cccccc.clickerheroes.monster;

enum MonsterConstInt {
    baseHp(10),
    levelBaseLine(140),
    bossBonus(10),
    ;

    private final int value;

    MonsterConstInt(int value) {
        this.value = value;
    }

    int get() {
        return value;
    }
}