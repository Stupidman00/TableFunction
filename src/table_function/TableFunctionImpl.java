package table_function;

import table_function.interpolation_strategy.InterpolationStrategy;

import java.util.*;

//TODO documentation to this class
public class TableFunctionImpl implements TableFunction {
    private final NavigableMap<Double, Double> table;

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
    public NavigableMap<Double, Double> getTable() {
        return Collections.unmodifiableNavigableMap(table);
    }

    @Override
    public Point findNearestPoint(double x)
            throws EmptyTableException
    {
        if (table.isEmpty()) throw new EmptyTableException();
        double prevKey = table.floorKey(x);
        double nextKey = table.ceilingKey(x);
        if (x - prevKey >= nextKey - x) {
            return new Point(nextKey, table.get(nextKey));
        } else {
            return new Point(prevKey, table.get(prevKey));
        }
    }

    @Override
    public double interpolate(double x, InterpolationStrategy strategy)
            throws EmptyTableException
    {
        if (!isInRange(x)) throw new IllegalArgumentException();
        return strategy.interpolate(x, this);
    }

    @Override
    public boolean isInRange(double x) {
        return x >= table.firstKey() && x <= table.lastKey();
    }

    @Override
    public void clear() {
        table.clear();
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
        return "TableFunction{" +
                "table = " + table +
                '}';
    }
}
