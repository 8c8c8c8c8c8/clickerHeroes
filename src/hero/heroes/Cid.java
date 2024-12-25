package hero.heroes;

import hero.AbstractHero;

public class Cid extends AbstractHero {
    public Cid(long damage, long cost) {
        super(damage, cost);
        this.isActive = true; // start hero
    }

    @Override
    public void globalSkill() {

    }

    @Override
    public void localSkill() {

    }
}
