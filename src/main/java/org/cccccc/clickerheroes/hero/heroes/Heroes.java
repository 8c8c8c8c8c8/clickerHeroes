package org.cccccc.clickerheroes.hero.heroes;

import javafx.scene.control.Label;
import org.cccccc.clickerheroes.gold.Gold;
import org.cccccc.clickerheroes.monster.Monster;

public interface Heroes {
    void levelUp(String name, Gold gold, int level);
    void attack(Monster monster);
    void bindToLabel(String name, Label label);
    void updateDamage();
}
