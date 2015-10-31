package de.htwg.qwirkle.controller;

import de.htwg.qwirkle.model.Grid;
import util.observer.Observable;

import java.util.TreeMap;

public class QController extends Observable {

    private String statusMessage;
    private Grid grid;




    public QController(int i) {
    }

    public void create() {
    }

    public String getGridString() {
        return grid.toString();
    }

    public String getStatus() {
        return statusMessage;
    }
}
