package table_function;

import table_function.interpolation_strategy.InterpolationStrategy;
import java.util.*;

/**
 * <p>This class implements interface {@link TableFunction}.</p>
 */
public final class TableFunctionImpl implements TableFunction {
    /**
     * This variable represent the table function.
     */
    private final NavigableMap<Double, Double> table;

    /**
     * This constructor creates the empty table.
     */
    TableFunctionImpl() {
        table = new TreeMap<>();
    }

    /**
     * <p>This method add to table new pair argument-value. If table
     * contains such argument then it will be rewritten.</p>
     *
     * @param x the argument.
     * @param y the value.
     */
    @Override
    public void add(double x, double y) {
        table.put(x, y);
    }

    /**
     * <p>Removes from table a pair of argument-value for an argument
     * if it is present. </p>
     *
     * @param x the argument.
     */
    @Override
    public void remove(double x) {
        table.remove(x);
    }

    /**
     * <p>Checks the presence of pair argument-value in table.</p>
     *
     * @param x the argument whose presence in this table is to be tested.
     * @param y the value whose presence in this table is to be tested.
     * @return <tt>true</tt> if table contains such pair of argument-value
     *         else returns <tt>false</tt>.
     */
    @Override
    public boolean contains(double x, double y) {
        return table.containsKey(x) && table.get(x) == y;
    }

    /**
     * <p>Returns navigable map of pairs argument-value.</p>
     *
     * @return a navigable map of pairs argument-value.
     */
    @Override
    public NavigableMap<Double, Double> getTable() {
        return Collections.unmodifiableNavigableMap(table);
    }

    /**
     * <p>Looking for a pair of argument-value defined
     * in the table nearest to the given argument <tt>x</tt>.</p>
     *
     * @param x the argument value for searching.
     * @return a pair of argument-value nearest to the given argument.
     * @throws EmptyTableException if table is empty.
     */
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

    /**
     * <p>Defines the value of the function on argument
     * of the domain of a function.</p>
     *
     * @param x the argument value.
     * @param strategy the method of interpolation.
     * @return a value of the function.
     * @throws EmptyTableException if table is empty.
     */
    @Override
    public double interpolate(double x, InterpolationStrategy strategy)
            throws EmptyTableException
    {
        if (!isInRange(x)) throw new IllegalArgumentException();
        return strategy.interpolate(x, this);
    }

    /**
     * <p>Defines belongs the argument <tt>x</tt> to the domain of function.</p>
     *
     * @param x the argument value.
     * @return <tt>true</tt> if argument belongs to the domain of function
     *          and <tt>false</tt> if not.
     */
    @Override
    public boolean isInRange(double x) {
        return x >= table.firstKey() && x <= table.lastKey();
    }

    /**
     * <p>Removes all pairs argument-value from table.</p>
     */
    @Override
    public void clear() {
        table.clear();
    }

    /**
     * <p>Compares the table with some object for equality.</p>
     *
     * @param o the object for comparison.
     * @return <tt>true</tt> if the specified object is equal to this map.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TableFunctionImpl that = (TableFunctionImpl) o;

        return table.equals(that.table);
    }

    /**
     * <p>Returns the hash code value for this table function.</p>
     *
     * @return a hash code value for this table function.
     */
    @Override
    public int hashCode() {
        return table.hashCode();
    }

    /**
     * <p>Returns a string representation of the table function.</p>
     *
     * @return a string representation of the table function.
     */
    @Override
    public String toString() {
        return "TableFunction{" +
                "table = " + table +
                '}';
    }
}
