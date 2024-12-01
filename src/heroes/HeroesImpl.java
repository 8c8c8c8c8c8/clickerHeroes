package heroes;

import hero.Hero;
import java.util.LinkedHashMap;
import java.util.Map;
import monster.Monster;

public class HeroesImpl implements Heroes {
    private Map<String, Hero> heroesMap;

    public HeroesImpl() {
        this.heroesMap = new LinkedHashMap<>();
    }

    private Hero getHero(String heroName) {
        return heroesMap.get(heroName);
    }

    @Override
    public void addHero(String heroName, Hero hero) {
        heroesMap.put(heroName, hero);
    }

    @Override
    public void levelUpHero(String heroName) {
        Hero hero = getHero(heroName);
        hero.levelUp();
    }

    @Override
    public void attack(Monster monster) {
        Hero hero;
        for (String heroName : heroesMap.keySet()) {
            hero = getHero(heroName);
            hero.attack(monster);
            if (!monster.isAlive()) {
                return;
            }
        }
    }
}
