package heroes;

import hero.Hero;
import monster.Monster;

public interface Heroes {
    void addHero(String heroName, Hero hero);
    void levelUpHero(String heroName);
    void attack(Monster monster);
}
