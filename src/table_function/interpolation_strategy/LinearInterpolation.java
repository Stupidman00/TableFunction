package table_function.interpolation_strategy;

import javafx.util.Pair;
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
        Pair<Double, Double> prev = new Pair<>(0.0, 0.0);
        Pair<Double, Double> next = new Pair<>(0.0, 0.0);
        double distancePrev = -1.0;
        double distanceNext = -1.0;

        for (double i : table.getTable().keySet()) {
            if (x >= i && (x - i < distancePrev || distancePrev < 0.0)) {
                distancePrev = x - i;
                prev = new Pair<>(i, table.getTable().get(i));
            }
            if (x <= i && (i - x < distanceNext || distanceNext < 0.0)) {
                distanceNext = i - x;
                next = new Pair<>(i, table.getTable().get(i));
            }
        }

        return (next.getValue() - prev.getValue()) * (x - prev.getKey()) /
                (next.getKey() - prev.getKey()) + prev.getValue();
    }
}
