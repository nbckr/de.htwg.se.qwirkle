package de.htwg.qwirkle.aview.gui;

import de.htwg.qwirkle.controller.IQController;
import de.htwg.qwirkle.controller.IQControllerGui;
import util.Constants;
import util.observer.IObserver;
import util.observer.QEvent;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class OpPanel extends JPanel implements IObserver {

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
        Listener listener = new Listener();

        actionChoicePanel = new JPanel();
        addButton = new JButton("Add Tile to Grid");
        addButton.addMouseListener(listener);
        tradeButton = new JButton("Trade Tile(s)");
        tradeButton.addMouseListener(listener);
        actionChoicePanel.add(addButton);
        actionChoicePanel.add(tradeButton);
        add(actionChoicePanel);

        instructionPanel = new JPanel();
        instructionLabel = new JLabel();
        instructionPanel.add(instructionLabel);
        add(instructionPanel);

        confirmPanel = new JPanel();
        confirmButton = new JButton("OK");
        confirmButton.addMouseListener(listener);
        confirmPanel.add(confirmButton);
        add(confirmButton);

        // refresh instr.?
    }

    private void refreshInstructions() {
        switch (controller.getState()) {
            case ADDTILES:
                instructionLabel.setText(Constants.INSTR_ADD);
                break;
            case TRADETILES:
                instructionLabel.setText(Constants.INSTR_TRADE);
                break;
            default:
                instructionLabel.setText(Constants.INSTR_CHOOSE);
        }
    }

    @Override
    public void update(QEvent e) {
        refreshInstructions();
    }

    class Listener extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent e) {

            if (e.getSource() == addButton) {
                controller.setState(IQController.State.ADDTILES);
                confirmButton.setText(":-)");
                if (controller.getNumberOfSelectedTiles() > 1) {
                    controller.unselectAllTilesAtHand();
                }
            }

            if (e.getSource() == tradeButton) {
                controller.setState(IQController.State.TRADETILES);
            }

            if (e.getSource() == confirmButton
                    && controller.getState() == IQController.State.TRADETILES) {
                controller.tradeSelectedTiles();
                controller.refillCurrentAndGoToNextPlayer();
            }

            if (e.getSource() == confirmButton
                    && controller.getState() == IQController.State.ADDTILES) {
                // TODO
                controller.refillCurrentAndGoToNextPlayer();
            }
        }
    }
}
