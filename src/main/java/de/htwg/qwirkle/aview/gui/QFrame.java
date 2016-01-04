package de.htwg.qwirkle.aview.gui;

import de.htwg.qwirkle.controller.IQControllerGui;
import de.htwg.qwirkle.model.Tile;
import javafx.scene.layout.Border;
import util.Constants;
import util.StretchIcon;
import util.observer.IObserver;
import util.observer.QEvent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class QFrame extends JFrame implements IObserver {

    private static final Dimension DEFAULT_WINDOWSIZE = new Dimension(1500, 900);
    private static final Dimension RIGID_AREA = new Dimension(10, 10);

    private JPanel mainPanel;
    private JPanel sidePanel;
    private LogoPanel logoPanel;
    private GridPanel gridPanel;
    private HandPanel handPanel;
    private PlayerScorePanel playerScorePanel;
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
        gridPanel.setPreferredSize(Constants.GRID_PANEL_SIZE);
        mainPanel.add(gridPanel);

        sidePanel = new JPanel();
        sidePanel.setLayout(new BoxLayout(sidePanel, BoxLayout.PAGE_AXIS));

        logoPanel = new LogoPanel();
        sidePanel.add(Box.createRigidArea(RIGID_AREA));
        sidePanel.add(logoPanel);

        playerScorePanel = new PlayerScorePanel(controller);
        //playerScorePanel.setPreferredSize(new Dimension(Constants.SIDE_PANEL_WIDTH, 100));
        sidePanel.add(Box.createRigidArea(RIGID_AREA));
        sidePanel.add(playerScorePanel);

        handPanel = new HandPanel(controller);
        //handPanel.setPreferredSize(new Dimension(Constants.SIDE_PANEL_WIDTH, 100));
        sidePanel.add(Box.createRigidArea(RIGID_AREA));
        sidePanel.add(handPanel);

        opPanel = new OpPanel(controller);
        ///opPanel.setPreferredSize(new Dimension(Constants.SIDE_PANEL_WIDTH, 150));
        sidePanel.add(Box.createRigidArea(RIGID_AREA));
        sidePanel.add(opPanel);

        statusPanel = new StatusPanel(controller);
        sidePanel.add(Box.createRigidArea(RIGID_AREA));
        //statusPanel.setPreferredSize(new Dimension(Constants.SIDE_PANEL_WIDTH, 40));
        sidePanel.add(statusPanel);

        sidePanel.setPreferredSize(new Dimension(Constants.SIDE_PANEL_WIDTH, 0));
        mainPanel.add(Box.createRigidArea(RIGID_AREA));
        mainPanel.add(sidePanel);

        // JFrame Settings
        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setSize(DEFAULT_WINDOWSIZE);
        pack();
        setResizable(true);
        setVisible(true);

        // open the New Game dialog
        new NewGameDialog(controller);
    }

    @Override
    public void update(QEvent e) {
        //repaint(50);
    }
}
