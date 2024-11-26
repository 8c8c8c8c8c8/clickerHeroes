public abstract class Hero {
    protected long damage;
    protected int level;

    public Hero() {
        this.level = 1;
    }
    public void attack(Monster monster) {
        monster.beAttacked(damage);
    }

    public void levelUp() {
        level++;
    }
}
