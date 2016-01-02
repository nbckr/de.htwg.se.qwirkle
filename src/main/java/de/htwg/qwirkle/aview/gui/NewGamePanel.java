package de.htwg.qwirkle.aview.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by niels on 02.01.2016.
 */
public class NewGamePanel extends JPanel implements ActionListener {

    private static final Dimension SIZE = new Dimension(300, 300);

    private JComboBox numPlayerBox;
    private final String[] NUM_PLAYER_OPTIONS = { "2", "3", "4" };
    private final int FIELDSIZE = 20;
    private int numPlayerChoice;

    private JTextField player1;
    private JTextField player2;
    private JTextField player3;
    private JTextField player4;

    public NewGamePanel() {
        setLayout(new FlowLayout());
        setSize(SIZE);

        numPlayerBox = new JComboBox(NUM_PLAYER_OPTIONS);
        numPlayerBox.setSelectedIndex(0);
        numPlayerBox.addActionListener(this);
        this.add(numPlayerBox);
        numPlayerChoice = Integer.parseInt(((String) numPlayerBox.getSelectedItem()));

        player1 = new JTextField(FIELDSIZE);
        player1.setBorder(BorderFactory.createTitledBorder("Player 1"));
        this.add(player1);

        player2 = new JTextField(FIELDSIZE);
        player2.setBorder(BorderFactory.createTitledBorder("Player 2"));
        this.add(player2);

        player3 = new JTextField(FIELDSIZE);
        player3.setBorder(BorderFactory.createTitledBorder("Player 3"));
        player3.setEnabled(false);
        this.add(player3);

        player4 = new JTextField(FIELDSIZE);
        player4.setBorder(BorderFactory.createTitledBorder("Player 4"));
        player4.setEnabled(false);
        this.add(player4);



    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == numPlayerBox) {
            JComboBox jcb = (JComboBox) e.getSource();
            numPlayerChoice = Integer.parseInt((String) jcb.getSelectedItem());

            if (numPlayerChoice < 4 ) {
                player4.setEnabled(false);
            }
            if (numPlayerChoice < 3 ) {
                player3.setEnabled(false);
            }

        }
    }
}
