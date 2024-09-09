package Controller;

import Model.Country;
import Config.Helper;
import View.BoardView;
import View.CountryView;
import View.FightView;

import java.util.Arrays;
import java.util.Collections;

public class FightController {

    Country attackingCountry;
    CountryView attackingCountryView;

    Country defendingCountry;
    CountryView defendingCountryView;

    int attackingSoldiers;
    int defendingSoldiers;
    private final BoardController boardController;
    private final BoardView boardView;

    public FightController(BoardController boardController, BoardView boardView) {
        this.boardController = boardController;
        this.boardView = boardView;
    }

    public Country getAttackingCountry() {
        return attackingCountry;
    }
    public void setAttackingCountry(Country country) {
        attackingCountry = country;
    }
    public void setAttackingCountryView(CountryView view) {
        attackingCountryView = view;
    }
    public Country getDefendingCountry() {
        return defendingCountry;
    }
    public void setDefendingCountry(Country country) {
        defendingCountry = country;
    }
    public void setDefendingCountryView(CountryView view) {
        defendingCountryView = view;
    }
    public int getAttackingSoldiers() {
        return attackingSoldiers;
    }
    public void setAttackingSoldiers(int soldiers) {
        attackingSoldiers = soldiers;
    }
    public int getDefendingSoldiers() {
        return defendingSoldiers;
    }
    public void setDefendingSoldiers(int soldiers) {
        defendingSoldiers = soldiers;
    }

    public void createFightView() {
        new FightView(boardView, this).createFightWindow();
    }


    public boolean checkEnoughAttackers(int attackers) {
        return (attackingCountry.getSoldiersInside() - attackers) >= 1;
    }

    public boolean checkEnoughDefenders(int defenders) {
        return defendingCountry.getSoldiersInside() - defenders >= 0;
    }

    public int rollDice() {
        return (int)(Math.random() * 6) + 1;
    }

    // Rolls a dice for every attacker and defender and sorts them from high to low
    public void fight(FightView view) {
        Integer[] a_dice = new Integer[attackingSoldiers];
        for (int i = 0; i < attackingSoldiers; i++) {
            a_dice[i] = rollDice();
        }
        Arrays.sort(a_dice, Collections.reverseOrder());
        view.setAttackerDiceLabel("Attacker Roll: " + Helper.setLabelContent(a_dice));

        Integer[] d_dice = new Integer[defendingSoldiers];
        for (int i = 0; i < defendingSoldiers; i++) {
            d_dice[i] = rollDice();
        }
        Arrays.sort(d_dice, Collections.reverseOrder());
        view.setDefenderDiceLabel("Defender Roll: " + Helper.setLabelContent(d_dice));

        resolveDiceRolls(a_dice, d_dice, view);
    }

    // Compares attacker and defender dice values and resets variables for next fight
    public void resolveDiceRolls(Integer[] attackerDices, Integer[] defenderDices, FightView view) {
        int attackerLosses = 0;
        int defenderLosses = 0;
        for (int i = 0; i < defenderDices.length; i++) {
            if(attackerDices[i] > defenderDices[i]){
                defenderLosses++;
            } else {
                attackerLosses++;
            }
        }
        view.setAttackersLabel("Attackers: 0");
        view.setDefendersLabel("Defenders: 0");

        attackingSoldiers = 0;
        defendingSoldiers = 0;

        attackingCountry.removeSoldiersInside(attackerLosses);
        defendingCountry.removeSoldiersInside(defenderLosses);

        view.setAttackingCountrySoldiersLabel("Soldiers in Country: " + attackingCountry.getSoldiersInside());
        view.setDefendingCountrySoldiersLabel("Soldiers in Country: " + defendingCountry.getSoldiersInside());

        view.setAttackerLosses("Attacker loses: " + attackerLosses + " Soldier(s)");
        view.setDefenderLosses("Defender loses: " + defenderLosses + " Soldier(s)");
    }

    // Updates the state of the countries after an attack
    public void updatePanels() {
        // Check if an attack was successful
        if (defendingCountry.getSoldiersInside() == 0) {
            defendingCountry.setOwner(attackingCountry.getOwner());
            defendingCountry.addSoldiersInside(attackingCountry.getSoldiersInside() - 1);
            attackingCountry.setSoldiersInside(1);

            boardController.getCurrentPlayer().receiveRandomCard();
            boardController.getCurrentPlayer().checkAndTradeCards();
            int i = 0;
            boardView.setPlayerCardsButtonText(i,boardController.getCurrentPlayer().getName() + " Cards: " + boardController.getCurrentPlayer().getCards().size());

        }

        // Colors of countries will be set to their owners color
        attackingCountryView.updateCountryPanel();
        defendingCountryView.updateCountryPanel();

        boardController.checkWin();

        boardController.checkIfTooManyCards();

        attackingCountry = null;
        defendingCountry = null;
    }
}
