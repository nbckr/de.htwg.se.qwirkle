package de.htwg.qwirkle.controller;

import de.htwg.qwirkle.aview.gui.QFrame;

/**
 * Created by niels on 18.12.2015.
 */
public interface IQControllerGui extends IQController{
    QFrame getGuiMainFrame();

    void setGuiMainFrame(QFrame qFrame);
}
