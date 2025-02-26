package org.cccccc.clickerheroes.hero.heroes;

import org.cccccc.clickerheroes.hero.Hero;
import org.cccccc.clickerheroes.hero.heroes.collections.*;

enum HeroesList {
    Cid(new Cid("1e0", "1e0")),
    Treebeast(new Treebeast("5e0", "1e1")),
    Ivan(new Ivan("2.2e1", "2.5e2")),
    Brittany(new Brittany("7.4e1", "1e3")),
    Fisherman(new Fisherman("2.45e2", "4e3"));

    private final Hero hero;

    HeroesList(Hero hero) {
        this.hero = hero;
    }

    public Hero hero() {
        return hero;
    }
}
