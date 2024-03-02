package com.nsu.burym.blackjack.gameField;

import com.nsu.burym.blackjack.gameModel.*;
import com.nsu.burym.blackjack.listeners.*;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GameTable extends JPanel {
    private final JFrame frame;
    private final Game game = new Game();
    private final Image[][] cardsImgs = new Image[4][13];
    public static int BET_AREA_X;
    public static int BET_AREA_Y;
    private final Image tableImg = new ImageIcon("pictures/table.jpg").getImage();
    private final Image shirtImg = new ImageIcon("pictures/shirt.png").getImage();
    private final Image arrowImg = new ImageIcon("pictures/arrow.png").getImage();
    private int TABLE_X;
    private int TABLE_Y;
    private int PLAYER_MONEY_X;
    private int PLAYER_MONEY_Y;
    private int PLAYER_BET_X;
    private int PLAYER_BET_Y;
    private int PLAYER_POINTS_X;
    private int PLAYER_POINTS_Y;
    private int DEALER_POINTS_X;
    private int DEALER_POINTS_Y;
    private int POINTS_SIZE;
    private int BUTTON_WIDTH;
    private int BUTTON_HEIGHT;
    private int BUTTON_1STR_X;
    private int BUTTON_1STR_Y;
    private int PLAYER_CARD_X;
    private int PLAYER_CARD_Y;
    private int DEALER_CARD_X;
    private int DEALER_CARD_Y;
    private int CARD_WIDTH;
    private int CARD_HEIGHT;
    private int ARROW_X;
    private int ARROW_Y;
    private int ARROW_WIDTH;
    private int ARROW_HEIGHT;
    private int PLAYER_CHIP_X;
    private int PLAYER_CHIP_Y;
    private int HINT_X;
    private int HINT_Y;
    private final ActionListener dealCardsListener = new ButtonDealCardsListener(this, game);
    private final ActionListener hitListener = new ButtonHitListener(this, game);
    private final ActionListener standListener = new ButtonStandListener(this, game);
    private final ActionListener doubleListener = new ButtonDoubleListener(this, game);
    private final ActionListener splitListener = new ButtonSplitListener(this, game);
    private ArrayList<Card> playerCards;
    private ArrayList<Card> otherPlayerCards;
    private ArrayList<Card> dealerCards;
    private boolean initGame = false;
    private boolean isEnd = false;
    private boolean isStand = false;
    private boolean isLose = false;
    private boolean isWin = false;
    private boolean isTie = false;
    private boolean isBet = false;
    private boolean isNoMoney = false;
    private boolean allowedToDouble = false;
    private boolean allowedToSplit = false;
    private boolean isSplit = false;
    private boolean isSecondHand = false;
    private boolean isWinOneHand = false;
    private int bank = 0;
    private int playerMoney;
    private final Points gamePoints;
    private final Map<String, JButton> buttons = new HashMap<>();
    Timer timer = new Timer(2000, new TimerListener(this, game));
    private final ArrayList<ChipComponent> chips25 = new ArrayList<>();
    private final ArrayList<ChipComponent> chips100 = new ArrayList<>();
    private final ArrayList<ChipComponent> chips50 = new ArrayList<>();
    private final double displayCoeffX;
    private final double displayCoeffY;
    public GameTable(JFrame frame, double displayCoeffX, double displayCoeffY) {
        this.frame = frame;
        this.displayCoeffX = displayCoeffX;
        this.displayCoeffY = displayCoeffY;
        setSizes(displayCoeffX, displayCoeffY);
        setLayout(null);
        loadImages();
        addButtons();
        createChips();
        playerMoney = game.getPlayerMoney();
        gamePoints = game.getGamePoints();
    }
    private void setSizes(double coeffX, double coeffY) {
        BET_AREA_X = (int) (420 * coeffX);
        BET_AREA_Y = (int) (240 * coeffY);
        PLAYER_MONEY_X = (int) (540 * coeffX);
        PLAYER_MONEY_Y = (int) (450 * coeffY);
        PLAYER_BET_X = (int) (350 * coeffX);
        PLAYER_BET_Y = (int) (300 * coeffY);
        PLAYER_POINTS_X = (int) (380 * coeffX);
        PLAYER_POINTS_Y = (int) (370 * coeffY);
        DEALER_POINTS_X = (int) (390 * coeffX);
        DEALER_POINTS_Y = (int) (120 * coeffY);
        POINTS_SIZE = (int) (20 * (coeffX + coeffY) / 2);
        BUTTON_WIDTH = (int) (80 * coeffX);
        BUTTON_HEIGHT = (int) (40 * coeffY);
        BUTTON_1STR_X = (int) (550 * coeffX);
        BUTTON_1STR_Y = (int) (500 * coeffY);
        PLAYER_CARD_X = (int) (410 * coeffX);
        PLAYER_CARD_Y = (int) (330 * coeffY);
        DEALER_CARD_X = (int) (440 * coeffX);
        DEALER_CARD_Y = (int) (110 * coeffY);
        CARD_WIDTH = (int) (45 * coeffX);
        CARD_HEIGHT = (int) (67 * coeffY);
        ARROW_X = (int) (418 * coeffX);
        ARROW_Y = (int) (400 * coeffY);
        ARROW_WIDTH = (int) (30 * coeffX);
        ARROW_HEIGHT = (int) (35 * coeffY);
        PLAYER_CHIP_X = (int) (490 * coeffX);
        PLAYER_CHIP_Y = (int) (390 * coeffY);
        TABLE_X = (int) (1000 * coeffX);
        TABLE_Y = (int) (700 * coeffY);
        HINT_X = (int) (40 * coeffX);
        HINT_Y = (int) (650 * coeffX);
    }


    private void createChips() {
        int offsetX = (int) (30 * displayCoeffX);

        for (int i = 0, offset = 0; i < 6; i++, offset += 5 * displayCoeffY) {
            ChipComponent chip = new ChipComponent(PLAYER_CHIP_X , PLAYER_CHIP_Y + offset, 25, displayCoeffX, displayCoeffY);
            ButtonMouseListener buttonMouseListener = new ButtonMouseListener(this, game, chip, displayCoeffX, displayCoeffY);
            chip.addMouseListener(buttonMouseListener);
            chip.addMouseMotionListener(buttonMouseListener);
            add(chip);
            chips25.add(chip);
        }
        for (int i = 0, offset = 0; i < 6; i++, offset += 5 * displayCoeffY) {
            ChipComponent chip = new ChipComponent(PLAYER_CHIP_X - offsetX, PLAYER_CHIP_Y + offset, 100, displayCoeffX, displayCoeffY);
            ButtonMouseListener buttonMouseListener = new ButtonMouseListener(this, game, chip, displayCoeffX, displayCoeffY);
            chip.addMouseListener(buttonMouseListener);
            chip.addMouseMotionListener(buttonMouseListener);
            add(chip);
            chips100.add(chip);
        }
        for (int i = 0, offset = 0; i < 5; i++, offset += 5 * displayCoeffY) {
            ChipComponent chip = new ChipComponent(PLAYER_CHIP_X - offsetX * 2, PLAYER_CHIP_Y + offset, 50, displayCoeffX, displayCoeffY);
            ButtonMouseListener buttonMouseListener = new ButtonMouseListener(this, game, chip, displayCoeffX, displayCoeffY);
            chip.addMouseListener(buttonMouseListener);
            chip.addMouseMotionListener(buttonMouseListener);
            add(chip);
            chips50.add(chip);
        }
    }

    private void removeChips(ArrayList<ChipComponent> chips) {
        for (int i = 0; i < chips.size(); i++) {
            if (inBetArea(chips.get(i))) {
                remove(chips.get(i));
                chips.remove(i);
                i--;
            }
        }
    }

    private void addChips(ArrayList<ChipComponent> chips, int displX, int val) {
        int len = chips.size();
        for (int i = 0, offset = 0; i < len; i++, offset += 5 * displayCoeffY) {
            if (inBetArea(chips.get(i))) {
                ChipComponent chip = new ChipComponent(PLAYER_CHIP_X - displX, PLAYER_CHIP_Y + offset, val, displayCoeffX, displayCoeffY);
                offset += 5 * displayCoeffY;
                ButtonMouseListener buttonMouseListener = new ButtonMouseListener(this, game, chip, displayCoeffX, displayCoeffY);
                chip.addMouseListener(buttonMouseListener);
                chip.addMouseMotionListener(buttonMouseListener);
                chips.add(chip);
                add(chip);
            }
            chips.get(i).setLocation(PLAYER_CHIP_X - displX, PLAYER_CHIP_Y + offset);
        }
    }

    private int doubleChip(ArrayList<ChipComponent> chips, int displX, int sum) {
        int len = chips.size();
        for (int i = 0, offset = 0; i < len; i++, offset += 5 * displayCoeffY) {
            if (!inBetArea(chips.get(i))) {
                if (sum - chips.get(i).getVal() >= 0) {
                    sum -= chips.get(i).getVal();
                    chips.get(i).setLocation((int) (BET_AREA_X + 10 * displayCoeffX + displX), (int) (BET_AREA_Y + 10 * displayCoeffY + offset));
                }
            }
        }
        return sum;
    }

    public void doubleChips() {
        int sum = bank;
        int offsetX = (int) (20 * displayCoeffX);
        sum = doubleChip(chips100, 0, sum);
        if (sum != 0) {
            sum = doubleChip(chips50, offsetX, sum);
        }
        if (sum != 0) {
            doubleChip(chips25, offsetX * 2, sum);
        }
    }

    public void resetChips() {
        int offsetX = (int) (30 * displayCoeffX);
        if (isWinOneHand || isTie) {
            for (int i = 0, offset = 0; i < chips25.size(); i++, offset += 5 * displayCoeffY) {
                chips25.get(i).setLocation(PLAYER_CHIP_X, PLAYER_CHIP_Y + offset);
            }
            for (int i = 0, offset = 0; i < chips100.size(); i++, offset += 5 * displayCoeffY) {
                chips100.get(i).setLocation(PLAYER_CHIP_X - offsetX, PLAYER_CHIP_Y + offset);
            }
            for (int i = 0, offset = 0; i < chips50.size(); i++, offset += 5 * displayCoeffY) {
                chips50.get(i).setLocation(PLAYER_CHIP_X - offsetX * 2, PLAYER_CHIP_Y + offset);
            }
        }
        if (isWin) {
            addChips(chips25, 0, 25);
            addChips(chips100, offsetX, 100);
            addChips(chips50, offsetX * 2, 50);
        }
        if (isLose) {
            removeChips(chips25);
            removeChips(chips100);
            removeChips(chips50);
        }
    }

    private boolean inBetArea(@NotNull ChipComponent tmp) {
        return tmp.getX() > BET_AREA_X && tmp.getX() < BET_AREA_X + 130 * displayCoeffX && tmp.getY() > BET_AREA_Y && tmp.getY() < BET_AREA_Y + 70 * displayCoeffY;
    }

    private void loadImages() {
        String path = "pictures/";
        for (int i = 0; i < 13; i++) {
            cardsImgs[0][i] = new ImageIcon(path + "ch_" + (i + 2) + ".png").getImage();
        }
        for (int i = 0; i < 13; i++) {
            cardsImgs[1][i] = new ImageIcon(path + "b_" + (i + 2) + ".png").getImage();
        }
        for (int i = 0; i < 13; i++) {
            cardsImgs[2][i] = new ImageIcon(path + "cr_" + (i + 2) + ".png").getImage();
        }
        for (int i = 0; i < 13; i++) {
            cardsImgs[3][i] = new ImageIcon(path + "p_" + (i + 2) + ".png").getImage();
        }
    }

    private void addButtons() {
        int offsetX = (int) (85 * displayCoeffX);
        JButton buttonDealCards = new JButton("Deal");
        buttonDealCards.setBounds(BUTTON_1STR_X, BUTTON_1STR_Y, BUTTON_WIDTH, BUTTON_HEIGHT);
        buttonDealCards.addActionListener(dealCardsListener);
        add(buttonDealCards);
        buttonDealCards.setEnabled(false);
        buttons.put("Deal", buttonDealCards);

        JButton buttonHit = new JButton("Hit");
        buttonHit.setBounds(BUTTON_1STR_X + offsetX, BUTTON_1STR_Y, BUTTON_WIDTH, BUTTON_HEIGHT);
        buttonHit.addActionListener(hitListener);
        add(buttonHit);
        buttonHit.setEnabled(false);
        buttons.put("Hit", buttonHit);

        JButton buttonStand = new JButton("Stand");
        buttonStand.setBounds(BUTTON_1STR_X + offsetX * 2, BUTTON_1STR_Y, BUTTON_WIDTH, BUTTON_HEIGHT);
        buttonStand.addActionListener(standListener);
        add(buttonStand);
        buttonStand.setEnabled(false);
        buttons.put("Stand", buttonStand);

        JButton buttonDouble = new JButton("Double");
        buttonDouble.setBounds(BUTTON_1STR_X + offsetX * 3, BUTTON_1STR_Y, BUTTON_WIDTH, BUTTON_HEIGHT);
        buttonDouble.addActionListener(doubleListener);
        add(buttonDouble);
        buttonDouble.setEnabled(false);
        buttons.put("Double", buttonDouble);

        JButton buttonSplit = new JButton("Split");
        buttonSplit.setBounds(BUTTON_1STR_X + offsetX * 4, BUTTON_1STR_Y, BUTTON_WIDTH, BUTTON_HEIGHT);
        buttonSplit.addActionListener(splitListener);
        add(buttonSplit);
        buttonSplit.setEnabled(false);
        buttons.put("Split", buttonSplit);
    }

    @Override
    protected void paintComponent(Graphics g) { // сам вызывается чтобы отрисовать
        super.paintComponent(g);
        g.drawImage(tableImg, 0, 0, TABLE_X, TABLE_Y,null);

        g.setFont(new Font("", Font.BOLD, POINTS_SIZE - 5));
        g.setColor(Color.LIGHT_GRAY);
        g.drawString("Move the chips to the center for the bet", HINT_X, HINT_Y);
        paintPlayerMoney(g);
        if (isNoMoney) {
            paintEndStr(g, "You don't have enough money.");
        }
        if (isBet) {
            isNoMoney = false;
            paintBet(g);
            buttons.get("Deal").setEnabled(true);
        }
        if (initGame) {
            paintCards(g);
            buttons.get("Deal").setEnabled(false);
            buttons.get("Hit").setEnabled(true);
            buttons.get("Stand").setEnabled(true);
            buttons.get("Double").setEnabled(allowedToDouble);
            buttons.get("Split").setEnabled(allowedToSplit);
        }
        if (isSplit) {
            if (!isSecondHand)
                g.drawImage(arrowImg, ARROW_X, ARROW_Y, ARROW_WIDTH, ARROW_HEIGHT,null);
            else {
                g.drawImage(arrowImg, (int) (ARROW_X + 202 * displayCoeffX), ARROW_Y, ARROW_WIDTH, ARROW_HEIGHT,null);
            }
        }
        paintPoints(g);
        if (isEnd) {
            buttons.get("Double").setEnabled(false);
            buttons.get("Hit").setEnabled(false);
            buttons.get("Split").setEnabled(false);
            buttons.get("Stand").setEnabled(false);
            if (isLose) {
                paintEndStr(g, "YOU LOSE.");
            }
            if (isWin) {
                paintEndStr(g, "YOU WIN!");
            }
            if (isTie) {
                paintEndStr(g, "WE HAVE A TIE.");
            }
            if (isWinOneHand) {
                paintEndStr(g, "YOU WIN ONE HAND.");
            }
            timer.start();
            timer.setRepeats(false);
        }
    }

    private void paintPlayerMoney(Graphics g) {
        Font font = new Font("", Font.BOLD, POINTS_SIZE);
        g.setFont(font);
        g.setColor(Color.CYAN);
        g.drawString(String.valueOf(playerMoney) + "$", PLAYER_MONEY_X, PLAYER_MONEY_Y);
    }

    private void paintBet(Graphics g) {
        Font font = new Font("", Font.BOLD, POINTS_SIZE);
        g.setFont(font);
        g.setColor(Color.CYAN);
        g.drawString(String.valueOf(bank) +"$", PLAYER_BET_X, PLAYER_BET_Y);
    }

    private void paintEndStr(Graphics g, String str) {
        Font font = new Font("", Font.BOLD, POINTS_SIZE + 20);
        g.setFont(font);
        g.setColor(Color.RED);
        g.drawString(str, (int) (300 * displayCoeffX), (int) (250 * displayCoeffY));
    }

    private void paintPoints(Graphics g) {
        Font font = new Font("", Font.BOLD, POINTS_SIZE);
        g.setFont(font);
        g.setColor(Color.ORANGE);
        g.drawString(String.valueOf(gamePoints.getPlayer()), PLAYER_POINTS_X, PLAYER_POINTS_Y);
        g.drawString(String.valueOf(gamePoints.getDealer()), DEALER_POINTS_X, DEALER_POINTS_Y);
        if (isSplit) {
            g.drawString(String.valueOf(gamePoints.getSecond()), (int) (PLAYER_POINTS_X + 200 * displayCoeffX), PLAYER_POINTS_Y);
        }
    }

    private void paintCards(Graphics g) {
        int offset = 0;
        for (Card card: playerCards) {
            int suit = card.getSuit();
            int number = card.getNumber();
            g.drawImage(cardsImgs[suit][number], PLAYER_CARD_X + offset, PLAYER_CARD_Y, CARD_WIDTH, CARD_HEIGHT, null);
            offset += 46 * displayCoeffX;
        }
        if (isSplit) {
            offset = 0;
            for (Card card: otherPlayerCards) {
                int suit = card.getSuit();
                int number = card.getNumber();
                g.drawImage(cardsImgs[suit][number], PLAYER_CARD_X + 200 + offset, PLAYER_CARD_Y, CARD_WIDTH, CARD_HEIGHT, null);
                offset += 46 * displayCoeffX;
            }
        }
        if (!isStand) {
            g.drawImage(shirtImg, (int) (DEALER_CARD_X + 46 * displayCoeffX), DEALER_CARD_Y, CARD_WIDTH, CARD_HEIGHT, null);
        }
        offset = 0;
        for (Card card: dealerCards) {
            int suit = card.getSuit();
            int number = card.getNumber();
            g.drawImage(cardsImgs[suit][number], DEALER_CARD_X + offset, DEALER_CARD_Y, CARD_WIDTH, CARD_HEIGHT, null);
            offset += 46 * displayCoeffX;
        }
    }

    public void setCards(@NotNull ArrayList<Card> playerCards, @NotNull ArrayList<Card> dealerCards, @NotNull ArrayList<Card> otherPlayerCards) {
        this.playerCards = playerCards;
        this.dealerCards = dealerCards;
        this.otherPlayerCards = otherPlayerCards;
    }

    public void setInitGame() {
        initGame = true;
    }

    public void setStand() {
        isStand = true;
    }

    public void setLose() {
        isEnd = true;
        isLose = true;
    }

    public void setWinOneHand() {
        isEnd = true;
        isWinOneHand = true;
    }

    public void setWin() {
        isWin = true;
        isEnd = true;
    }

    public void setTie() {
        isEnd = true;
        isTie = true;
    }

    public void setBank(int bank) {
        this.bank = bank;
        isBet = true;
    }

    public void setPlayerMoney(int playerMoney) {
        this.playerMoney = playerMoney;
    }

    public void setAllowedToDouble(boolean allowedToDouble) {
        this.allowedToDouble = allowedToDouble;
    }

    public void setSplit() {
        isSplit = true;
    }

    public void setAllowedToSplit(boolean allowedToSplit) {
        this.allowedToSplit = allowedToSplit;
    }

    public void setIsSecondHand() {
        isSecondHand = true;
    }

    public void resetFlags() {
        isLose = false;
        isWin = false;
        isTie = false;
        isWinOneHand = false;
        isStand = false;
        isEnd = false;
        isBet = false;
        isSplit = false;
        allowedToDouble = false;
        allowedToSplit = false;
        initGame = false;
        isSecondHand = false;
    }

    public boolean isInitGame() {
        return initGame;
    }
}
