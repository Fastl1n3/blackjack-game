package com.nsu.burym.blackjack.listeners;

import com.nsu.burym.blackjack.gameField.GameTable;
import com.nsu.burym.blackjack.gameModel.Game;
import com.nsu.burym.blackjack.gameModel.Winner;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonStandListener implements ActionListener {
    private final GameTable gameTable;
    private final Game game;

    public ButtonStandListener(GameTable gameTable, Game game) {
        this.gameTable = gameTable;
        this.game = game;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (game.getIsSplit() && !game.getIsSecondHand()) {
            game.setIsSecondHand();
            gameTable.setIsSecondHand();
        }
        else {
            game.dealersMove();
            Winner res = game.whoWin();
            gameTable.setStand();
            if (res == Winner.PLAYER_BOTH) {
                gameTable.setWin();
            }
            if (res == Winner.PLAYER_ONE) {
                if (game.getIsSplit()) {
                    gameTable.setWinOneHand();
                }
                else {
                    gameTable.setWin();
                }
            }
            if (res == Winner.TIE) {
                gameTable.setTie();
            }
            if (res == Winner.DEALER) {
                gameTable.setLose();
            }
        }
        gameTable.repaint();
    }
}
