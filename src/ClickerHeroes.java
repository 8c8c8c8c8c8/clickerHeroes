import gold.Gold;
import gold.GoldImpl;
import hero.heroes.Heroes;
import monster.Monster;
import monster.MonsterImpl;

public class ClickerHeroes {
    private final int ONE_SECOND = 1000;
    private final Monster monster;
    private final Gold gold;

    public ClickerHeroes() {
        this.monster = new MonsterImpl();
        this.gold = new GoldImpl();
    }

    private void printHeroesStatus() {
        for (Heroes hero : Heroes.values()) {
            System.out.println(hero);
        }
    }

    private void printGoldStatus() {
        System.out.println(gold);
    }

    private void printMonsterStatus() {
        System.out.println(monster);
    }

    public void run() throws InterruptedException {
        while (true) {
            printHeroesStatus();
            printMonsterStatus();
            printGoldStatus();
            for (Heroes hero : Heroes.values()) {
                hero.attack(monster);
            }
            if (!monster.isAlive()) {
                gold.earnGold(monster);
                monster.levelUp();
            }

            Thread.sleep(ONE_SECOND);
        }
    }
}
