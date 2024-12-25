package hero;

import gold.Gold;
import monster.Monster;

public interface Hero {

    void attack(Monster monster);

    void levelUp(Gold gold);
    void globalSkill();
    void localSkill();
}
