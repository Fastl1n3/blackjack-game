package com.nsu.burym.blackjack.listeners;

import com.nsu.burym.blackjack.gameField.GameTable;
import com.nsu.burym.blackjack.gameModel.Game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TimerListener implements ActionListener {
    private final GameTable gameTable;
    private final Game game;

    public TimerListener(GameTable gameTable, Game game) {
        this.gameTable = gameTable;
        this.game = game;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        game.endGame();
        int a = game.getPlayerMoney();
        gameTable.resetChips();
        gameTable.resetFlags();
        gameTable.setPlayerMoney(a);
        gameTable.repaint();
    }
}
