package org.cccccc.clickerheroes.di;

import com.google.inject.AbstractModule;
import org.cccccc.clickerheroes.clickerHeroes.ClickerHeroes;
import org.cccccc.clickerheroes.clickerHeroes.ClickerHeroesImpl;
import org.cccccc.clickerheroes.gold.Gold;
import org.cccccc.clickerheroes.gold.GoldImpl;
import org.cccccc.clickerheroes.monster.Monster;
import org.cccccc.clickerheroes.monster.MonsterImpl;

public class DIModule extends AbstractModule {
    // dependency inject class
    @Override
    protected void configure() {
        bind(ClickerHeroes.class).to(ClickerHeroesImpl.class);
        bind(Gold.class).to(GoldImpl.class);
        bind(Monster.class).to(MonsterImpl.class);
    }
}
