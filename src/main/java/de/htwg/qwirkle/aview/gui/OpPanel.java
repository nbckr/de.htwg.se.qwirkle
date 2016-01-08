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
    private JPanel confirmCancelPanel;
    private JLabel instructionLabel;
    private JButton addButton;
    private JButton tradeButton;
    private JButton confirmButton;
    private JButton cancelButton;
    private JButton finishAddingButton;

    public OpPanel(IQControllerGui controller) {

        this.controller = controller;
        controller.addObserver(this);

        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setBorder(Constants.INNER_BORDER);
        Listener listener = new Listener();

        actionChoicePanel = new JPanel();
        addButton = new JButton("Add Tile(s) to Grid");
        addButton.addMouseListener(listener);
        tradeButton = new JButton("Trade Tile(s)");
        tradeButton.addMouseListener(listener);
        actionChoicePanel.add(addButton);
        actionChoicePanel.add(tradeButton);
        add(actionChoicePanel);

        instructionPanel = new JPanel();
        instructionLabel = new JLabel("");
        instructionPanel.add(instructionLabel);
        add(instructionPanel);

        confirmCancelPanel = new JPanel();
        confirmButton = new JButton("OK");
        confirmButton.addMouseListener(listener);
        confirmCancelPanel.add(confirmButton);

        cancelButton = new JButton("Cancel");
        cancelButton.addMouseListener(listener);
        confirmCancelPanel.add(cancelButton);

        finishAddingButton = new JButton("Finish round");
        finishAddingButton.addMouseListener(listener);
        finishAddingButton.setVisible(false);
        confirmCancelPanel.add(finishAddingButton);

        add(confirmCancelPanel);
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

            // change state to ADDTILES
            if (e.getSource() == addButton) {
                controller.setState(IQController.State.ADDTILES);
                if (controller.getNumberOfSelectedTiles() > 1) {
                    controller.unselectAllTilesAtHand();
                }
            }

            // change state to TRADETILES
            if (e.getSource() == tradeButton) {
                controller.setState(IQController.State.TRADETILES);
            }

            // go back to state CHOOSE_ACTION
            if (e.getSource() == cancelButton) {
                controller.setState(IQController.State.CHOOSE_ACTION);
            }

            // trade tiles
            if (e.getSource() == confirmButton
                    && controller.getState() == IQController.State.TRADETILES) {
                controller.tradeSelectedTiles();
                controller.refillCurrentAndGoToNextPlayer();
            }

            // add tiles
            if (e.getSource() == confirmButton
                    && controller.getState() == IQController.State.ADDTILES
                    && controller.getNumberOfSelectedTiles() == 1
                    && controller.targetPositionOnGridIsSet()
                    && controller.getTileFromGrid(controller.getTargetPositionOnGrid()) == null) {

                controller.addSelectedTileToTargetPosition();
                controller.removeSelectedTilesFromCurrentPlayer();

                // now that adding started, can't be aborted; better do this via controller
                cancelButton.setVisible(false);
                finishAddingButton.setVisible(true);
                tradeButton.setEnabled(false);
                addButton.setEnabled(false);

                if (controller.getCurrentHandSize() == 0) {
                    finishShouldBeDoneInController();
                }
            }

            // finish adding
            if (e.getSource() == finishAddingButton) {
                finishShouldBeDoneInController();
            }
        }
    }

    public void finishShouldBeDoneInController() {
        controller.refillCurrentAndGoToNextPlayer();
        finishAddingButton.setVisible(false);
        cancelButton.setVisible(true);
        tradeButton.setEnabled(true);
        addButton.setEnabled(true);
    }
}
