package com.nsu.burym.blackjack.listeners;

import com.nsu.burym.blackjack.gameField.GameTable;
import com.nsu.burym.blackjack.gameModel.Card;
import com.nsu.burym.blackjack.gameModel.Game;
import com.nsu.burym.blackjack.gameModel.Points;
import org.jetbrains.annotations.NotNull;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ButtonDealCardsListener implements ActionListener  {
    private final GameTable gameTable;
    private final Game game;

    public ButtonDealCardsListener(@NotNull GameTable gameTable, @NotNull Game game) {
        this.gameTable = gameTable;
        this.game = game;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        game.initRound();
        ArrayList<Card> playerCards = game.getPlayerHand();
        ArrayList<Card> dealerCards = game.getDealerHand();
        ArrayList<Card> otherPlayerCards = game.getSecondHand();
        gameTable.setInitGame();
        gameTable.setCards(playerCards, dealerCards, otherPlayerCards);
        if (game.isEqualsCards() && game.getPlayerMoney() - game.getBank() >= 0) {
            gameTable.setAllowedToSplit(true);
        }
        Points points = game.getGamePoints();
        if (points.getPlayer() > 8 && points.getPlayer() < 12 && game.getPlayerMoney() - game.getBank() >= 0) {
            gameTable.setAllowedToDouble(true);
        }
        gameTable.repaint();
    }
}
