package org.cccccc.clickerheroes.clickerHeroes;

import javafx.concurrent.Task;

public class ClickerHeroesTask extends Task<Void> {
    private final int ONE_SECOND = 1000; // run-cycle per one second
    private final ClickerHeroes clickerHeroes;

    public ClickerHeroesTask(ClickerHeroes clickerHeroes) {
        this.clickerHeroes = clickerHeroes;
    }

    @Override
    protected Void call() throws Exception {
        clickerHeroes.start();
        while (clickerHeroes.isRunning()) {
            updateMessage(clickerHeroes.getMonsterStatus());
            clickerHeroes.runCycle();
            try {
                Thread.sleep(ONE_SECOND);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }
}
