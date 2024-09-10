package Controller;

import View.StartWindowView;

import javax.swing.*;
import java.awt.*;

public class StartWindowController {
    JFrame startWindowFrame;

    Color[] playerColors = new Color[4];
    String[] playerWinConditions = new String[4];
    String boardChoice;

    public void setPlayerColor(int playerIndex, Color color) {
        playerColors[playerIndex] = color;
    }

    public Color getPlayerColor(int playerIndex) {
        return playerColors[playerIndex];
    }

    public void setPlayerWinCondition(int playerNumber, String selectedCondition) {
        playerWinConditions[playerNumber] = selectedCondition;
    }

    public void setBoardChoice(String choice) {
        boardChoice = choice;
    }

    public boolean boardChosen() {
        return boardChoice != null;
    }

    public void createStartWindow() {
        startWindowFrame = new StartWindowView(this).drawStartWindowFrame();
    }
    private boolean boardCreated = false;
    public void startGame(String[] playerNames) {
        if (boardCreated) {
            return;
        }
        System.out.println(playerWinConditions[0]);
        boardCreated = true;
        BoardController board = new BoardController(boardChoice, playerNames, playerColors, playerWinConditions);
        System.out.println("board wird erstellt");
        board.createBoardView();

        startWindowFrame.dispose();
    }

    public static void main(String[] args) {
        StartWindowController startWindowController = new StartWindowController();
        startWindowController.createStartWindow();
    }

}
