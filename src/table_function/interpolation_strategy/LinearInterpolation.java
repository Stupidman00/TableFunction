package table_function.interpolation_strategy;

import table_function.Point;
import table_function.TableFunction;

/**
 * <p> This class implements the linear interpolation method. </p>
 */
public class LinearInterpolation implements InterpolationStrategy {
    /**
     * <p> Defines the value of the function on argument
     * of the domain of function. Uses the linear interpolation
     * method.</p>
     *
     * @param x the argument.
     * @param table interpolated function in table view.
     * @return the value of function.
     */
    @Override
    public double interpolate(double x, TableFunction table) {
        if (!table.isInRange(x)) throw new IllegalArgumentException();
        if (table.getTable().containsKey(x)) return table.getTable().get(x);
        double prevX = table.getTable().floorKey(x);
        double nextX = table.getTable().ceilingKey(x);
        Point prev = new Point(prevX, table.getTable().get(prevX));
        Point next = new Point(nextX, table.getTable().get(nextX));
        return (next.getY() - prev.getY()) * (x - prev.getX()) /
                (next.getX() - prev.getX()) + prev.getY();
    }
}