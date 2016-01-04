package de.htwg.qwirkle.aview.gui;

import de.htwg.qwirkle.controller.IQController;
import de.htwg.qwirkle.controller.IQControllerGui;
import de.htwg.qwirkle.model.Player;
import util.JTextFieldLimit;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by niels on 02.01.2016.
 */
public class NewGameDialog extends JDialog {

    private static final String[] NUM_PLAYER_OPTIONS = { "2", "3", "4" };
    private static final int FIELDSIZE = 15;

    IQControllerGui controller;
    NewGamePanel newGamePane;
    JOptionPane optionPane;
    List<Player> players;

    public NewGameDialog(IQControllerGui controller) {
        super();
        this.setLocationByPlatform(true);
        this.controller = controller;
        this.setTitle("Start new game");
        this.setModal(true);

        this.newGamePane = new NewGamePanel();
        this.optionPane = new JOptionPane(newGamePane, JOptionPane.PLAIN_MESSAGE,
                JOptionPane.OK_CANCEL_OPTION);
        this.setContentPane(optionPane);

        this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                exitOrShowError();
            }
        });

        optionPane.addPropertyChangeListener(
                new PropertyChangeListener() {
                    @Override
                    public void propertyChange(PropertyChangeEvent e) {
                        String prop = e.getPropertyName();

                        if (NewGameDialog.this.isVisible()
                                && (e.getSource() == optionPane)
                                && (prop.equals(JOptionPane.VALUE_PROPERTY))) {
                            // TODO check input validity

                            JOptionPane e2 = (JOptionPane) e.getSource();
                            if ((Integer) e2.getValue() == JOptionPane.OK_OPTION) {
                                startGame();

                            } else if ((Integer) e2.getValue() == JOptionPane.CANCEL_OPTION) {
                                exitOrShowError();
                            }
                        }
                    }
                });

        this.pack();
        this.setResizable(false);
        this.setVisible(true);
    }

    private class NewGamePanel extends JPanel implements ActionListener {
        private JPanel choicePanel;
        private JPanel namesPanel;

        private JComboBox numPlayerBox;
        private int numPlayerChoice;

        private JTextField player1;
        private JTextField player2;
        private JTextField player3;
        private JTextField player4;

        public NewGamePanel() {
            this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

            choicePanel = new JPanel();
            choicePanel.setLayout(new FlowLayout());
            choicePanel.setBorder(BorderFactory.createTitledBorder("Choose number of players"));

            numPlayerBox = new JComboBox(NUM_PLAYER_OPTIONS);
            numPlayerBox.setSelectedIndex(0);
            numPlayerBox.addActionListener(this);
            choicePanel.add(numPlayerBox);
            numPlayerChoice = Integer.parseInt((String) numPlayerBox.getSelectedItem());

            namesPanel = new JPanel();
            namesPanel.setLayout(new BoxLayout(namesPanel, BoxLayout.PAGE_AXIS));
            namesPanel.setBorder(BorderFactory.createTitledBorder("Enter players' names"));

            player1 = new JTextField(FIELDSIZE);
            player1.setDocument(new JTextFieldLimit(FIELDSIZE));
            player1.setBorder(BorderFactory.createTitledBorder("Player 1"));
            namesPanel.add(player1);

            player2 = new JTextField(FIELDSIZE);
            player2.setDocument(new JTextFieldLimit(FIELDSIZE));
            player2.setBorder(BorderFactory.createTitledBorder("Player 2"));
            namesPanel.add(player2);

            player3 = new JTextField(FIELDSIZE);
            player3.setDocument(new JTextFieldLimit(FIELDSIZE));
            player3.setBorder(BorderFactory.createTitledBorder("Player 3"));
            player3.setEnabled(false);
            namesPanel.add(player3);

            player4 = new JTextField(FIELDSIZE);
            player4.setDocument(new JTextFieldLimit(FIELDSIZE));
            player4.setBorder(BorderFactory.createTitledBorder("Player 4"));
            player4.setEnabled(false);
            namesPanel.add(player4);

            this.add(choicePanel);
            this.add(namesPanel);
        }

        public int getNumPlayerChoice() {
            return numPlayerChoice;
        }

        public String[] getPlayerNames() {
            String[] playerNames = new String[numPlayerChoice];

            playerNames[0] = player1.getText().isEmpty() ? "Player 1" : player1.getText();
            playerNames[1] = player2.getText().isEmpty() ? "Player 2" : player2.getText();
            if (playerNames.length > 2) {
                playerNames[2] = player3.getText().isEmpty() ? "Player 3" : player3.getText();
            }
            if (playerNames.length > 3) {
                playerNames[3] = player4.getText().isEmpty() ? "Player 4" : player4.getText();
            }
            return playerNames;
        }

        @Override
        public void actionPerformed(ActionEvent e) {

            if (e.getSource() == numPlayerBox) {
                JComboBox jcb = (JComboBox) e.getSource();
                numPlayerChoice = Integer.parseInt((String) jcb.getSelectedItem());

                if (numPlayerChoice < 3 ) {
                    player3.setEnabled(false);
                } else {
                    player3.setEnabled(true);
                }

                if (numPlayerChoice < 4 ) {
                    player4.setEnabled(false);
                }
                else {
                    player4.setEnabled(true);
                }

            }
        }
    }

    private void exitOrShowError() {
        if (controller.getState() == IQController.State.UNINIZIALIZED) {
            int value = JOptionPane.showConfirmDialog(this, "You haven't started a game " +
                    "yet. Do you want to quit?", "Error!", JOptionPane.YES_NO_OPTION);
            if (value == JOptionPane.YES_OPTION) {
                controller.exit();
            }
        } else {
            this.setVisible(false);
        }
    }

    private void startGame() {
        String[] playerNames = newGamePane.getPlayerNames();
        players = new ArrayList<Player>(playerNames.length);
        for (String name : playerNames) {
            players.add(new Player(name));
        }
        controller.init(players);
        controller.create();
        this.setVisible(false);
    }
}