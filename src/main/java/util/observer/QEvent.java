/*
Source: https://moodle.htwg-konstanz.de/moodle/course/view.php?id=713
 */

package util.observer;

public class QEvent {

    private Events _e;

    public QEvent(Events e) {
        this._e = e;
    }

    public Events getEvent() {
        return this._e;
    }

    public enum Events {
        getPlayer, // specifies number of players and initialize them
        message, // print the controller message
        def, // default value
    }

}
