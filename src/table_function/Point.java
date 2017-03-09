package table_function;

/**
 * This class represents the pair of coordinates as a point.
 */
public final class Point {
    /**
     * The first coordinate.
     */
    private double x;

    /**
     * The second coordinate.
     */
    private double y;

    /**
     * Creates point from two coordinates.
     *
     * @param x the first coordinate.
     * @param y the second coordinate.
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Returns a first coordinate.
     *
     * @return a first coordinate.
     */
    public double getX() {
        return x;
    }

    /**
     * Returns a second coordinate.
     *
     * @return a second coordinate.
     */
    public double getY() {
        return y;
    }

    /**
     * <p>Compares a point with some object for equality.</p>
     *
     * @param o the reference object with which to compare.
     * @return <tt>true</tt> if this object is the same as the object argument.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Point point = (Point) o;

        return Double.compare(point.getX(), getX()) == 0 && Double.compare(point.getY(), getY()) == 0;
    }

    /**
     * <p>Returns a hash code value for the point.</p>
     *
     * @return a hash code value for this point.
     */
    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(getX());
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(getY());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    /**
     * <p>Returns a string representation of the point.</p>
     *
     * @return a string representation of the point.
     */
    @Override
    public String toString() {
        return "Point {" +
                "x = " + x +
                ", y = " + y +
                '}';
    }
}
