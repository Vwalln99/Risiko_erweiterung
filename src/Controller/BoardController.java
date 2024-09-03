package Controller;

import Model.Country;
import Config.NeighborRelations;
import Model.Player;
import View.BoardView;
import View.CountryView;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class BoardController {

    BoardView boardView;

    String turn;
    private String phase = "Set Soldiers";
    String lastPhase;

    Map<String, Country> allCountries = new HashMap<>();
    Map<String, CountryView> allCountryViews = new HashMap<>();
    Map<String, String[]> countryNeighbors = new HashMap<>();

    private final String boardChoice;
    private final Player playerOne;
    private final Player playerTwo;
    private Player currentPlayer;
    public FightController fightController;
    private SendArmyController sendArmyController;


    public BoardController(String boardChoice, String playerOneName, String playerTwoName,
                           Color playerOneColor, Color playerTwoColor) {
        this.boardChoice = boardChoice;
        this.playerOne = new Player(playerOneName, playerOneColor);
        this.playerTwo = new Player(playerTwoName, playerTwoColor);
        this.currentPlayer = playerOne;
    }

    public void createBoardView() {
        System.out.println(this.playerOne.getPlayerColor());
        System.out.println(this.playerTwo.getPlayerColor());
        boardView = new BoardView(this.boardChoice, allCountries, this, allCountryViews);
        boardView.setVisible(true);
        boardView.setCurrentPhaseLabel(getPhase());
        turn = this.playerOne.getName() + "'s Turn";
        boardView.setPlayerTurnLabel(turn);

        setCountryNeighbors(boardChoice);

        this.fightController = new FightController(this, boardView);
        this.sendArmyController = new SendArmyController(this, boardView);
    }

    public String getPhase() {
        return phase;
    }
    public void setPhase(String newPhase) {
        phase = newPhase;
    }
    public Player getCurrentPlayer() {
        return this.currentPlayer;
    }
    public void setCurrentPlayer(Player newCurrentPlayer) {
        this.currentPlayer = newCurrentPlayer;
    }
    public Player getPlayerOne() {
        return this.playerOne;
    }
    public Player getPlayerTwo() {
        return this.playerTwo;
    }

    // Depending on the board, sets the all other countries which a country can attack or send soldiers to
    public void setCountryNeighbors(String boardChoice) {
        switch (boardChoice) {
            case "board1" -> NeighborRelations.addCountryNeighbors1(countryNeighbors);
            case "board2" -> NeighborRelations.addCountryNeighbors2(countryNeighbors);
            case "board3" -> NeighborRelations.addCountryNeighbors3(countryNeighbors);
        }
    }

    // Highlights all neighbors of a country
    public void showHideNeighbors(String countryName, boolean show) {
        String[] allNeighbors = countryNeighbors.get(countryName);

        for (String neighbor : allNeighbors) {
            allCountryViews.get(neighbor).setHighlight(show);
        }
    }

    // Checks if all available countries have been chosen at beginning of game
    public boolean allCountriesFilled() {
        boolean allFilled = true;
        for (Country c : allCountries.values()) {
            if (c.getOwner() == null) {
                allFilled = false;
                break;
            }
        }
        return allFilled;
    }

    public boolean checkIfNeighbor(String countryName, String potentialNeighbor) {
        boolean neighborCheck = false;
        String[] neighbors = countryNeighbors.get(countryName);
        for (String neighbor : neighbors) {
            if(neighbor.equals(potentialNeighbor)) {
                neighborCheck = true;
                break;
            }
        }
        return neighborCheck;
    }

    // Logic for the first phase, where both players choose and fill their starting countries
    public void placeSoldiers(Country country, CountryView view) {
        if(turn.equals(this.playerOne.getName() + "'s Turn") && (country.getSoldiersInside() == 0 || allCountriesFilled())){
            country.setOwner(this.playerOne);
            this.playerOne.removeSoldiers(1);
            country.addSoldiersInside(1);
            view.setSoldierLabel("Soldiers: " + country.getSoldiersInside());
            view.setBackgroundColor(this.playerOne.getPlayerColor());
            view.setSoldierIcons(country.getSoldiersInside());
            turn = turn.equals(this.playerOne.getName() + "'s Turn") ? this.playerTwo.getName() + "'s Turn" : this.playerOne.getName() + "'s Turn";
            this.currentPlayer = this.currentPlayer == this.playerOne ? this.playerTwo : this.playerOne;
            boardView.setPlayerTurnLabel(turn);


        } else if (turn.equals(this.playerTwo.getName() + "'s Turn") && (country.getSoldiersInside() == 0 || allCountriesFilled())) {
            country.setOwner(this.playerTwo);
            this.playerTwo.removeSoldiers(1);
            country.addSoldiersInside(1);
            view.setSoldierLabel("Soldiers: " + country.getSoldiersInside());
            view.setBackgroundColor(this.playerTwo.getPlayerColor());
            view.setSoldierIcons(country.getSoldiersInside());
            turn = turn.equals(this.playerOne.getName() + "'s Turn") ? this.playerTwo.getName() + "'s Turn" : this.playerOne.getName() + "'s Turn";
            this.currentPlayer = this.currentPlayer == this.playerOne ? this.playerTwo : this.playerOne;
            boardView.setPlayerTurnLabel(turn);

        }

        // Switches to next phase when the players have no more soldiers to place
        if(this.playerTwo.getSoldiers() == 0) {
            setPhase("Attack Phase");
            boardView.setCurrentPhaseLabel(getPhase());
            boardView.endTurnButton.setEnabled(true);
        }
    }

    // Setting or Unsetting an attacking and defending country for the Attack Phase
    public void attackPhase(Country country, CountryView view) {
        if(this.fightController.getAttackingCountry() == null && country.getOwner() == this.currentPlayer && country.getSoldiersInside() > 1) {
            this.fightController.setAttackingCountry(country);
            this.fightController.setAttackingCountryView(view);
            view.setBackgroundColor(Color.CYAN);
        } else if (this.fightController.getAttackingCountry() != null && this.fightController.getAttackingCountry().getName().equals(country.getName())) {
            this.fightController.setAttackingCountry(null);
            this.fightController.setAttackingCountryView(null);
            if (this.currentPlayer == this.playerOne) {
                view.setBackgroundColor(this.playerOne.getPlayerColor());
            } else {
                view.setBackgroundColor(this.playerTwo.getPlayerColor());
            }
            boardView.attackButton.setEnabled(false);
        } else if (this.fightController.getAttackingCountry() != null &&
                this.fightController.getDefendingCountry() == null &&
                checkIfNeighbor(this.fightController.getAttackingCountry().getName(), country.getName()) &&
                this.fightController.getAttackingCountry().getOwner() != country.getOwner()) {
            this.fightController.setDefendingCountry(country);
            this.fightController.setDefendingCountryView(view);
            view.setBackgroundColor(Color.RED);
            boardView.attackButton.setEnabled(true);
        } else if (this.fightController.getDefendingCountry() != null && this.fightController.getDefendingCountry().getName().equals(country.getName())) {
            this.fightController.setDefendingCountry(null);
            this.fightController.setDefendingCountryView(null);
            if (this.currentPlayer == this.playerOne) {
                view.setBackgroundColor(this.playerTwo.getPlayerColor());
            } else {
                view.setBackgroundColor(this.playerOne.getPlayerColor());
            }
            boardView.attackButton.setEnabled(false);
        }
    }

    // Start the extra phase for each player after they clicked their card button with 3 or more cards
    public void playerOneSetCardsPhase() {
        this.playerOne.cardsToSoldiers();
        boardView.setPlayerOneCardsButtonText(this.playerOne.getName() + " Cards: " + this.playerOne.getCards());
        lastPhase = getPhase();
        setPhase(this.playerOne.getName() + ": Set Soldiers");
        boardView.setCurrentPhaseLabel(this.playerOne.getName() + ": Set " + this.playerOne.getSoldiers() + " Soldier(s)");
    }

    public void playerTwoSetCardsPhase() {
        this.playerTwo.cardsToSoldiers();
        boardView.setPlayerTwoCardsButtonText(this.playerTwo.getName() + " Cards: " + this.playerTwo.getCards());
        lastPhase = getPhase();
        setPhase(this.playerTwo.getName() + ": Set Soldiers");
        boardView.setCurrentPhaseLabel(this.playerTwo.getName() + ": Set " + this.playerOne.getSoldiers() + " Soldier(s)");
    }

    // Logic for the extra phase, after the card button click
    public void playerOneSetCardTroops(Country country, CountryView view) {
        this.playerOne.removeSoldiers(1);
        country.addSoldiersInside(1);
        view.setSoldierLabel("Soldiers: " + country.getSoldiersInside());
        view.setSoldierIcons(country.getSoldiersInside());
        if(this.playerOne.getSoldiers() == 0) {
            setPhase(lastPhase);
            boardView.setCurrentPhaseLabel(getPhase());
        } else {
            boardView.setCurrentPhaseLabel(this.playerOne.getName() + ": Set " + this.playerOne.getSoldiers() + " Soldier(s)");
        }
    }

    public void playerTwoSetCardTroops(Country country, CountryView view) {
        this.playerTwo.removeSoldiers(1);
        country.addSoldiersInside(1);
        view.setSoldierLabel("Soldiers: " + country.getSoldiersInside());
        view.setSoldierIcons(country.getSoldiersInside());
        if(this.playerTwo.getSoldiers() == 0) {
            setPhase(lastPhase);
            boardView.setCurrentPhaseLabel(getPhase());
        } else {
            boardView.setCurrentPhaseLabel(this.playerTwo.getName() + ": Set " + this.playerTwo.getSoldiers() + " Soldier(s)");
        }
    }

    // Setting / Unsetting a sending and receiving country and opening the Send Armies Window
    public void fortificationPhase(Country country, CountryView view) {
        if(this.sendArmyController.getSendingCountry() == null &&
                country.getSoldiersInside() > 1 &&
                country.getOwner() == this.currentPlayer) {
            this.sendArmyController.setSendingCountry(country);
            this.sendArmyController.setSendingCountryView(view);
            view.setBackgroundColor(Color.MAGENTA);
        } else if (this.sendArmyController.getSendingCountry() != null && this.sendArmyController.getSendingCountry().getName().equals(country.getName())) {
            this.sendArmyController.setSendingCountry(null);
            this.sendArmyController.setSendingCountryView(null);
            if (this.currentPlayer == this.playerOne) {
                view.setBackgroundColor(this.playerOne.getPlayerColor());
            } else {
                view.setBackgroundColor(this.playerTwo.getPlayerColor());
            }
        } else if (this.sendArmyController.getReceivingCountry() == null &&
                this.sendArmyController.getSendingCountry() != null &&
                checkIfNeighbor(this.sendArmyController.getSendingCountry().getName(), country.getName()) &&
                this.sendArmyController.getSendingCountry().getOwner() == country.getOwner()) {
            this.sendArmyController.setReceivingCountry(country);
            this.sendArmyController.setReceivingCountryView(view);
            this.sendArmyController.createSendArmyView();
        }
    }

    // Sets new Soldiers at beginning of turn and switches to other player, when first one is done
    public void setNewTroops(Country country, CountryView view) {
        if(country.getOwner() == this.currentPlayer) {
            country.addSoldiersInside(1);
            this.currentPlayer.removeSoldiers(1);
            view.setSoldierLabel("Soldiers: " + country.getSoldiersInside());
            view.setSoldierIcons(country.getSoldiersInside());
            boardView.setCurrentPhaseLabel(getPhase() + " " + this.currentPlayer.getName() + " " + this.currentPlayer.getSoldiers() + " Soldier(s)");

            if(this.currentPlayer == this.playerOne && this.playerOne.getSoldiers() == 0 && this.playerTwo.getSoldiers() != 0) {
                setCurrentPlayer(this.playerTwo);
                turn = this.currentPlayer.getName() + "'s Turn";
                boardView.setPlayerTurnLabel(turn);
                boardView.setCurrentPhaseLabel(getPhase() + " " + this.currentPlayer.getName() + " " + this.currentPlayer.getSoldiers() + " Soldier(s)");
            }
            else if(this.currentPlayer == this.playerTwo && this.playerTwo.getSoldiers() == 0 && this.playerOne.getSoldiers() != 0) {
                setCurrentPlayer(this.playerOne);
                turn = this.currentPlayer.getName() + "'s Turn";
                boardView.setPlayerTurnLabel(turn);
                boardView.setCurrentPhaseLabel(getPhase() + " " + this.currentPlayer.getName() + " " + this.currentPlayer.getSoldiers() + " Soldier(s)");
            }
            else if(this.playerOne.getSoldiers() == 0 && this.playerTwo.getSoldiers() == 0) {
                this.currentPlayer = this.currentPlayer == this.playerOne ? this.playerTwo : this.playerOne;
                turn = this.currentPlayer.getName() + "'s Turn";
                boardView.setPlayerTurnLabel(turn);
                setPhase("Attack Phase");
                boardView.setCurrentPhaseLabel(getPhase());
            }
        }
    }

    public void endPhase() {
        if(getPhase().equals("Attack Phase")) {
            setPhase("Fortification Phase");
            boardView.setCurrentPhaseLabel("Fortifications: " + this.sendArmyController.getFortifications() + " Left");
            boardView.setEndTurnButtonText("End Turn");
        }
        else if(getPhase().equals("Fortification Phase")) {
            endTurn();
        }
    }

    public void checkIfTooManyCards() {
        // If player has more than 5 cards, he is forced to use them
        if (this.currentPlayer.getCards() > 5) {
            if (this.currentPlayer == this.playerOne) {
                this.playerOne.cardsToSoldiers();
                boardView.setPlayerOneCardsButtonText(this.playerOne.getName() + " Cards: " + this.playerOne.getCards());
                setPhase(this.playerOne.getName() + ": Set Soldiers");
                boardView.setCurrentPhaseLabel(this.playerOne.getName() + ": Set " + this.playerOne.getSoldiers() + " Soldier(s)");
            }
            if (this.currentPlayer == this.playerTwo) {
                this.playerTwo.cardsToSoldiers();
                boardView.setPlayerTwoCardsButtonText(this.playerTwo.getName() + " Cards: " + this.playerTwo.getCards());
                setPhase(this.playerTwo.getName() + ": Set Soldiers");
                boardView.setCurrentPhaseLabel(this.playerTwo.getName() + ": Set " + this.playerTwo.getSoldiers() + " Soldier(s)");
            }
        }
    }

    // Calculates the new Troops at the beginning of a turn, depending on owned countries and continents
    public void getNewTroops(Player player) {
        int newTroops = 0;
        int countriesOwned = 0;
        int continentsOwned = checkOwningContinents(player);

        for (Country c : allCountries.values()) {
            if(c.getOwner() == player) countriesOwned++;
        }
        if((countriesOwned / 3) < 3) {
            newTroops += 3;
        } else {
            newTroops += (int)Math.floor(countriesOwned / 3);
        }
        newTroops += continentsOwned * 3;

        player.addSoldiers(newTroops);
    }

    public int checkOwningContinents(Player player) {
        int continentsOwned = 0;
        int contA = 0;
        int contB = 0;
        int contC = 0;
        int contD = 0;

        for (Country c : allCountries.values()) {
            if(c.getOwner() == player) {
                if(c.getContinent().equals("A")) {
                    contA++;
                    if(contA == 6) continentsOwned++;
                }
                if(c.getContinent().equals("B")) {
                    contB++;
                    if(contB == 6) continentsOwned++;
                }
                if(c.getContinent().equals("C")) {
                    contC++;
                    if(contC == 6) continentsOwned++;
                }
                if(c.getContinent().equals("D")) {
                    contD++;
                    if(contD == 6) continentsOwned++;
                }
            }
        }
        return continentsOwned;
    }


    public void endTurn() {
        this.currentPlayer = this.currentPlayer == this.playerOne ? this.playerTwo : this.playerOne;
        turn = this.currentPlayer.getName() + "'s Turn";
        boardView.setPlayerTurnLabel(turn);
        setPhase("New Troops Phase");
        getNewTroops(this.playerOne);
        getNewTroops(this.playerTwo);
        boardView.setCurrentPhaseLabel(getPhase() + " " + this.currentPlayer.getName() + " " + this.currentPlayer.getSoldiers() + " Soldier(s)");
        this.sendArmyController.setFortifications(3);
    }

    public void checkWin() {
        boolean win = true;
        for(Country country : allCountries.values()) {
            if (country.getOwner() != currentPlayer) {
                win = false;
                break;
            }
        }
        if(win) {
            JOptionPane.showMessageDialog(boardView, currentPlayer.getName() + " has won the game!");
        }
    }


}