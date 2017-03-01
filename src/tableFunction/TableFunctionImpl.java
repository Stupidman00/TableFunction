package tableFunction;

import javafx.util.Pair;
import java.util.*;

//TODO documentation to this class
public class TableFunctionImpl implements TableFunction {
    private final SortedMap<Double, Double> table;

    TableFunctionImpl() {
        table = new TreeMap<>();
    }

    @Override
    public void add(double x, double y) {
        table.put(x, y);
    }

    @Override
    public void remove(double x) {
        table.remove(x);
    }

    @Override
    public boolean contains(double x, double y) {
        return table.containsKey(x) && table.get(x) == y;
    }

    @Override
    public Map<Double, Double> getTable() {
        return Collections.unmodifiableSortedMap(table);
    }

    @Override
    public Pair<Double, Double> findNearestPoint(double x)
            throws EmptyTableException, IllegalArgumentException
    {
        if (table.isEmpty()) throw new EmptyTableException();
        double prevKey = table.headMap(x).lastKey();
        double nextKey = table.tailMap(x).firstKey();
        if (x - prevKey >= nextKey - x) {
            return new Pair<>(nextKey, table.get(nextKey));
        } else {
            return new Pair<>(prevKey, table.get(prevKey));
        }
    }

    @Override
    public double interpolate(double x, Interpolation type)
            throws IllegalArgumentException, EmptyTableException {
        if (!isInRange(x)) throw new IllegalArgumentException();
        InterpolationStrategy strategy = InterpolationStrategy.chooseStrategy(type);
        return strategy.interpolate(x, this);
    }

    @Override
    public boolean isInRange(double x) {
        return x >= table.firstKey() && x <= table.lastKey();
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
        return "tableFunction.TableFunctionImpl{" +
                "table=" + table +
                '}';
    }
}
