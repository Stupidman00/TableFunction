import javafx.util.Pair;

import java.util.Map;

/**
 * Created by StupidMan00 on 10.02.2017.
 */
public interface TableFunction {
    void add(double x, double y);
    void remove(double x, double y);
    Map<Double, Double> getTable();
    Pair<Double, Double> getValue(double x);
    double interpolate(double x);
}
