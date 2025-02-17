package org.cccccc.clickerheroes.hero.heroes;

import org.cccccc.clickerheroes.hero.Hero;
import org.cccccc.clickerheroes.hero.heroes.collections.*;

enum HeroesList {
    Cid(new Cid(1, 1)),
    Treebeast(new Treebeast(5, 10)),
    Ivan(new Ivan(22, 250)),
    Brittany(new Brittany(74, 1000)),
    Fisherman(new Fisherman(245, 4000));

    private final Hero hero;

    HeroesList(Hero hero) {
        this.hero = hero;
    }

    public Hero hero() {
        return hero;
    }
}
