package hero.heroes;

import hero.Hero;
import monster.Monster;

public enum Heroes {
    Cid(new Cid(1, 1)),
    Treebeast(new Treebeast(5, 10));
    private final Hero hero;

    Heroes(Hero hero) {
        this.hero = hero;
    }
    public void attack(Monster monster) {
        hero.attack(monster);
    }

    @Override
    public String toString() {
        return hero.toString();
    }
}
