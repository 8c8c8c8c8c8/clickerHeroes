package org.cccccc.clickerheroes.hero.heroes;

import org.cccccc.clickerheroes.hero.Hero;
import org.cccccc.clickerheroes.hero.heroes.collections.*;
import org.cccccc.clickerheroes.monster.Monster;

import java.util.Arrays;
import java.util.List;

public enum Heroes {
    Cid(new Cid(1, 1), 0),
    Treebeast(new Treebeast(5, 10), 1),
    Ivan(new Ivan(22, 250), 2),
    Brittany(new Brittany(74, 1000), 3),
    Fisherman(new Fisherman(245, 4000), 4);
    private final Hero hero;
    private final int index;

    Heroes(Hero hero, int index) {
        this.hero = hero;
        this.index = index;
    }

    public boolean isActive() {
        return hero.isActive();
    }

    @Override
    public String toString() {
        return hero.toString();
    }

    public static void attack(Monster monster) {
        Arrays.stream(Heroes.values()).forEach(hero -> {
            hero.hero.attack(monster);
        });
    }

    public static String getHeroStatus() {
        List<String> activeHeroList = Arrays.stream(Heroes.values())
                .filter(Heroes::isActive)
                .map(hero -> {
                    return String.format("index: %d, name: %s, %s", hero.index, hero.name(), hero.hero);
                })
                .toList();
        return String.join("\n", activeHeroList);
    }
}
