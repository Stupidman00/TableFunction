package table_function;

import javafx.util.Pair;
import org.junit.Test;
import table_function.interpolation_strategy.NearestNeighbourInterpolation;

import static org.junit.Assert.*;

public class TableFunctionImplTest {
    @Test
    public void findNearestPoint() throws Exception {
        TableFunction table = new TableFunctionImpl();
        for (int i = -4; i < 5; i++) {
            table.add(i, i * i);
        }
        table.remove(3);
        assertEquals(new Pair<>(-4.0, 16.0), table.findNearestPoint(-3.9));
        assertEquals(new Pair<>(0.0, 0.0), table.findNearestPoint(-0.25));
        assertEquals(new Pair<>(1.0, 1.0), table.findNearestPoint(0.5));
        assertEquals(new Pair<>(4.0, 16.0), table.findNearestPoint(3));
    }

    @Test
    public void interpolate() throws Exception {
        TableFunction table = new TableFunctionImpl();
        for (int i = -4; i < 5; i++) {
            table.add(i, i * i);
        }
        assertEquals(16.0, table.interpolate(-3.7, new NearestNeighbourInterpolation()), 1.0E-05);
        assertEquals(1.0, table.interpolate(-0.65, new NearestNeighbourInterpolation()), 1.0E-05);
        assertEquals(0.0, table.interpolate(-0.5, new NearestNeighbourInterpolation()), 1.0E-05);
        assertEquals(16.0, table.interpolate(3.9, new NearestNeighbourInterpolation()), 1.0E-05);
        assertEquals(9.0, table.interpolate(-3.0, new NearestNeighbourInterpolation()), 1.0E-05);
        assertEquals(16.0, table.interpolate(-4.0, new NearestNeighbourInterpolation()), 1.0E-05);
        assertEquals(16.0, table.interpolate(4.0, new NearestNeighbourInterpolation()), 1.0E-05);

        table.clear();
        table.add(-4,1);
        table.add(-2,4);
        table.add(1,7);
        table.add(3,3);
        table.add(5,1);
        table.add(6,6);
        assertEquals(2.5, table.interpolate(-3), 1.0E-05);
        assertEquals(5.0, table.interpolate(2), 1.0E-05);
        assertEquals(5.0, table.interpolate(5.8), 1.0E-05);
        assertEquals(7.0, table.interpolate(1), 1.0E-05);
        assertEquals(1.0, table.interpolate(-4), 1.0E-05);
        assertEquals(6.0, table.interpolate(6), 1.0E-05);
    }
}