package Controller;

import View.StartWindowView;

import javax.swing.*;
import java.awt.*;

public class StartWindowController {
    JFrame startWindowFrame;

    Color playerOneColor;
    Color playerTwoColor;
    String boardChoice;

    public void setPlayerOneColor(Color color) {
        playerOneColor = color;
    }
    public void setPlayerTwoColor(Color color) {
        playerTwoColor = color;
    }
    public void setBoardChoice(String choice) {
        boardChoice = choice;
    }
    public boolean boardChosen() {
        return boardChoice != null;
    }
    public boolean colorsSet() {
        return playerOneColor != null && playerTwoColor != null;
    }

    public void createStartWindow() {
        startWindowFrame = new StartWindowView(this).drawStartWindowFrame();
    }

    public void startGame(String playerOneName, String playerTwoName) {
        BoardController board = new BoardController(boardChoice, playerOneName, playerTwoName, playerOneColor, playerTwoColor);
        board.createBoardView();

        startWindowFrame.dispose();
    }

    public static void main(String[] args) {
        StartWindowController startWindowController = new StartWindowController();
        startWindowController.createStartWindow();

    }

}