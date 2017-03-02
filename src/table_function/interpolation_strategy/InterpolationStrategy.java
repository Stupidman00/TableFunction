package table_function.interpolation_strategy;

import table_function.TableFunction;
import table_function.TableFunction.*;

/**
 * <p>This interface using for representation of interpolation algorithms. </p>
 */
public interface InterpolationStrategy {

    /**
     * <p> Defines the value of the function on argument
     * of the domain of function.</p>
     *
     * @param x the argument.
     * @param table interpolated function in table view.
     * @return the value of function.
     * @throws EmptyTableException if table is empty.
     * @throws IllegalArgumentException if argument out of the domain of function.
     */
    double interpolate(double x, TableFunction table)
            throws EmptyTableException;
}
