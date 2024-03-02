package com.nsu.burym.blackjack.listeners;

import com.nsu.burym.blackjack.gameField.GameTable;
import com.nsu.burym.blackjack.gameModel.Game;
import com.nsu.burym.blackjack.gameModel.Winner;
import org.jetbrains.annotations.NotNull;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonHitListener implements ActionListener {
    private final GameTable gameTable;
    private final Game game;

    public ButtonHitListener(@NotNull GameTable gameTable, @NotNull Game game) {
        this.gameTable = gameTable;
        this.game = game;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int isEnd = game.hitCard(game.getHand());
        if (isEnd != 0) {
            if (game.getIsSplit() && !game.getIsSecondHand()) {
                game.setIsSecondHand();
                gameTable.setIsSecondHand();
            }
            else {
                if (isEnd != -1 && !game.getIsSecondHand()) {
                    gameTable.setStand();
                    game.dealersMove();
                }
                Winner res = game.whoWin();
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
        }
        gameTable.setAllowedToDouble(false);
        gameTable.repaint();
    }
}
