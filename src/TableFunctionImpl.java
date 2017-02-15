import javafx.util.Pair;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Admin on 11.02.2017.
 */
public class TableFunctionImpl implements TableFunction {
    private final Map<Double, Double> table;

    TableFunctionImpl() {
        table = new HashMap<>();
    }

    @Override
    public void add(double x, double y) {
        if (!table.containsKey(x)) table.put(x, y);
    }

    @Override
    public void remove(double x) {
        table.remove(x);
    }

    @Override
    public Map<Double, Double> getTable() {
        return table;
    }

    @Override
    public Pair<Double, Double> findNearestPoint(double x) throws Exception {
        if (table.isEmpty()) throw new Exception("Table is empty.");
        Pair<Double, Double> result = new Pair<>(0.0, 0.0);
        double distance = 0.0;
        for (double i : table.keySet()) {
            if (i == x) return new Pair<>(i, table.get(i));
            if (distance == 0.0 || Math.abs(i - x) < distance) {
                distance = Math.abs(i - x);
                result = new Pair<>(i, table.get(i));
            }
        }
        return result;
    }

    @Override
    public double interpolate(double x) throws Exception {
        return findNearestPoint(x).getValue();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TableFunctionImpl that = (TableFunctionImpl) o;

        return table.equals(that.table);
    }

    @Override
    public int hashCode() {
        return table.hashCode();
    }

    @Override
    public String toString() {
        return "TableFunctionImpl{" +
                "table=" + table +
                '}';
    }
}
