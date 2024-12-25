package monster;

public class MonsterImpl implements Monster {
    private long maxHp;
    private long hp; // current hp
    private float HP_INCREASE_RATE = 1.55F;
    private long level;

    public MonsterImpl() {
        // initial monster
        this.maxHp = 10;
        this.hp = this.maxHp;
        this.level = 1;
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
        this.level++;
    }

    @Override
    public long yieldGold() {
        return (long) (maxHp / 10 * Math.min(3, Math.pow(1.025f, Math.max(0, level-75))));
    }

    @Override
    public String toString() {
        return String.format("monster hp: %d, level: %d", hp, level);
    }
}
