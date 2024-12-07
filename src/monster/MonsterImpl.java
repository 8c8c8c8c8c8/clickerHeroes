package monster;

public class MonsterImpl implements Monster {
    private long maxHp;
    private long hp; // current hp
    private float HpIncreaseRate = 1.55F;

    public MonsterImpl() {
        // initial monster
        this.maxHp = 10;
        this.hp = this.maxHp;
    }

    private long increaseHp() {
        return (long) Math.ceil(maxHp * HpIncreaseRate);
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
    }
}
