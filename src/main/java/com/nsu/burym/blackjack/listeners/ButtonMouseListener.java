package com.nsu.burym.blackjack.listeners;

import com.nsu.burym.blackjack.gameField.GameTable;
import com.nsu.burym.blackjack.gameField.ChipComponent;
import com.nsu.burym.blackjack.gameModel.Game;
import org.jetbrains.annotations.NotNull;

import javax.swing.event.MouseInputAdapter;
import java.awt.event.MouseEvent;

import static com.nsu.burym.blackjack.gameField.GameTable.BET_AREA_X;
import static com.nsu.burym.blackjack.gameField.GameTable.BET_AREA_Y;

public class ButtonMouseListener extends MouseInputAdapter {
    private final ChipComponent listenObj;
    private final Game game;
    private final GameTable gameTable;
    private int mouseX = 0;
    private int mouseY = 0;
    private int compX = 0;
    private int compY = 0;
    private final double displayCoeffX;
    private final double displayCoeffY;
    private boolean isBetArea = false;

    public ButtonMouseListener(@NotNull GameTable gameTable, @NotNull Game game, @NotNull ChipComponent listenObj, double displayCoeffX, double displayCoeffY) {
        this.gameTable = gameTable;
        this.game = game;
        this.listenObj = listenObj;
        this.displayCoeffX = displayCoeffX;
        this.displayCoeffY = displayCoeffY;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        mouseX = e.getXOnScreen();
        mouseY = e.getYOnScreen();
        compX = listenObj.getX();
        compY = listenObj.getY();
        isBetArea = listenObj.getX() > BET_AREA_X && listenObj.getX() < BET_AREA_X + 130 * displayCoeffX && listenObj.getY() > BET_AREA_Y && listenObj.getY() < BET_AREA_Y + 70 * displayCoeffY;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (!(listenObj.getX() > BET_AREA_X && listenObj.getX() < BET_AREA_X + 130 * displayCoeffX && listenObj.getY() > BET_AREA_Y && listenObj.getY() < BET_AREA_Y + 60 * displayCoeffY) || gameTable.isInitGame()) {
            listenObj.setLocation(compX, compY);
        }
        else if (!isBetArea) {
            makeBet();
        }
    }

    @Override
    public void mouseDragged (MouseEvent e){
        int deltaX = e.getXOnScreen() - mouseX;
        int deltaY = e.getYOnScreen() - mouseY;
        listenObj.setLocation(compX + deltaX, compY + deltaY);
    }

    private void makeBet() {
        game.addBet(listenObj.getVal());
        int bank = game.getBank();
        gameTable.setBank(bank);
        gameTable.setPlayerMoney(game.getPlayerMoney());
        gameTable.repaint();
    }

}
