package tableFunction;

import tableFunction.TableFunction.*;

/**
 * <p>This interface using for representation of interpolation algorithms. </p>
 */
interface InterpolationStrategy {
    /**
     * <p>Method for algorithm selection.</p>
     *
     * @param type type of interpolation algorithm.
     * @return object of chosen algorithm.
     * @throws IllegalStateException if type not define.
     */
    static InterpolationStrategy chooseStrategy(Interpolation type) {
        switch (type) {
            case LINEAR:
                return new LinearInterpolation();
            case NEAREST_NEIGHBOUR:
                return new NearestNeighbourInterpolation();
            default:
                throw new IllegalStateException("No such interpolation strategy.");
        }
    }

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
            throws IllegalArgumentException, EmptyTableException;
}
