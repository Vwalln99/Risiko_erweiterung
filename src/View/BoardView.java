package View;

import Controller.BoardController;
import Config.Helper;
import Model.Country;
import Model.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;

public class BoardView extends JFrame implements ActionListener {
    public static final int DICE_ROW_HEIGHT = 50;
    public static final int ROW_WIDTH_OUTSIDE = 150;
    public static final int ROW_WIDTH_INSIDE = 1000;
    public static final int FIELD_WIDTH = 1400;
    public static final int FIELD_HEIGHT = 500; //Anpassungen Bildschirm Laptop
    public static final int STAT_ROW_HEIGHT = 30;

    public JLabel playerTurn;
    public JLabel currentPhase;

    public JButton attackButton;
    public JButton endTurnButton;

    //new: list for buttons so everything updates dynamically for up to 4 players
    public JButton[] playerCardsButton;
    //public JButton playerTwoCards;

    GridBagLayout boardLayout = new GridBagLayout();
    GridBagConstraints boardConstraints = new GridBagConstraints();
    JPanel boardPanel = new JPanel(boardLayout);

    public Map<String, Country> allCountries;
    public BoardController boardController;
    public Map<String, CountryView> allCountryViews;

    public BoardView(String boardChoice, Map<String, Country> allCountries, BoardController boardController, Map<String, CountryView> allCountryViews) {
        super("Risk");
        this.allCountries = allCountries;
        this.boardController = boardController;
        this.allCountryViews = allCountryViews;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);

        boardLayout.columnWidths = new int[] {ROW_WIDTH_OUTSIDE, ROW_WIDTH_INSIDE, ROW_WIDTH_OUTSIDE};
        boardLayout.rowHeights = new int[] {DICE_ROW_HEIGHT, FIELD_HEIGHT, STAT_ROW_HEIGHT};

        // Top row of game board
        playerTurn = new JLabel("Player Turn", JLabel.CENTER);
        playerTurn.setOpaque(true);
        playerTurn.setBackground(boardController.getCurrentPlayer().getPlayerColor());

        currentPhase = new JLabel("Current Phase", JLabel.CENTER);
        currentPhase.setOpaque(true);
        currentPhase.setBackground(Color.WHITE);


        endTurnButton = new JButton("End Phase");
        endTurnButton.addActionListener(this);
        endTurnButton.setActionCommand("End Phase");
        endTurnButton.setEnabled(false);

        // Country and continent creation depending on choice in Start Window
        JPanel allContinents = new JPanel();
        ContinentCreator continentCreator = new ContinentCreator(boardController);
        switch (boardChoice) {
            case "board1" -> {

                allContinents = new JPanel(new GridLayout(2, 2));
                JPanel continentOne = continentCreator.createBoard1("A", this.allCountries, this.allCountryViews);
                JPanel continentTwo = continentCreator.createBoard1("B", this.allCountries, this.allCountryViews);
                JPanel continentThree = continentCreator.createBoard1("C", this.allCountries, this.allCountryViews);
                JPanel continentFour = continentCreator.createBoard1("D", this.allCountries, this.allCountryViews);

                allContinents.add(continentOne);
                allContinents.add(continentTwo);
                allContinents.add(continentThree);
                allContinents.add(continentFour);
            }
            case "board2" -> allContinents = continentCreator.createBoard2(this.allCountries, this.allCountryViews);
            case "board3" -> allContinents = continentCreator.createBoard3(this.allCountries, this.allCountryViews);
            case "board4" -> allContinents = continentCreator.createBoard4(this.allCountries, this.allCountryViews);
            case "board5" -> allContinents = continentCreator.createBoard5(this.allCountries, this.allCountryViews);
            case "board6" -> allContinents = continentCreator.createBoard6(this.allCountries, this.allCountryViews);
        }
        allContinents.setBackground(new Color(153,204,255));

