package util.state;

/**
 * Created by opuee on 20.01.16.
 */
public class Yellow extends Color {
    @Override
    public String getAnsiFG() {
        return "\u001B[33m";
    }

    @Override
    public String getAnsiBG() {
        return "\u001B[43m";
    }

    @Override
    public String toString() {
        return "YELLOW";
    }
}
