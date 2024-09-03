package View;

import Controller.FightController;
import Config.Helper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FightView implements ActionListener {
    public static final int ATTACK_WIDTH = 300;
    public static final int ATTACK_HEIGHT = 300;
    public static final int DICE_HEIGHT = 80;
    public static final int LOSSES_HEIGHT = 80;
    public static final int BUTTON_HEIGHT = 50;
    GridBagLayout fightLayout = new GridBagLayout();
    GridBagConstraints fightConstraints = new GridBagConstraints();
    JPanel fightPanel = new JPanel(fightLayout);

    JDialog frame;
    JLabel a_soldiers;
    JLabel a_attackers;
    JLabel d_soldiers;
    JLabel d_defenders;
    JLabel attackerDice;
    JLabel defenderDice;
    JLabel attackerLosses;
    JLabel defenderLosses;

    FightController controller;
    BoardView parent;

    public FightView( BoardView parent, FightController controller) {
        this.parent = parent;
        this.controller = controller;
    }

    public void createFightWindow() {
        frame = new JDialog(this.parent, "Fight", true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(false);

        fightLayout.rowHeights = new int[] {ATTACK_HEIGHT, DICE_HEIGHT, LOSSES_HEIGHT, BUTTON_HEIGHT};
        fightLayout.columnWidths = new int[] {ATTACK_WIDTH, ATTACK_WIDTH};

        JPanel attackingPanel = new JPanel(new GridLayout(4,1));
        attackingPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        JLabel a_countryName = new JLabel("Country: " + controller.getAttackingCountry().getName(), JLabel.CENTER);
        a_soldiers = new JLabel("Soldiers in Country: " + controller.getAttackingCountry().getSoldiersInside(), JLabel.CENTER);
        a_attackers = new JLabel("Attackers: 0", JLabel.CENTER);
        JPanel a_buttons = new JPanel();
        JButton a_one = new JButton("One");
        a_one.addActionListener(this);
        a_one.setActionCommand("a_one");
        JButton a_two = new JButton("Two");
        a_two.addActionListener(this);
        a_two.setActionCommand("a_two");
        JButton a_three = new JButton("Three");
        a_three.addActionListener(this);
        a_three.setActionCommand("a_three");
        a_buttons.add(a_one);
        a_buttons.add(a_two);
        a_buttons.add(a_three);
        attackingPanel.add(a_countryName);
        attackingPanel.add(a_soldiers);
        attackingPanel.add(a_attackers);
        attackingPanel.add(a_buttons);

        JPanel defendingPanel = new JPanel(new GridLayout(4,1));
        defendingPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        JLabel d_countryName = new JLabel("Country: " + controller.getDefendingCountry().getName(), JLabel.CENTER);
        d_soldiers = new JLabel("Soldiers in Country: " + controller.getDefendingCountry().getSoldiersInside(), JLabel.CENTER);
        d_defenders = new JLabel("Defenders: 0", JLabel.CENTER);
        JPanel d_buttons = new JPanel();
        JButton d_one = new JButton("One");
        d_one.addActionListener(this);
        d_one.setActionCommand("d_one");
        JButton d_two = new JButton("Two");
        d_two.addActionListener(this);
        d_two.setActionCommand("d_two");
        d_buttons.add(d_one);
        d_buttons.add(d_two);
        defendingPanel.add(d_countryName);
        defendingPanel.add(d_soldiers);
        defendingPanel.add(d_defenders);
        defendingPanel.add(d_buttons);

        JPanel a_dicePanel = new JPanel();
        a_dicePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        attackerDice = new JLabel("Attacker Roll:");
        a_dicePanel.add(attackerDice);

        JPanel d_dicePanel = new JPanel();
        d_dicePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        defenderDice = new JLabel("Defender Roll:");
        d_dicePanel.add(defenderDice);

        JPanel lossesPanel = new JPanel(new GridLayout(1,2));
        lossesPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        attackerLosses = new JLabel("", JLabel.CENTER);
        defenderLosses = new JLabel("", JLabel.CENTER);
        lossesPanel.add(attackerLosses);
        lossesPanel.add(defenderLosses);

        JPanel buttonPanel = new JPanel();
        JButton rollDice = new JButton("Roll Dice");
        rollDice.addActionListener(this);
        rollDice.setActionCommand("roll_dice");
        JButton exitFight = new JButton("Exit Fight");
        exitFight.addActionListener(this);
        exitFight.setActionCommand("exit_fight");
        buttonPanel.add(rollDice);
        buttonPanel.add(exitFight);

        fightPanel.add(attackingPanel, Helper.buildBoardConstraints(fightConstraints, 0,0,1,1));
        fightPanel.add(defendingPanel, Helper.buildBoardConstraints(fightConstraints, 0,1,1,1));
        fightPanel.add(a_dicePanel, Helper.buildBoardConstraints(fightConstraints,1,0,1,1));
        fightPanel.add(d_dicePanel, Helper.buildBoardConstraints(fightConstraints,1,1,1,1));
        fightPanel.add(lossesPanel, Helper.buildBoardConstraints(fightConstraints, 2, 0,1, 2));
        fightPanel.add(buttonPanel, Helper.buildBoardConstraints(fightConstraints,3,0,1,2));

        frame.setContentPane(fightPanel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void setAttackingCountrySoldiersLabel(String text) {
        a_soldiers.setText(text);
    }
    public void setAttackersLabel(String text) {
        a_attackers.setText(text);
    }
    public void setDefendingCountrySoldiersLabel(String text) {
        d_soldiers.setText(text);
    }
    public void setDefendersLabel(String text) {
        d_defenders.setText(text);
    }
    public void setAttackerDiceLabel(String text) {
        attackerDice.setText(text);
    }
    public void setDefenderDiceLabel(String text) {
        defenderDice.setText(text);
    }
    public void setAttackerLosses(String text) { attackerLosses.setText(text); }
    public void setDefenderLosses(String text) { defenderLosses.setText(text); }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("a_one") && controller.checkEnoughAttackers(1)) {
            controller.setAttackingSoldiers(1);
            setAttackersLabel("Attackers: " + controller.getAttackingSoldiers());
        }
        if(e.getActionCommand().equals("a_two") && controller.checkEnoughAttackers(2)) {
            controller.setAttackingSoldiers(2);
            setAttackersLabel("Attackers: " + controller.getAttackingSoldiers());
        }
        if(e.getActionCommand().equals("a_three") && controller.checkEnoughAttackers(3)) {
            controller.setAttackingSoldiers(3);
            setAttackersLabel("Attackers: " + controller.getAttackingSoldiers());
        }
        if(e.getActionCommand().equals("d_one") && controller.checkEnoughDefenders(1)) {
            controller.setDefendingSoldiers(1);
            setDefendersLabel("Defenders: " + controller.getDefendingSoldiers());
        }
        if(e.getActionCommand().equals("d_two") && controller.checkEnoughDefenders(2)) {
            controller.setDefendingSoldiers(2);
            setDefendersLabel("Defenders: " + controller.getDefendingSoldiers());
        }

        if(e.getActionCommand().equals("roll_dice") && (controller.getAttackingSoldiers() != 0 && controller.getDefendingSoldiers() != 0)) {
            controller.fight(this);
        }

        if (e.getActionCommand().equals("exit_fight")) {
            controller.updatePanels();
            frame.dispose();
        }
    }
}
