package Model;

import java.awt.*;

public class Player {
    private final String name;
    private int soldiers;
    private int cards;
    private final Color playerColor;

    public Player(String name, Color playerColor) {
        this.name = name;
        this.soldiers = 20;
        this.cards = 0;
        this.playerColor = playerColor;
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

    public int getCards() {
        return this.cards;
    }

    public void addCards(int cards) {
        this.cards += cards;
    }

    public void removeCards(int cards) {
        this.cards -= cards;
    }

    public void cardsToSoldiers() {
        removeCards(3);
        addSoldiers(5);
    }
}