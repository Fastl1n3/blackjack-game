package com.nsu.burym.blackjack.gameModel;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class Game {
    private ArrayList<Card> playerHand;
    private ArrayList<Card> dealerHand;
    private Deck deck;
    private int bank = 0;
    private int playerMoney = 1000;
    private final Points gamePoints = new Points();
    private ArrayList<Card> otherPlayerHand;
    private boolean isSecondHand = false;
    private boolean isSplit = false;

    public void initRound() {
        deck = new Deck();
        playerHand = new ArrayList<>();
        dealerHand = new ArrayList<>();
        otherPlayerHand = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            playerHand.add(deck.getRandomCard());
        }
        dealerHand.add(deck.getRandomCard());
        gamePoints.setDealer(countPoints(dealerHand));
        gamePoints.setPlayer(countPoints(playerHand));
    }
    /* 0 1 2 3 4 5 6 7 8  9 10 11 12
       2 3 4 5 6 7 8 9 10 V D  K  T */
    public int hitCard(@NotNull ArrayList<Card> hand) {
        hand.add(deck.getRandomCard());
        int points = countPoints(hand);
        if (isSecondHand) {
            gamePoints.setSecondPlayer(points);
        }
        else {
            gamePoints.setPlayer(points);
        }
        if (points == 21) {
            return 1;
        }
        if (points > 21) {
            return -1;
        }
        return 0;
    }

    private int countPoints(@NotNull ArrayList<Card> cards) {
        int points = 0;
        for (Card card : cards) {
            if (card.getNumber() < 12 && card.getNumber() > 8) {
                points += 10;
            } else if (card.getNumber() == 12) {
                if (points + 11 > 21) {
                    points += 1;
                } else {
                    points += 11;
                }
            } else {
                points += card.getNumber() + 2;
            }
        }
        return points;
    }

    public void dealersMove() {
        int dPoints = gamePoints.getDealer();
        while (dPoints < 17) {
            dealerHand.add(deck.getRandomCard());
            dPoints = countPoints(dealerHand);
        }
        gamePoints.setDealer(dPoints);
    }

    public Winner whoWin() {
        int k = 0;
        if (isSplit) {
            if (gamePoints.getPlayer() <= 21 && (gamePoints.getDealer() < gamePoints.getPlayer() || gamePoints.getDealer() > 21)) {
                k++;
                playerMoney += bank;
            }
            if (gamePoints.getSecond() <= 21 && (gamePoints.getDealer() < gamePoints.getSecond() || gamePoints.getDealer() > 21)) {
                k++;
                playerMoney += bank;
            }
        }
        else {
            if (gamePoints.getPlayer() <= 21) {
                if (gamePoints.getDealer() < gamePoints.getPlayer() || gamePoints.getDealer() > 21) {
                    k++;
                    playerMoney += bank * 2;
                }
                if (gamePoints.getPlayer() == gamePoints.getDealer()) {
                    playerMoney += bank;
                    bank = 0;
                    return Winner.TIE;
                }
            }
        }
        bank = 0;
        if (k == 2) {
            return Winner.PLAYER_BOTH;
        }
        else if (k == 1) {
            return Winner.PLAYER_ONE;
        }
        return Winner.DEALER;
    }

    public void addBet(int bet) {
        if (playerMoney - bet >= 0) {
            bank += bet;
            playerMoney -= bet;
        }
    }

    public void doubleBet() {
        playerMoney -= bank;
        bank *= 2;
        hitCard(playerHand);
    }

    public void initSplit() {
        isSplit = true;
        otherPlayerHand.add(playerHand.remove(1));
        gamePoints.setPlayer(countPoints(playerHand));
        gamePoints.setSecondPlayer(countPoints(otherPlayerHand));
        playerMoney -= bank;
        bank *= 2;
    }

    public void endGame() {
        gamePoints.resetPoints();
        isSplit = false;
        isSecondHand = false;
    }

    public ArrayList<Card> getPlayerHand() {
        return playerHand;
    }

    public ArrayList<Card> getDealerHand() {
        return dealerHand;
    }

    public ArrayList<Card> getSecondHand() {
        return otherPlayerHand;
    }

    public int getBank() {
        return bank;
    }

    public int getPlayerMoney() {
        return playerMoney;
    }

    public Points getGamePoints() {
        return gamePoints;
    }

    public boolean getIsSplit() {
        return isSplit;
    }

    public boolean getIsSecondHand() {
        return isSecondHand;
    }

    public ArrayList<Card> getHand() {
        if (!isSecondHand) {
            return playerHand;
        }
        else {
            return otherPlayerHand;
        }
    }

    public void setIsSecondHand() {
        isSecondHand = true;
    }

    public boolean isEqualsCards() {
        if (playerHand.get(0).getNumber() < 12 && playerHand.get(0).getNumber() > 8 && playerHand.get(1).getNumber() < 12 && playerHand.get(1).getNumber() > 8) {
            return true;
        }
        return playerHand.get(0).getNumber() == playerHand.get(1).getNumber();
    }
}
