package com.nsu.burym.blackjack.gameModel;

import java.util.ArrayList;
import java.util.Random;
/* 0 1 2 3 4 5 6 7 8  9 10 11 12
   2 3 4 5 6 7 8 9 10 V D  K  T
 */
public class Deck {
    private int actual_num = 52;
    private final ArrayList<Card> listCards = new ArrayList<>();

    public Deck() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 13; j++) {
                listCards.add(new Card(i, j));
            }
        }
    }

    public Card getRandomCard() {
        int i = new Random().nextInt(actual_num);
        actual_num--;
        return listCards.remove(i);
    }
}
