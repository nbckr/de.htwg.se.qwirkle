package de.htwg.qwirkle.aview.gui;

import de.htwg.qwirkle.controller.IQController;
import de.htwg.qwirkle.controller.IQControllerGui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

/**
 * Created by niels on 18.12.2015.
 */
public class MenuBar extends JMenuBar implements ActionListener {

    IQControllerGui controller;

    JMenu fileMenu;
    JMenuItem newMenuItem, quitMenuItem;

    JMenu helpMenu;
    JMenuItem instructionsMenuItem, aboutMenuItem;

    public MenuBar(IQControllerGui controller) {

        this.controller = controller;

        // File Menu
        fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F);

        newMenuItem = new JMenuItem("New");
        newMenuItem.addActionListener(this);
        fileMenu.add(newMenuItem);

        fileMenu.addSeparator();

        quitMenuItem = new JMenuItem("Quit");
        quitMenuItem.addActionListener(this);
        fileMenu.add(quitMenuItem);

        // Help Menu
        helpMenu = new JMenu("Help");
        helpMenu.setMnemonic(KeyEvent.VK_H);

        instructionsMenuItem = new JMenuItem("Instructions");
        instructionsMenuItem.addActionListener(this);
        helpMenu.add(instructionsMenuItem);

        aboutMenuItem = new JMenuItem("About");
        aboutMenuItem.addActionListener(this);
        helpMenu.add(aboutMenuItem);

        // Add Menus to MenuBar
        this.add(fileMenu);
        this.add(helpMenu);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == newMenuItem) {
            controller.create();
        }

        if (e.getSource() == quitMenuItem) {
            controller.exit();
        }

        if (e.getSource() == instructionsMenuItem) {
            // TODO
        }

        if (e.getSource() == aboutMenuItem) {
            // TODO
        }
    }
}