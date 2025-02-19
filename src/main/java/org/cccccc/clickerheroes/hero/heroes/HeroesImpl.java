package org.cccccc.clickerheroes.hero.heroes;

import com.google.inject.Inject;
import javafx.scene.control.Label;
import org.cccccc.clickerheroes.datatype.ExpExprProperty;
import org.cccccc.clickerheroes.datatype.ExpExprPropertyFactory;
import org.cccccc.clickerheroes.gold.Gold;
import org.cccccc.clickerheroes.monster.Monster;

public class HeroesImpl implements Heroes {
    private final ExpExprProperty allHeroesDamage;

    @Inject
    public HeroesImpl(ExpExprPropertyFactory expExprPropertyFactory) {
        this.allHeroesDamage = expExprPropertyFactory.create("allHero");
    }

    @Override
    public void levelUp(String name, Gold gold, int level) {
        // todo
        updateDamage();
    }

    @Override
    public void attack(Monster monster) {
        // todo
    }

    @Override
    public void bindToLabel(String name, Label label) {
        if (name.equals("allHero")) {
            label.textProperty().bind(allHeroesDamage.asString());
            return;
        }
        for (HeroesList hero: HeroesList.values()) {
            if (hero.name().equals(name)) {
//                todo
            }
        }
    }

    @Override
    public void updateDamage() {

        // todo
    }
}
