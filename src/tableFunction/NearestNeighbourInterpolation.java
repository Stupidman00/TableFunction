package tableFunction;

/**
 * <p> This class implements the interpolation method to nearest neighbor. </p>
 */
class NearestNeighbourInterpolation implements InterpolationStrategy {
    /**
     * <p> Defines the value of the function on argument
     * of the domain of function. Uses the interpolation
     * method to nearest neighbor.</p>
     *
     * @param x the argument.
     * @param table interpolated function in table view.
     * @return the value of function.
     */
    @Override
    public double interpolate(double x, TableFunction table)
            throws TableFunction.EmptyTableException, IllegalArgumentException
    {
        return table.findNearestPoint(x).getValue();
    }
}
