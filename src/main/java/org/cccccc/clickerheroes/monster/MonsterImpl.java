package org.cccccc.clickerheroes.monster;

public class MonsterImpl implements Monster {
    private final long START_HP = 10;
    private long maxHp;
    private long hp; // current hp
    private float HP_INCREASE_RATE = 1.55F;
    private long level;
    private long maxLevel;

    public MonsterImpl() {
        // initial org.cccccc.clickerheroes.monster
        this.maxHp = 10;
        this.hp = this.maxHp;
        this.level = 1;
        this.maxLevel = 1;
    }

    private long increaseHp() {
        return (long) Math.ceil(maxHp * HP_INCREASE_RATE);
    }

    @Override
    public int getHpRatio() {
        return (int) Math.ceil((float) hp / maxHp * 100);
    }

    @Override
    public void beAttacked(long damage) {
        hp -= damage;
    }

    @Override
    public boolean isAlive() {
        return hp > 0;
    }

    @Override
    public void levelUp() {
        this.maxHp = increaseHp();
        this.hp = this.maxHp;
        this.maxLevel = ++this.level;
    }

    @Override
    public long yieldGold() {
        return (long) (maxHp / 10 * Math.min(3, Math.pow(1.025f, Math.max(0, level-75))));
    }

    @Override
    public String toString() {
        return String.format("org.cccccc.clickerheroes.monster hp: %d, level: %d", hp, level);
    }

    @Override
    public void goToLevel(long level) {
        this.maxHp = (long) Math.ceil(START_HP * Math.pow(1.07, level-1));
    }
}

enum MonsterConstant {

    HP_INC_RATE(1.07f);
    private final float VALUE1;
    private final long VALUE2;
    MonsterConstant(float value) {
        this.VALUE1 = value;
        this.VALUE2 = Integer.parseInt(null);
    }

    MonsterConstant(long value) {
        this.VALUE1 = Integer.parseInt(null);
        this.VALUE2 = value;
    }

    public static void main(String[] args) {

    }
}
