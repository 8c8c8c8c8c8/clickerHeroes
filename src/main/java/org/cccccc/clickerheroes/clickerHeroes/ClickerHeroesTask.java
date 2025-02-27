package org.cccccc.clickerheroes.clickerHeroes;

import javafx.concurrent.Task;

public class ClickerHeroesTask extends Task<Void> {
    private final ClickerHeroes clickerHeroes;

    public ClickerHeroesTask(ClickerHeroes clickerHeroes) {
        this.clickerHeroes = clickerHeroes;
    }

    private String concatMessages() {
        return String.format("%s$%s",
                clickerHeroes.getMonsterStatus(),
                clickerHeroes.getGoldStatus());
    }

    @Override
    protected Void call() throws Exception {
        int oneSecond = 1000; // run-cycle per one second
        clickerHeroes.start();
        while (clickerHeroes.isRunning()) {
            updateMessage(concatMessages());
            clickerHeroes.runCycle();
            try {
                Thread.sleep(oneSecond);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }
}
