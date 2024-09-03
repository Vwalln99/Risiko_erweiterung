package View;

import Controller.StartWindowController;
import Config.Helper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartWindowView implements ActionListener {
    public static final int HEADLINE_HEIGHT = 50;
    public static final int NAME_HEIGHT = 150;
    public static final int BOARD_CHOICE_HEIGHT = 150;
    public static final int PLAYER_NAME_WIDTH = 250;

    JFrame frame;

    JTextField playerOneName;
    JTextField playerTwoName;
    JButton playerOneColorButton1;
    JButton playerOneColorButton2;
    JButton playerOneColorButton3;
    JButton playerTwoColorButton1;
    JButton playerTwoColorButton2;
    JButton playerTwoColorButton3;

    JButton board1;
    JButton board2;
    JButton board3;

    GridBagLayout startWindowLayout = new GridBagLayout();
    GridBagConstraints startWindowConstraints = new GridBagConstraints();
    JPanel startWindowPanel = new JPanel(startWindowLayout);

    private final StartWindowController controller;

    public StartWindowView(StartWindowController controller) {
        this.controller = controller;
    }

    public JFrame drawStartWindowFrame() {
        frame = new JFrame("Risk");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400,400);
        frame.setResizable(false);

        startWindowLayout.rowHeights = new int[] { HEADLINE_HEIGHT, NAME_HEIGHT, BOARD_CHOICE_HEIGHT};
        startWindowLayout.columnWidths = new int[] { PLAYER_NAME_WIDTH, PLAYER_NAME_WIDTH};

        JLabel headline = new JLabel("Risk", JLabel.CENTER);
        headline.setFont(new Font(headline.getFont().getName(), Font.PLAIN, 20));


        JPanel playerOnePanel = new JPanel(new GridLayout(4, 1));
        playerOnePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        JLabel playerOneHeadline = new JLabel("Player One Name: ", JLabel.CENTER);
        playerOneName = new JTextField();
        JLabel playerOneColorHeadline = new JLabel("Choose Player One Color: ", JLabel.CENTER);
        JPanel playerOneColorButtonPanel = new JPanel(new GridLayout(1, 3));
        playerOneColorButtonPanel.setBorder(BorderFactory.createEmptyBorder(0,10,2,10));
        playerOneColorButton1 = createButton("", new Color(61, 194, 87), "playerOneColorButton1");
        playerOneColorButton2 = createButton("", new Color(60, 101, 195), "playerOneColorButton2");
        playerOneColorButton3 = createButton("", new Color(197, 60, 58), "playerOneColorButton3");
        playerOneColorButtonPanel.add(playerOneColorButton1);
        playerOneColorButtonPanel.add(playerOneColorButton2);
        playerOneColorButtonPanel.add(playerOneColorButton3);
        playerOnePanel.add(playerOneHeadline);
        playerOnePanel.add(playerOneName);
        playerOnePanel.add(playerOneColorHeadline);
        playerOnePanel.add(playerOneColorButtonPanel);

        JPanel playerTwoPanel = new JPanel(new GridLayout(4, 1));
        playerTwoPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        JLabel playerTwoHeadline = new JLabel("Player Two Name: ", JLabel.CENTER);
        playerTwoName = new JTextField();
        JLabel playerTwoColorHeadline = new JLabel("Choose Player Two Color: ", JLabel.CENTER);
        JPanel playerTwoColorButtonPanel = new JPanel(new GridLayout(1, 3));
        playerTwoColorButtonPanel.setBorder(BorderFactory.createEmptyBorder(0,10,2,10));
        playerTwoColorButton1 = createButton("", new Color(194, 61, 168), "playerTwoColorButton1");
        playerTwoColorButton2 = createButton("", new Color(195, 154, 60), "playerTwoColorButton2");
        playerTwoColorButton3 = createButton("", new Color(58, 195, 197), "playerTwoColorButton3");
        playerTwoColorButtonPanel.add(playerTwoColorButton1);
        playerTwoColorButtonPanel.add(playerTwoColorButton2);
        playerTwoColorButtonPanel.add(playerTwoColorButton3);
        playerTwoPanel.add(playerTwoHeadline);
        playerTwoPanel.add(playerTwoName);
        playerTwoPanel.add(playerTwoColorHeadline);
        playerTwoPanel.add(playerTwoColorButtonPanel);


        JPanel boardChoicePanel = new JPanel(new GridLayout(3, 1));
        JLabel choice = new JLabel("Choose a game board:", JLabel.CENTER);
        JPanel buttonPanel = new JPanel(new GridLayout(1, 3));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0,20,10,20));
        board1 = createButton("Board 1", null, "board1");
        board2 = createButton("Board 2", null, "board2");
        board3 = createButton("Board 3", null, "board3");

        JButton startButton = new JButton("Start Game");
        startButton.addActionListener(this);
        startButton.setActionCommand("startButton");
        buttonPanel.add(board1);
        buttonPanel.add(board2);
        buttonPanel.add(board3);
        boardChoicePanel.add(choice);
        boardChoicePanel.add(buttonPanel);
        boardChoicePanel.add(startButton);

        startWindowPanel.add(headline, Helper.buildBoardConstraints(startWindowConstraints, 0, 0, 1, 2));
        startWindowPanel.add(playerOnePanel, Helper.buildBoardConstraints(startWindowConstraints, 1, 0, 1, 1));
        startWindowPanel.add(playerTwoPanel, Helper.buildBoardConstraints(startWindowConstraints, 1, 1, 1, 1));
        startWindowPanel.add(boardChoicePanel, Helper.buildBoardConstraints(startWindowConstraints, 2, 0, 1, 2));

        frame.setContentPane(startWindowPanel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        return frame;
    }

    public JButton createButton(String text, Color color, String actionCommand) {
        JButton button = new JButton(text);
        button.setBackground(color);
        button.setBorder(BorderFactory.createLineBorder(new Color(228, 229, 227), 2));
        button.addActionListener(this);
        button.setActionCommand(actionCommand);

        return button;
    }

    // Highlights the first Button in given Array
    public void highlightButton(JButton activeButton, JButton notActiveButton1, JButton notActiveButton2) {
        notActiveButton1.setBorder(BorderFactory.createLineBorder(new Color(228, 229, 227), 2));
        notActiveButton2.setBorder(BorderFactory.createLineBorder(new Color(228, 229, 227), 2));
        activeButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("playerOneColorButton1")) {
            highlightButton(playerOneColorButton1, playerOneColorButton2, playerOneColorButton3);
            controller.setPlayerOneColor(playerOneColorButton1.getBackground());
        }
        if (e.getActionCommand().equals("playerOneColorButton2")) {
            highlightButton(playerOneColorButton2, playerOneColorButton1, playerOneColorButton3);
            controller.setPlayerOneColor(playerOneColorButton2.getBackground());
        }
        if (e.getActionCommand().equals("playerOneColorButton3")) {
            highlightButton(playerOneColorButton3, playerOneColorButton1, playerOneColorButton2);
            controller.setPlayerOneColor(playerOneColorButton3.getBackground());
        }
        if (e.getActionCommand().equals("playerTwoColorButton1")) {
            highlightButton(playerTwoColorButton1, playerTwoColorButton2, playerTwoColorButton3);
            controller.setPlayerTwoColor(playerTwoColorButton1.getBackground());
        }
        if (e.getActionCommand().equals("playerTwoColorButton2")) {
            highlightButton(playerTwoColorButton2, playerTwoColorButton1, playerTwoColorButton3);
            controller.setPlayerTwoColor(playerTwoColorButton2.getBackground());
        }
        if (e.getActionCommand().equals("playerTwoColorButton3")) {
            highlightButton(playerTwoColorButton3, playerTwoColorButton1, playerTwoColorButton2);
            controller.setPlayerTwoColor(playerTwoColorButton3.getBackground());
        }

        if (e.getActionCommand().equals("board1")) {
            highlightButton(board1, board2, board3);
            controller.setBoardChoice("board1");
        }
        if (e.getActionCommand().equals("board2")) {
            highlightButton(board2, board1, board3);
            controller.setBoardChoice("board2");
        }
        if (e.getActionCommand().equals("board3")) {
            highlightButton(board3, board1, board2);
            controller.setBoardChoice("board3");
        }
        if (e.getActionCommand().equals("startButton") &&
            controller.colorsSet() &&
            controller.boardChosen() &&
            !playerOneName.getText().isBlank() &&
            !playerTwoName.getText().isBlank() &&
            !playerOneName.getText().equals(playerTwoName.getText())) {

            controller.startGame(playerOneName.getText(), playerTwoName.getText());
        }
    }
}
