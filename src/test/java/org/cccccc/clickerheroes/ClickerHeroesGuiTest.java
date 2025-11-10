package org.cccccc.clickerheroes;

import javafx.scene.layout.GridPane;
import org.cccccc.clickerheroes.javafx.gui.ClickerHeroesGuiImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ClickerHeroesGuiTest {
    @InjectMocks
    ClickerHeroesGuiImpl clickerHeroesGui;

    @Test
    public void addUpgradeImageToGridPaneTest() {
        GridPane gridPane = new GridPane();
        clickerHeroesGui.addUpgradeImageToGridPane(gridPane, "Cid");
    }
}
