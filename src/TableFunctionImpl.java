import javafx.util.Pair;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Admin on 11.02.2017.
 */
public class TableFunctionImpl implements TableFunction {
    private Map<Double, Double> values;

    TableFunctionImpl() {
        values = new HashMap<>();
    }

    @Override
    public void add(double x, double y) {
        if (!values.containsKey(x)) values.put(x, y);
    }

    @Override
    public void remove(double x, double y) {
        values.remove(x, y);
    }

    @Override
    public Map<Double, Double> getTable() {
        return values;
    }

    @Override
    public Pair<Double, Double> findNearestPoint(double x) throws Exception {
        if (values.isEmpty()) throw new Exception("Table is empty.");
        Pair<Double, Double> result = new Pair<>(0.0, 0.0);
        double distance = 0.0;
        for (double i : values.keySet()) {
            if (i == x) return new Pair<>(i, values.get(i));
            if (distance == 0.0 || Math.abs(i - x) < distance) {
                distance = Math.abs(i - x);
                result = new Pair<>(i, values.get(i));
            }
        }
        return result;
    }

    @Override
    public double interpolate(double x) throws Exception {
        return findNearestPoint(x).getValue();
    }
}
