import javafx.util.Pair;

import java.util.Map;

/**
 * Created by Admin on 11.02.2017.
 */
public class TableFunctionImpl implements TableFunction {
    private Map<Double, Double> values;


    @Override
    public void add(double x, double y) {
        values.put(x,y);
    }

    @Override
    public void remove(double x, double y) {
        values.remove(x,y);
    }

    @Override
    public Map<Double, Double> getTable() {
        return values;
    }

    @Override
    public Pair<Double, Double> getValue(double x) {
        return new Pair(x, values.get(x));
    }

    @Override
    public double interpolate(double x) {
        return 0;
    }
}
