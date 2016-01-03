package de.htwg.qwirkle.aview.gui;

import de.htwg.qwirkle.controller.IQController;
import de.htwg.qwirkle.controller.IQControllerGui;
import util.observer.IObserver;
import util.observer.QEvent;

import javax.swing.*;
import java.awt.*;

/**
 * Created by niels on 18.12.2015.
 */
public class QFrame extends JFrame implements IObserver {

    private static final Dimension DEFAULT_WINDOWSIZE = new Dimension(800, 1000);
    private static final Dimension SPACE_INBETWEEN = new Dimension(10, 0);
    private JPanel mainPanel;
    private JPanel sidePanel;
    private GridPanel gridPanel;
    private HandPanel handPanel;
    private OpPanel opPanel;
    private StatusPanel statusPanel;
    private IQControllerGui controller;

    public QFrame (final IQControllerGui controller) {
        super("HTWG QWIRKLE 2016");
        this.controller = controller;
        controller.addObserver(this);

        MenuBar menuBar = new MenuBar(controller);
        setJMenuBar(menuBar);

        // Main Panel
        mainPanel = new JPanel();
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.LINE_AXIS));

        gridPanel = new GridPanel(controller);
        mainPanel.add(gridPanel);

        sidePanel = new JPanel();
        sidePanel.setLayout(new BoxLayout(sidePanel, BoxLayout.PAGE_AXIS));
        mainPanel.add(Box.createRigidArea(SPACE_INBETWEEN));
        mainPanel.add(sidePanel);

        opPanel = new OpPanel(controller);
        sidePanel.add(opPanel);

        handPanel = new HandPanel(controller);
        sidePanel.add(handPanel);

        statusPanel = new StatusPanel(controller);
        sidePanel.add(statusPanel);


        // JFrame Settings
        setContentPane(mainPanel);
        //setTitle("HTWG QWiRKLE");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(DEFAULT_WINDOWSIZE);
        setResizable(true);
        setVisible(true);

        // open the New Game dialog
        menuBar.showNewGameDialog(controller);
    }



    @Override
    public void update(QEvent e) {
        statusPanel.setText(controller.getStatusMessage());
        repaint(50);
    }
}
