package Model;

public class Country {
    private final String name;
    private int soldiersInside;
    private Player owner;
    private final String continent;
    private int soldiersSend;
    private Player player;

    public Country(String name, String continent) {
        this.name = name;
        this.soldiersInside = 0;
        this.continent = continent;
        this.soldiersSend = 0;
        this.owner = null;
        this.player = null;
    }

    public String getName() {
        return this.name;
    }

    public int getSoldiersInside() {
        return this.soldiersInside;
    }

    public void addSoldiersInside(int soldiers) {
        this.soldiersInside += soldiers;
    }

    public void removeSoldiersInside(int soldiers) {

        this.soldiersInside -= soldiers;

        //new: give up territory and make it neutral
        if (this.soldiersInside < 0) {
            this.soldiersInside = 0;
        }

        if (this.soldiersInside == 0) {
            this.player = null;
            System.out.println(this.name + " gave up their territory. Now it's neutral.");
        }
    }
    public void setSoldiersInside(int soldiers) {
        this.soldiersInside = soldiers;
    }

    public Player getOwner() {
        return this.owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public String getContinent() {
        return this.continent;
    }

    public int getSoldiersSend() {
        return this.soldiersSend;
    }

    public void setSoldiersSend(int soldiers) {
        this.soldiersSend = soldiers;
    }

    public void resetSoldiersSend() {
        this.soldiersSend = 0;
    }
}
