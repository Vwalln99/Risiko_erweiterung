package View;

import Config.Helper;
import Controller.SendArmyController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SendArmyView implements ActionListener {
    public static final int SEND_ARMIES_WIDTH = 300;
    public static final int QUESTION_HEIGHT = 100;
    public static final int INPUT_HEIGHT = 100;
    public static final int BUTTON_HEIGHT = 50;
    GridBagLayout sendLayout = new GridBagLayout();
    GridBagConstraints sendConstraints = new GridBagConstraints();
    JPanel sendPanel = new JPanel(sendLayout);

    JDialog dialog;
    JSpinner soldiersSend;
    JButton sendButton;
    JButton cancelButton;

    private final SendArmyController controller;
    private final BoardView parent;

    public SendArmyView(SendArmyController controller, BoardView parent) {
        this.controller = controller;
        this.parent = parent;
    }

    public void createSendWindow() {
        dialog = new JDialog(this.parent, "Send Armies", true);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setResizable(false);

        sendLayout.columnWidths = new int[] {SEND_ARMIES_WIDTH};
        sendLayout.rowHeights = new int[] {QUESTION_HEIGHT, INPUT_HEIGHT, BUTTON_HEIGHT};

        JLabel question = new JLabel("How many soldiers do you want to send from " + controller.getSendingCountry().getName() + " to " + controller.getReceivingCountry().getName() + " ?");

        JPanel soldiersPanel = new JPanel(new FlowLayout());
        soldiersSend = new JSpinner(new SpinnerNumberModel(1, 1, controller.getSendingCountry().getSoldiersInside() - 1, 1));
        soldiersSend.setSize(100, 100);
        soldiersPanel.add(soldiersSend);

        JPanel buttonPanel = new JPanel();
        sendButton = new JButton("Send");
        sendButton.addActionListener(this);
        sendButton.setActionCommand("send");
        buttonPanel.add(sendButton);
        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(this);
        cancelButton.setActionCommand("cancel");
        buttonPanel.add(cancelButton);

        sendPanel.add(question, Helper.buildBoardConstraints(sendConstraints,0,0,1,1));
        sendPanel.add(soldiersPanel, Helper.buildBoardConstraints(sendConstraints, 1,0,1,1));
        sendPanel.add(buttonPanel, Helper.buildBoardConstraints(sendConstraints, 2,0,1,1));

        dialog.setContentPane(sendPanel);
        dialog.pack();
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("send")) {
            System.out.println((int)soldiersSend.getValue());
            controller.sendArmy((int)soldiersSend.getValue());
            dialog.dispose();
        }
        else if(e.getActionCommand().equals("cancel")) {
            dialog.dispose();
        }
    }
}
