package util.state;

/**
 * Created by opuee on 20.01.16.
 */
public class Cross extends Shape {
    @Override
    public String getANSI() {
        return "CR";
    }

    @Override
    public String toString() {
        return "CROSS";
    }
}
