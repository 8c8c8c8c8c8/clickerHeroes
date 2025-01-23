package org.cccccc.clickerheroes.hero.heroes;

import org.cccccc.clickerheroes.hero.Hero;
import org.cccccc.clickerheroes.hero.heroes.collections.*;
import org.cccccc.clickerheroes.monster.Monster;

public enum Heroes {
    Cid(new Cid(1, 1)),
    Treebeast(new Treebeast(5, 10)),
    Ivan(new Ivan(22, 250)),
    Brittany(new Brittany(74, 1000)),
    Fisherman(new Fisherman(245, 4000));
    private final Hero hero;

    Heroes(Hero hero) {
        this.hero = hero;
    }
    public void attack(Monster monster) {
        hero.attack(monster);
    }

    public boolean isActive() {
        return hero.isActive();
    }
    @Override
    public String toString() {
        return hero.toString();
    }
}
