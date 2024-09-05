package View;

import Controller.BoardController;
import Model.Country;
import Config.Helper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class CountryView implements MouseListener {

    JLabel soldierLabel;
    JLabel soldierIconLabel;
    ImageIcon soldierIcon = new ImageIcon(System.getProperty("user.dir") + "\\src\\Images\\soldier_icon.png");

    GridBagLayout countryLayout = new GridBagLayout();
    GridBagConstraints countryConstraints = new GridBagConstraints();
    JPanel countryPanel = new JPanel(countryLayout);

    public BoardController boardController;
    public Country country;

    public CountryView(BoardController boardController, Country country) {
        this.boardController = boardController;
        this.country = country;
    }

    public JPanel createCountry(Color borderColor, String countryName) {
        countryLayout.rowHeights = new int[] {20,10,55};
        countryLayout.columnWidths = new int[] {80};

        countryPanel.add(new JLabel(countryName, JLabel.CENTER), Helper.buildBoardConstraints(countryConstraints, 0,0,1,1));

        soldierLabel = new JLabel("Soldiers: 0");
        soldierLabel.setHorizontalAlignment(SwingConstants.CENTER);
        soldierLabel.setVerticalAlignment(SwingConstants.TOP);
        countryPanel.add(soldierLabel, Helper.buildBoardConstraints(countryConstraints, 1,0,1,1));

        soldierIconLabel = new JLabel();
        soldierIconLabel.setHorizontalAlignment(SwingConstants.CENTER);
        soldierIconLabel.setVerticalAlignment(SwingConstants.TOP);
        countryPanel.add(soldierIconLabel, Helper.buildBoardConstraints(countryConstraints, 2,0,2,1));

        countryPanel.setBorder(BorderFactory.createLineBorder(borderColor, 3));
        countryPanel.setOpaque(true);

        countryPanel.addMouseListener(this);
        return countryPanel;
    }

    // Updates the country after fights and fortifications
    public void updateCountryPanel() {
        setSoldierLabel("Soldiers: " + country.getSoldiersInside());
        if(country.getOwner() == boardController.getCurrentPlayer()) {
            setBackgroundColor(boardController.getCurrentPlayer().getPlayerColor());
        }
        setSoldierIcons(country.getSoldiersInside());
    }

    public void setSoldierLabel(String text) {
        soldierLabel.setText(text);
    }

    public void setSoldierIcons(int soldiersInside) {
        countryPanel.remove(soldierIconLabel);
        soldierIconLabel = new JLabel();
        soldierIconLabel.setLayout(new GridLayout(2,10));
        for (int i = 0; i < soldiersInside; i++) {
            JLabel icon = new JLabel(soldierIcon);
            soldierIconLabel.add(icon);
        }
        countryPanel.add(soldierIconLabel, Helper.buildBoardConstraints(countryConstraints, 2, 0, 2, 1));
    }

    public void setBackgroundColor(Color color) {
        countryPanel.setBackground(color);
    }

    // Sets correct border color for and after highlighting it
    public void setHighlight(boolean on) {
        if(on) {
            countryPanel.setBorder(BorderFactory.createLineBorder(Color.GREEN, 4));
        } else {
            switch (country.getContinent()) {
                case "A" -> countryPanel.setBorder(BorderFactory.createLineBorder(new Color(249,225,68), 3));
                case "B" -> countryPanel.setBorder(BorderFactory.createLineBorder(new Color(241,115,115),3));
                case "C" -> countryPanel.setBorder(BorderFactory.createLineBorder(new Color(99,189,89),3));
                case "D" -> countryPanel.setBorder(BorderFactory.createLineBorder(new Color(67,80,156),3));
                case "E" -> countryPanel.setBorder(BorderFactory.createLineBorder(new Color(120,85,10),3));
                case "F" -> countryPanel.setBorder(BorderFactory.createLineBorder(new Color(255,30,255),3));
                default -> countryPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(SwingUtilities.isLeftMouseButton(e)) {

            if(boardController.getPhase().equals("Set Soldiers") && (country.getOwner() == null || country.getOwner() == boardController.getCurrentPlayer())) {
                boardController.placeSoldiers(country, this);
            }
            else if(boardController.getPhase().equals("Attack Phase")){
                boardController.attackPhase(country, this);
            }
            else if (boardController.getPhase().equals(boardController.getCurrentPlayer().getName() + ": Set Soldiers") && country.getOwner() == boardController.getCurrentPlayer()) {
                boardController.setCardTroops( country, this);
            }
            else if (boardController.getPhase().equals("Fortification Phase")) {
                boardController.fortificationPhase(country, this);
            }
            else if (boardController.getPhase().equals("New Troops Phase")) {
                boardController.setNewTroops(country, this);
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(SwingUtilities.isRightMouseButton(e)){
            boardController.showHideNeighbors(country.getName(), true);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(SwingUtilities.isRightMouseButton(e)){
            boardController.showHideNeighbors(country.getName(), false);
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
}
