import javafx.util.Pair;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Admin on 11.02.2017.
 */
public class TableFunctionImplTest {
    @Test
    public void findNearestPoint() throws Exception {
        TableFunction table = new TableFunctionImpl();
        for (int i = -4; i < 5; i++) {
            table.add(i, i * i);
        }
        table.remove(3);
        assertEquals(new Pair<>(-4.0, 16.0), table.findNearestPoint(-5));
        assertEquals(new Pair<>(0.0, 0.0), table.findNearestPoint(-0.25));
        assertEquals(new Pair<>(0.0, 0.0), table.findNearestPoint(0.5));
        assertEquals(new Pair<>(2.0, 4.0), table.findNearestPoint(3));
    }

    @Test
    public void interpolate() throws Exception {
        TableFunction table = new TableFunctionImpl();
        for (int i = -4; i < 5; i++) {
            table.add(i, i * i);
        }
        assertEquals(16.0, table.interpolate(-19), 1.0E-05);
        assertEquals(0.0, table.interpolate(-0.25), 1.0E-05);
        assertEquals(1.0, table.interpolate(-0.5), 1.0E-05);
        assertEquals(16.0, table.interpolate(4.1), 1.0E-05);
    }

}