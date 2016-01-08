package de.htwg.qwirkle.aview.gui;

import de.htwg.qwirkle.controller.IQController;
import de.htwg.qwirkle.controller.IQControllerGui;
import de.htwg.qwirkle.model.Grid;
import util.observer.QEvent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * This is implemented as Label because Labels accept the
 * use of Icons, however it rather functions as a Panel.
 */
public class GridTileLabel extends AbstractTileLabel {

    private Grid.Position position;

    public GridTileLabel(Grid.Position position, IQControllerGui controller) {

        this.controller = controller;
        controller.addObserver(this);

        this.position = position;

        addComponentListener(this);
        addMouseListener(new Listener());

        update(new QEvent());
    }

    public GridTileLabel(int row, int col, IQControllerGui controller) {
        this(new Grid.Position(row, col), controller);
    }

    @Override
    public void refreshTile() {
        tile = controller.getTileFromGrid(position);
    }

    @Override
    public void refreshBorder() {
        if (controller.getState() == IQController.State.ADDTILES
                && controller.targetPositionOnGridIsSet()
                && position == controller.getTargetPositionOnGrid()) {
            setBorder(BORDER_TARGET);
        } else {
            setBorder(BORDER_PLAIN);
        }
    }

    class Listener extends MouseAdapter {

        // remember this tile when clicked in ADDTILES state
        @Override
        public void mousePressed(MouseEvent e) {

            if (controller.getState() == IQController.State.ADDTILES
                    && tile == null) {

                controller.setTargetPositionOnGrid(position);
            }
        }

        // hover empty fields when in ADDTILES state
        @Override
        public void mouseEntered(MouseEvent e) {
            if (controller.getState() == IQController.State.ADDTILES
                    && tile == null
                    && position != controller.getTargetPositionOnGrid()) {
                setBorder(BORDER_HOVER);
            }
        }

        @Override
        public void mouseExited(MouseEvent e) {
            if (controller.getState() == IQController.State.ADDTILES
                    && tile == null) {
                refreshBorder();
            }
        }
    }
}
