package com.nsu.burym.blackjack.listeners;

import com.nsu.burym.blackjack.gameField.GameTable;
import com.nsu.burym.blackjack.gameModel.Game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonSplitListener implements ActionListener {
    private final GameTable gameTable;
    private final Game game;

    public ButtonSplitListener(GameTable gameTable, Game game) {
        this.gameTable = gameTable;
        this.game = game;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        game.initSplit();
        int bank = game.getBank();
        gameTable.setAllowedToSplit(false);
        gameTable.doubleChips();
        gameTable.setBank(bank);
        gameTable.setPlayerMoney(game.getPlayerMoney());
        gameTable.setSplit();
        gameTable.repaint();
    }
}
