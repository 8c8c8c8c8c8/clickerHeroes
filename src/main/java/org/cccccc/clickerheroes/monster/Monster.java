package org.cccccc.clickerheroes.monster;

public interface Monster {
    int getHpRatio();
    void beAttacked(long damage);
    boolean isAlive();
    void levelUp();
    long yieldGold();
    void goToLevel(long level);
}
