package com.nsu.burym.blackjack;

import com.nsu.burym.blackjack.gameField.GameTable;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        double displayCoeffX = (double) gd.getDisplayMode().getWidth() / 1920;
        double displayCoeffY = (double) gd.getDisplayMode().getHeight() / 1080;
        JFrame frame = new JFrame("BlackJack");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize((int) (1000 * displayCoeffX), (int) (700 * displayCoeffY));
        frame.setResizable(false);
        frame.setLocation(300,100);
        frame.add(new GameTable(frame, displayCoeffX, displayCoeffY));
        frame.setVisible(true);
    }
}
