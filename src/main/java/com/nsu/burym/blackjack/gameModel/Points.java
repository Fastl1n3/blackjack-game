package com.nsu.burym.blackjack.gameModel;

public class Points {
    private int player = 0;
    private int dealer = 0;
    private int secondPlayer = 0;

    public void resetPoints() {
        player = 0;
        dealer = 0;
        secondPlayer = 0;
    }

    public void setDealer(int dealer) {
        this.dealer = dealer;
    }

    public void setPlayer(int player) {
        this.player = player;
    }

    public void setSecondPlayer(int secondPlayer) {
        this.secondPlayer = secondPlayer;
    }

    public int getSecond() {
        return secondPlayer;
    }

    public int getDealer() {
        return dealer;
    }

    public int getPlayer() {
        return player;
    }
}
