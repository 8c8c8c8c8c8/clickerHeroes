package hero;

import monster.Monster;

public abstract class HeroImpl implements Hero {
    protected long damage;
    protected int level;

    public HeroImpl() {
        this.level = 1;
    }

    @Override
    public void attack(Monster monster) {
        monster.beAttacked(damage);
    }

    @Override
    public void levelUp() {
        level++;
    }
}
