package Controller;

import View.StartWindowView;

import javax.swing.*;
import java.awt.*;

public class StartWindowController {
    JFrame startWindowFrame;

    Color[] playerColors = new Color[4];
    String boardChoice;

    public void setPlayerColor(int playerIndex, Color color) {
        playerColors[playerIndex] = color;
    }

    public Color getPlayerColor(int playerIndex) {
        return playerColors[playerIndex];
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

    public void startGame(String[] playerNames) {
        BoardController board = new BoardController(boardChoice, playerNames, playerColors);
        board.assignColorsToCountries();
        board.createBoardView();

        startWindowFrame.dispose();
    }

    public static void main(String[] args) {
        StartWindowController startWindowController = new StartWindowController();
        startWindowController.createStartWindow();
    }
}