        // Bottom row of game board
        //modified for up to 4 players
        int numberOfPlayers = boardController.players.size();
        playerCardsButton = new JButton[numberOfPlayers];
        for (int i = 0; i < numberOfPlayers; i++) {
            String playerName = boardController.players.get(i).getName();
            playerCardsButton[i] = new JButton(playerName + " Cards: 0");
            playerCardsButton[i].setBackground(Color.ORANGE);
            playerCardsButton[i].setOpaque(true);
            playerCardsButton[i].addActionListener(this);
            playerCardsButton[i].setActionCommand("playerCards_" + i);
        }
        /*playerOneCards = new JButton(this.boardController.getPlayerOne().getName() + " Cards: 0" );
        playerOneCards.setBackground(Color.ORANGE);
        playerOneCards.setOpaque(true);
        playerOneCards.addActionListener(this);
        playerOneCards.setActionCommand("playerOneCards");

        playerTwoCards = new JButton(this.boardController.getPlayerTwo().getName() + " Cards: 0");
        playerTwoCards.setOpaque(true);
        playerTwoCards.setBackground(Color.ORANGE);
        playerTwoCards.addActionListener(this);
        playerTwoCards.setActionCommand("playerTwoCards");*/

        attackButton = new JButton("Attack");
        attackButton.addActionListener(this);
        attackButton.setActionCommand("fight");
        attackButton.setEnabled(false);


        boardPanel.add(playerTurn, Helper.buildBoardConstraints(boardConstraints,0,0,1,1));
        boardPanel.add(currentPhase, Helper.buildBoardConstraints(boardConstraints,0,1,1,1));
        boardPanel.add(endTurnButton, Helper.buildBoardConstraints(boardConstraints,0,2,1,1));
        boardPanel.add(allContinents, Helper.buildBoardConstraints(boardConstraints,1,0,1,3));
        /*boardPanel.add(playerOneCards, Helper.buildBoardConstraints(boardConstraints,2,0,1,1));
        boardPanel.add(playerTwoCards, Helper.buildBoardConstraints(boardConstraints,2,2,1,1));*/
        for (int i = 0; i < numberOfPlayers; i++) {
            boardPanel.add(playerCardsButton[i], Helper.buildBoardConstraints(boardConstraints,3,i,1,1));
        }
        boardPanel.add(attackButton, Helper.buildBoardConstraints(boardConstraints,2,1,1,1));

        boardPanel.setPreferredSize(new Dimension(FIELD_WIDTH, DICE_ROW_HEIGHT + FIELD_HEIGHT + STAT_ROW_HEIGHT + 30));
        setContentPane(boardPanel);

        pack();
        setLocationRelativeTo(null);
    }

    public void setPlayerTurnLabel(String text) {
        playerTurn.setText(text);
        playerTurn.setBackground(boardController.getCurrentPlayer().getPlayerColor());
    }

    public void setCurrentPhaseLabel(String text) {
        currentPhase.setText(text);
    }

    public void setEndTurnButtonText(String text) {
        endTurnButton.setText(text);
    }

    public void setPlayerCardsButtonText(int playerIndex, String text) {
        if (playerIndex >= 0 && playerIndex < playerCardsButton.length) {
            playerCardsButton[playerIndex].setText(text);
        }
    }

   /* public void setPlayerTwoCardsButtonText(String text) {
        playerTwoCards.setText(text);
    }*/




    @Override
    //added new logic for dynamic playing
    public void actionPerformed(ActionEvent e) {
       if(e.getActionCommand().equals("fight")) {
            boardController.fightController.createFightView();
        }
        for (JButton jButton : playerCardsButton) {
            if (e.getSource() == jButton) {
                Player player = boardController.getCurrentPlayer();
                if (player.getCards().size() >= 3 &&
                        (boardController.getPhase().equals("Attack Phase") ||
                                boardController.getPhase().equals("Fortification Phase"))) {
                    boardController.setCardPhase(player);
                }
                return;
            }
        }
       /*
        if(boardController.getCurrentPlayer() == boardController.getPlayerOne() &&
            e.getActionCommand().equals("playerOneCards") &&
            boardController.getPlayerOne().getCards() >= 3 &&
            (boardController.getPhase().equals("Attack Phase") || boardController.getPhase().equals("Fortification Phase")))
        {
            boardController.playerOneSetCardsPhase();
        }
        if(boardController.getCurrentPlayer() == boardController.getPlayerTwo() &&
            e.getActionCommand().equals("playerTwoCards") &&
            boardController.getPlayerTwo().getCards() >= 3 &&
            (boardController.getPhase().equals("Attack Phase") || boardController.getPhase().equals("Fortification Phase")))
        {
            boardController.playerTwoSetCardsPhase();
        }

*/
        if(e.getActionCommand().equals("End Phase")) {
            boardController.endPhase();
        }
    }
}
