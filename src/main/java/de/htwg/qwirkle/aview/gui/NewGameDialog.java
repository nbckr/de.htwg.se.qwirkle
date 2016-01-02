package de.htwg.qwirkle.aview.gui;

import de.htwg.qwirkle.controller.IQController;
import de.htwg.qwirkle.controller.IQControllerGui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.LinkedHashSet;

/**
 * Created by niels on 02.01.2016.
 */
public class NewGameDialog extends JDialog {

    IQControllerGui controller;
    NewGamePanel newGamePane;
    JOptionPane optionPane;

    public NewGameDialog(IQControllerGui controller) {
        super();
        this.controller = controller;
        this.setTitle("Start new game");
        this.setModal(true);

        this.newGamePane = new NewGamePanel();
        this.optionPane = new JOptionPane(newGamePane, JOptionPane.PLAIN_MESSAGE,
                JOptionPane.OK_CANCEL_OPTION);
        this.setContentPane(optionPane);

        this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                exitOrShowError();
            }
        });

        optionPane.addPropertyChangeListener(
                new PropertyChangeListener() {
                    public void propertyChange(PropertyChangeEvent e) {
                        String prop = e.getPropertyName();

                        if (NewGameDialog.this.isVisible()
                                && (e.getSource() == optionPane)
                                && (prop.equals(JOptionPane.VALUE_PROPERTY))) {
                            // TODO check input validity

                            JOptionPane e2 = (JOptionPane) e.getSource();
                            if ((Integer) e2.getValue() == JOptionPane.OK_OPTION) {
                                // TODO: controller start game
                                
                            } else if ((Integer) e2.getValue() == JOptionPane.CANCEL_OPTION) {
                                exitOrShowError();
                            }
                        }
                    }
                });

        this.pack();
        this.setResizable(false);
        this.setVisible(true);

        /*
        int value = ((Integer) optionPane.getValue()).intValue();
        if (value == JOptionPane.OK_OPTION) {
            System.out.println("okay!");

        } else if (value == JOptionPane.CANCEL_OPTION) {

            System.out.println("aborted!");
        }*/
    }

    private class NewGamePanel extends JPanel implements ActionListener {
        private JPanel choicePanel;
        private JPanel namesPanel;

        private JComboBox numPlayerBox;
        private final String[] NUM_PLAYER_OPTIONS = { "2", "3", "4" };
        private final int FIELDSIZE = 20;
        private int numPlayerChoice;

        private JTextField player1;
        private JTextField player2;
        private JTextField player3;
        private JTextField player4;
        private LinkedHashSet<JTextField> allPlayers;

        public NewGamePanel() {
            this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
            //this.setSize(SIZE);

            choicePanel = new JPanel();
            choicePanel.setLayout(new FlowLayout());
            choicePanel.setBorder((BorderFactory.createTitledBorder("Choose number of players")));

            numPlayerBox = new JComboBox(NUM_PLAYER_OPTIONS);
            numPlayerBox.setSelectedIndex(0);
            numPlayerBox.addActionListener(this);
            choicePanel.add(numPlayerBox);
            numPlayerChoice = Integer.parseInt(((String) numPlayerBox.getSelectedItem()));

            namesPanel = new JPanel();
            namesPanel.setLayout(new BoxLayout(namesPanel, BoxLayout.PAGE_AXIS));
            namesPanel.setBorder((BorderFactory.createTitledBorder("Enter players' names")));

            player1 = new JTextField(FIELDSIZE);
            player1.setBorder(BorderFactory.createTitledBorder("Player 1"));
            namesPanel.add(player1);

            player2 = new JTextField(FIELDSIZE);
            player2.setBorder(BorderFactory.createTitledBorder("Player 2"));
            namesPanel.add(player2);

            player3 = new JTextField(FIELDSIZE);
            player3.setBorder(BorderFactory.createTitledBorder("Player 3"));
            player3.setEnabled(false);
            namesPanel.add(player3);

            player4 = new JTextField(FIELDSIZE);
            player4.setBorder(BorderFactory.createTitledBorder("Player 4"));
            player4.setEnabled(false);
            namesPanel.add(player4);

            this.add(choicePanel);
            this.add(namesPanel);

            allPlayers = new LinkedHashSet<>();
            allPlayers.add(player1);
            allPlayers.add(player2);
        }

        public int getNumPlayerChoice() {
            return numPlayerChoice;
        }

        public String[] getPlayerNames() {
            ArrayList<String> playerNames = new ArrayList<>();
            for (JTextField tf : allPlayers) {
                playerNames.add(tf.toString());
            }
            return (String[]) playerNames.toArray();
        }

        @Override
        public void actionPerformed(ActionEvent e) {

            if (e.getSource() == numPlayerBox) {
                JComboBox jcb = (JComboBox) e.getSource();
                numPlayerChoice = Integer.parseInt((String) jcb.getSelectedItem());

                if (numPlayerChoice < 3 ) {
                    player3.setEnabled(false);
                    allPlayers.remove(player3);
                } else {
                    player3.setEnabled(true);
                    allPlayers.add(player3);
                }

                if (numPlayerChoice < 4 ) {
                    player4.setEnabled(false);
                    allPlayers.remove(player4);
                }
                else {
                    player4.setEnabled(true);
                    allPlayers.add(player4);
                }

            }
        }
    }

    private void exitOrShowError() {
        //if (controller.getState() == IQController.State.EMPTY) {
        if (true) {
            JOptionPane.showMessageDialog(this, "Please start a new game first", "Error!",
                    JOptionPane.ERROR_MESSAGE);
        } else {
            this.setVisible(false);
        }
    }
}