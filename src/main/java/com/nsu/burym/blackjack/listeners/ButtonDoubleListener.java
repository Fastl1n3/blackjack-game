package com.nsu.burym.blackjack.listeners;

import com.nsu.burym.blackjack.gameField.GameTable;
import com.nsu.burym.blackjack.gameModel.Game;
import com.nsu.burym.blackjack.gameModel.Winner;
import org.jetbrains.annotations.NotNull;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonDoubleListener implements ActionListener {
    private final GameTable gameTable;
    private final Game game;

    public ButtonDoubleListener(@NotNull GameTable gameTable, @NotNull Game game) {
        this.gameTable = gameTable;
        this.game = game;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        game.doubleBet();
        int bank = game.getBank();
        gameTable.doubleChips();
        gameTable.setBank(bank);
        gameTable.setPlayerMoney(game.getPlayerMoney());
        game.dealersMove();
        gameTable.setStand();
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
        gameTable.repaint();
    }
}
