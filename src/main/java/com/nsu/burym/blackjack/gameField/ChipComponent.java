package com.nsu.burym.blackjack.gameField;

import javax.swing.*;

public class ChipComponent extends JLabel {
    int val;

    public ChipComponent(int x, int y, Integer val, double displayCoeffX, double displayCoeffY) {
        this.val = val;
        setBounds(x, y, (int) (30 * displayCoeffX), (int) (35 * displayCoeffY));
        setOpaque(false);
        String path = "pictures/chips" + val + ".png";
        setIcon(new ImageIcon(path));
    }

    public int getVal() {
        return val;
    }
}
