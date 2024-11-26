public class MonsterImpl implements Monster {
    private long MAX_HP;
    private long hp; // current hp

    public MonsterImpl() {
        // initial monster
        this.MAX_HP = 10;
        this.hp = this.MAX_HP;
    }

    public MonsterImpl(Monster monster) {
        this.MAX_HP = (long) Math.ceil(monster.getMAX_HP() * 1.55);
        this.hp = this.MAX_HP;
    }

    public long getMAX_HP() {
        return this.MAX_HP;
    }

    public void beAttacked(long damage) {
        hp -= damage;
    }

    public boolean isAlive() {
        return hp > 0;
    }

    public Monster levelUp() {
        return new MonsterImpl(this);
    }
}
