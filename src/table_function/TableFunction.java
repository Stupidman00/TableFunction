package table_function;

import javafx.util.Pair;
import table_function.interpolation_strategy.InterpolationStrategy;
import table_function.interpolation_strategy.LinearInterpolation;

import java.util.Map;

/**
 * <p> Interface for a tabular representation of the function. </p>
 * @author Victor Bachurihin
 */
public interface TableFunction {

    /**
     * <p> Exception class to represent situation of actions with empty table. </p>
     */
    class EmptyTableException extends Exception {}

    /**
     * <p> Adds in table new pair of argument-value.</p>
     * <p> If the table already have such argument it will be rewritten.</p>
     *
     * @param x argument.
     * @param y value.
     */
    void add(double x, double y);

    /**
     * <p> Removes from table a pair of argument-value for an argument
     * if it is present.</p>
     *
     * @param x argument.
     */
    void remove(double x);

    /**
     * <p>Checks the presence of pair argument-value in table.</p>
     *
     * @param x argument whose presence in this table is to be tested.
     * @param y value whose presence in this table is to be tested.
     * @return <tt>true</tt> if table contains such pair of argument-value
     *         else returns <tt>false</tt>.
     */
    boolean contains(double x, double y);

    /**
     * <p>Returns map of pairs argument-value.</p>
     *
     * @return map of pairs argument-value.
     */
    Map<Double, Double> getTable();

    /**
     * <p>Looking for a pair of argument-value defined
     * in the table nearest to the given argument <tt>x</tt>.</p>
     *
     * @param x argument value for searching.
     * @return pair of argument-value nearest to the given argument.
     */
    Pair<Double, Double> findNearestPoint(double x)
            throws EmptyTableException;

    /**
     * <p>Defines the value of the function on argument
     * of the region function definition.</p>
     *
     * <p>Uses linear interpolation.</p>
     *
     * @param x argument value.
     * @return value of the function.
     */
    default double interpolate(double x)
            throws EmptyTableException
    {
        return interpolate(x, new LinearInterpolation());
    }

    /**
     * <p>Defines the value of the function on argument
     * of the domain of a function.</p>
     *
     * @param x argument value.
     * @param strategy method of interpolation.
     * @return value of the function.
     */
    double interpolate(double x, InterpolationStrategy strategy)
            throws EmptyTableException;

    /**
     * <p>Defines belongs the argument <tt>x</tt> to the domain of function.</p>
     *
     * @param x argument value.
     * @return <tt>true</tt> if argument belongs to the domain of function
     *          and <tt>false</tt> if not.
     */
    boolean isInRange(double x);

    /**
     * <p>Removes all pairs argument-value from table.</p>
     */
    void clear();
}
