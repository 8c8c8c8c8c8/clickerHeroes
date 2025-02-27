package org.cccccc.clickerheroes.monster;

enum MonsterConstDouble {
    hpInc(1.55f),
    hpInc2(1.145f),
    ;

    private final double value;

    MonsterConstDouble(double value) {
        this.value = value;
    }

    double get() {
        return value;
    }
}
