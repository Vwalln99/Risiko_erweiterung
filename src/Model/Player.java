package Model;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Player {
    private final String name;
    private int soldiers;
    //private int cards;
    private final Color playerColor;
    private final List<String> cards;
    private  String winCondition;


    public Player(String name, Color playerColor, int initialSoldiers, String winCondition) {
        this.name = name;
        this.soldiers = initialSoldiers;
        this.cards = new ArrayList<String>();
        this.playerColor = playerColor;
        this.winCondition = winCondition;
    }

    public String getWinCondition() {
        return winCondition;
    }

    public String getName() {
        return this.name;
    }

    public Color getPlayerColor() {
        return this.playerColor;
    }

    public int getSoldiers() {
        return this.soldiers;
    }

    public void addSoldiers(int soldiers) {
        this.soldiers += soldiers;
    }

    public void removeSoldiers(int soldiers) {
        this.soldiers -= soldiers;
    }



    public List<String> getCards() {
        return this.cards;
    }

    public void addCards(String cards) {
        this.cards.add(cards);
    }

    public void removeCards(int num) {
        for (int i = 0; i < num && !cards.isEmpty(); i++) {
            cards.remove(0);
        }
    }

    public boolean canTradeCards() {
        int infantry = 0, cavalry = 0, artillery = 0;

        for (String card : cards) {
            switch (card) {
                case "Infanterie":
                    infantry++;
                    break;
                case "Kavallerie":
                    cavalry++;
                    break;
                case "Artillerie":
                    artillery++;
                    break;
            }
        }

        return infantry >= 3 || cavalry >= 3 || artillery >= 3 || (infantry >= 1 && cavalry >= 1 && artillery >= 1);
    }

    public void tradeCards() {
        int infantry = 0, cavalry = 0, artillery = 0;
        int soldiersAdded = 0;

        for (String card : cards) {
            switch (card) {
                case "Infanterie":
                    infantry++;
                    break;
                case "Kavallerie":
                    cavalry++;
                    break;
                case "Artillerie":
                    artillery++;
                    break;
            }
        }

        if (infantry >= 3) {
            removeCards(3);
            soldiersAdded = 4;
        } else if (cavalry >= 3) {
            removeCards(3);
            soldiersAdded = 5;
        } else if (artillery >= 3) {
            removeCards(3);
            soldiersAdded = 6;
        } else if (infantry >= 1 && cavalry >= 1 && artillery >= 1) {
            removeCards(3);
            soldiersAdded = 5;
        }

        addSoldiers(soldiersAdded);
    }

    public void receiveRandomCard() {
        String[] cardTypes = {"Infanterie", "Kavallerie", "Artillerie"};
        Random rand = new Random();
        String newCard = cardTypes[rand.nextInt(cardTypes.length)];
        addCards(newCard);
    }

    public void checkAndTradeCards() {
        if (canTradeCards()) {
            tradeCards();
        }
    }
}

