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
    private JPanel mainPanel;
    private GridPanel gridPanel;
    private HandPanel handPanel;
    private OpPanel opPanel;
    private StatusPanel statusPanel;
    private IQControllerGui controller;

    public QFrame (final IQControllerGui controller) {
        this.controller = controller;
        controller.addObserver(this);

        setJMenuBar(new MenuBar(controller));

        // Main Panel
        mainPanel = new JPanel();
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainPanel.setLayout(new FlowLayout());

        gridPanel = new GridPanel(controller);
        mainPanel.add(gridPanel);

        opPanel = new OpPanel(controller);
        mainPanel.add(opPanel);

        handPanel = new HandPanel(controller);
        mainPanel.add(handPanel);

        statusPanel = new StatusPanel(controller);
        mainPanel.add(statusPanel);





        // JFrame Settings
        setContentPane(mainPanel);
        setTitle("HTWG QWiRKLE");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(DEFAULT_WINDOWSIZE);
        setResizable(true);
        setVisible(true);

    }

    @Override
    public void update(QEvent e) {
        statusPanel.setText(controller.getStatusMessage());
        repaint();
    }
}
