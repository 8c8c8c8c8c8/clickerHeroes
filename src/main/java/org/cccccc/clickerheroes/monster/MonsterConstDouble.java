package org.cccccc.clickerheroes.monster;

public enum MonsterConstDouble {
    hpInc(1.55),
    hpInc2(1.145),
    ;

    private final double value;

    MonsterConstDouble(double value) {
        this.value = value;
    }

    public double get() {
        return value;
    }
}
