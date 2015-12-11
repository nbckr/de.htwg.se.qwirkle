/*
Source: https://moodle.htwg-konstanz.de/moodle/course/view.php?id=713
 */

package util.observer;

public class QEvent {

    private Events events;

    public QEvent(Events e) {
        this.events = e;
    }

    public Events getEvent() {
        return this.events;
    }

    public enum Events {
        GET_PLAYER, // specifies number of players and initialize them
        MESSAGE, // print the controller message
        DEF, // default value
    }

}
