package org.cccccc.clickerheroes.hero.heroes;

import org.cccccc.clickerheroes.hero.Hero;
import org.cccccc.clickerheroes.hero.heroes.collections.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

enum HeroesList {
    Cid(new Cid("1e0", "1e0"), 4),
    Treebeast(new Treebeast("5e0", "1e1"), 4),
    Ivan(new Ivan("2.2e1", "2.5e2"), 4),
    Brittany(new Brittany("7.4e1", "1e3"), 4),
    Fisherman(new Fisherman("2.45e2", "4e3"), 4);

    private final Hero hero;
    private final int numOfUpgrade;

    HeroesList(Hero hero, int numOfUpgrade) {
        this.hero = hero;
        this.numOfUpgrade = numOfUpgrade;
    }

    public Hero hero() {
        return hero;
    }

    public String getHeroImageAddress() {
        return String.format("%s.png", name());
    }

    public List<String> getHeroUpgradeImageAddress() {
        return IntStream.range(0, numOfUpgrade)
                .mapToObj(i -> String.format("%s/%d.jpg", name().toLowerCase(), i))
                .collect(Collectors.toList());
    }
}
