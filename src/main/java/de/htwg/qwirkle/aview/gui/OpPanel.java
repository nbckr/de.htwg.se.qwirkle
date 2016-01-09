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

    private IQControllerGui contr;
    private JPanel actionChoicePanel;
    private JPanel instructionPanel;
    private JPanel confirmCancelPanel;
    private JLabel instructionLabel;
    private JButton addButton;
    private JButton tradeButton;
    private JButton confirmButton;
    private JButton cancelButton;
    private JButton finishAddingButton;

    public OpPanel(IQControllerGui contr) {

        this.contr = contr;
        contr.addObserver(this);

        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setBorder(Constants.INNER_BORDER);

        actionChoicePanel = new JPanel();
        addButton = new JButton("Add Tile(s) to Grid");
        addButton.addMouseListener(new AddListener());
        tradeButton = new JButton("Trade Tile(s)");
        tradeButton.addMouseListener(new TradeListener());
        actionChoicePanel.add(addButton);
        actionChoicePanel.add(tradeButton);
        add(actionChoicePanel);

        instructionPanel = new JPanel();
        instructionLabel = new JLabel("");
        instructionPanel.add(instructionLabel);
        add(instructionPanel);

        confirmCancelPanel = new JPanel();
        confirmButton = new JButton("OK");
        confirmButton.addMouseListener(new ConfirmListener());
        confirmCancelPanel.add(confirmButton);

        cancelButton = new JButton("Cancel");
        cancelButton.addMouseListener(new CancelListener());
        confirmCancelPanel.add(cancelButton);

        finishAddingButton = new JButton("Finish round");
        finishAddingButton.addMouseListener(new FinishListener());
        finishAddingButton.setVisible(false);
        confirmCancelPanel.add(finishAddingButton);

        add(confirmCancelPanel);
    }

    private void refreshInstructions() {
        switch (contr.getState()) {
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

    class AddListener extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent e) {
            // change state to ADDTILES
            contr.setState(IQController.State.ADDTILES);
            if (contr.getNumberOfSelectedTiles() > 1) {
                contr.unselectAllTilesAtHand();
            }
        }
    }

    class TradeListener extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent e) {
            // change state to TRADETILES
            contr.setState(IQController.State.TRADETILES);
        }
    }

    class CancelListener extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent e) {
            // go back to state CHOOSE_ACTION
            contr.setState(IQController.State.CHOOSE_ACTION);
        }
    }

    class ConfirmListener extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent e) {
            // trade tiles
            if (contr.getState() == IQController.State.TRADETILES) {
                contr.tradeSelectedTiles();
                contr.refillCurrentAndGoToNextPlayer();
            }

            // add tiles
            if (contr.getState() == IQController.State.ADDTILES
                    && contr.getNumberOfSelectedTiles() == 1
                    && contr.targetPositionOnGridIsSet()
                    && contr.getTileFromGrid(contr.getTargetPositionOnGrid()) == null) {

                // check if adding tile to target position is legit
                if (!contr.validateAdding(contr.getSingleSelectedTile(), contr.getTargetPositionOnGrid())) {
                    JOptionPane.showMessageDialog(OpPanel.this, "Error: You can't add this tile here!");
                    return;
                }

                contr.addSelectedTileToTargetPosition();
                contr.removeSelectedTilesFromCurrentPlayer();

                // now that adding started, can't be aborted; better do this via contr
                cancelButton.setVisible(false);
                finishAddingButton.setVisible(true);
                tradeButton.setEnabled(false);
                addButton.setEnabled(false);

                if (contr.getCurrentHandSize() == 0) {
                    finishShouldBeDoneInController();
                }
            }
        }
    }

    class FinishListener extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent e) {
            // finish adding
            finishShouldBeDoneInController();
        }
    }

    public void finishShouldBeDoneInController() {
        contr.refillCurrentAndGoToNextPlayer();
        finishAddingButton.setVisible(false);
        cancelButton.setVisible(true);
        tradeButton.setEnabled(true);
        addButton.setEnabled(true);
    }
}
