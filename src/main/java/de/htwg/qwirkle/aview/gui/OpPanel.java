package de.htwg.qwirkle.aview.gui;

import de.htwg.qwirkle.controller.IQControllerGui;
import util.Constants;
import util.observer.IObserver;
import util.observer.QEvent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by niels on 18.12.2015.
 */
public class OpPanel extends JPanel implements IObserver {

    private static final Dimension SIZE = new Dimension(Constants.SIDE_PANEL_WIDTH, 100);
    private IQControllerGui controller;
    private JPanel actionChoicePanel;
    private JPanel instructionPanel;
    private JPanel confirmPanel;
    private JLabel instructionLabel;
    private JButton addButton;
    private JButton tradeButton;
    private JButton confirmButton;

    public OpPanel(IQControllerGui controller) {

        this.controller = controller;
        controller.addObserver(this);

        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setBorder(Constants.INNER_BORDER);
        setPreferredSize(SIZE);

        actionChoicePanel = new JPanel();
        addButton = new JButton("Add Tile to Grid");
        tradeButton = new JButton("Trade Tile(s)");
        actionChoicePanel.add(addButton);
        actionChoicePanel.add(tradeButton);
        add(actionChoicePanel);

        instructionPanel = new JPanel();
        instructionLabel = new JLabel();
        instructionLabel.setText(Constants.INSTR_CHOOSE);
        instructionPanel.add(instructionLabel);
        add(instructionPanel);

        confirmPanel = new JPanel();
        confirmButton = new JButton("OK");
        confirmPanel.add(confirmButton);
        add(confirmButton);

        addMouseListener(new Listener());
    }

    @Override
    public void update(QEvent e) {
        // necessary?
    }

    class Listener extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent e) {

            if (e.getSource() == confirmButton) {

            }
        }
    }
}
