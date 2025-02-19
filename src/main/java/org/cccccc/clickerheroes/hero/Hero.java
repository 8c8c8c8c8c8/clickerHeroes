package org.cccccc.clickerheroes.hero;

import javafx.scene.control.Label;
import org.cccccc.clickerheroes.gold.Gold;

public interface Hero {
    void levelUp(Gold gold, int level);
    void learnSkill(int skillIndex);
    boolean isActive();
    long getCost(int level);
    void bindToLabel(Label label);
}
