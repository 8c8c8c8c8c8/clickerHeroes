package org.cccccc.clickerheroes.hero.heroes;

import com.google.inject.Inject;
import javafx.scene.control.Label;
import org.cccccc.clickerheroes.datatype.ExpExprProperty;
import org.cccccc.clickerheroes.datatype.factory.ExpExprPropertyFactory;
import org.cccccc.clickerheroes.gold.Gold;
import org.cccccc.clickerheroes.monster.Monster;
import org.cccccc.clickerheroes.utils.BindToFXML;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class HeroesImpl implements Heroes, BindToFXML {
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
        monster.beAttacked(allHeroesDamage);
    }

    @Override
    public void bind(Map<String, Object> nameSpace) {
        Label allHeroDamageLabel = (Label) nameSpace.get("allHeroDamage");
        allHeroDamageLabel.textProperty().bind(allHeroesDamage.asString());
        Arrays.stream(HeroesList.values()).forEach(hero -> {
            ((BindToFXML) hero.hero()).bind(nameSpace);
        });
    }

    @Override
    public void updateDamage() {
        allHeroesDamage.reset();
        Arrays.stream(HeroesList.values()).forEach(hero -> {
            hero.hero().addDamageTo(allHeroesDamage);
        });
    }

    @Override
    public void learnSkill(String name, int skillIndex) {
        for (HeroesList hero : HeroesList.values()) {
            if (hero.name().equals(name)) {
                hero.hero().learnSkill(skillIndex);
                break;
            }
        }
    }

    @Override
    public List<String> getAllHeroNames() {
        return Arrays.stream(HeroesList.values()).map(Enum::name).toList();
    }
}
