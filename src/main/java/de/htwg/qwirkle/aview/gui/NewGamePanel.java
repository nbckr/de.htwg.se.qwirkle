package de.htwg.qwirkle.aview.gui;

import javax.swing.*;
import javax.swing.text.html.ObjectView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedHashSet;

/**
 * Created by niels on 02.01.2016.
 */
public class NewGamePanel extends JPanel implements ActionListener {

    private static final Dimension SIZE = new Dimension(300, 300);

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
        this.setSize(SIZE);

        choicePanel = new JPanel();
        choicePanel.setLayout(new FlowLayout());
        choicePanel.setBorder((BorderFactory.createTitledBorder("Choose number of players")));

        numPlayerBox = new JComboBox(NUM_PLAYER_OPTIONS);
        numPlayerBox.setSelectedIndex(0);
        numPlayerBox.addActionListener(this);
        choicePanel.add(numPlayerBox);
        numPlayerChoice = Integer.parseInt(((String) numPlayerBox.getSelectedItem()));

        namesPanel = new JPanel();
        namesPanel.setLayout(new BoxLayout(namesPanel, BoxLayout.LINE_AXIS));
        namesPanel.setBorder((BorderFactory.createTitledBorder("Enter players' names")));

        player1 = new JTextField(FIELDSIZE);
        player1.set
        player1.setText("Player 1");
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
