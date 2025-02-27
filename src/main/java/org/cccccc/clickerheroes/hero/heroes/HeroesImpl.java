package org.cccccc.clickerheroes.hero.heroes;

import com.google.inject.Inject;
import javafx.scene.control.Label;
import org.cccccc.clickerheroes.datatype.ExpExprProperty;
import org.cccccc.clickerheroes.datatype.ExpExprPropertyFactory;
import org.cccccc.clickerheroes.gold.Gold;
import org.cccccc.clickerheroes.monster.Monster;
import utils.BindToLabel;

import java.util.Arrays;

public class HeroesImpl implements Heroes, BindToLabel {
    private final ExpExprProperty allHeroesDamage;

    @Inject
    public HeroesImpl(ExpExprPropertyFactory expExprPropertyFactory) {
        this.allHeroesDamage = expExprPropertyFactory.create("allHeroesDamage");
    }

    @Override
    public void levelUp(String name, Gold gold, int level) {
        for (HeroesList hero : HeroesList.values()) {
            if (hero.name().equals(name)) {
                hero.hero().levelUp(gold, level);
                break;
            }
        }
        updateDamage();
    }

    @Override
    public void attack(Monster monster) {
        // todo
    }

    @Override
    public void bindToLabel(Label label) {
        // todo
    }

    @Override
    public void updateDamage() {
        allHeroesDamage.reset();
        Arrays.stream(HeroesList.values()).forEach(hero -> {
            // todo
        });
    }
}
