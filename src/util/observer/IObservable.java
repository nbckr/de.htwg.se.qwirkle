/*
Source: https://moodle.htwg-konstanz.de/moodle/course/view.php?id=713
 */

package util.observer;

public interface IObservable {

    void addObserver(IObserver s);

    void removeObserver(IObserver s);

    void removeAllObservers();

    void notifyObservers();

    void notifyObservers(Event e);
}