package com.nsu.burym.blackjack.gameModel;

public class Card {
    private final int suit;
    private final int number;

    public Card(int suit, int number) {
        this.number = number;
        this.suit = suit;
    }

    public int getNumber() {
        return number;
    }
    public int getSuit() {
        return suit;
    }
}
