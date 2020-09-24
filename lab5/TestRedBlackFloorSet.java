import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertEquals;

/**
 * Created by hug.
 */
public class TestRedBlackFloorSet {
    @Test
    public void randomizedTest() {
       // TODO: YOUR CODE HERE
        RedBlackFloorSet s = new RedBlackFloorSet();
        AListFloorSet as = new AListFloorSet();
        for (int i = 0; i < 10000; i++) {
            double num = StdRandom.uniform(-5000.0, 5001.0);
            s.add(num);
            as.add(num);
        }
        for (int i = 0; i < 10000; i++) {
            double num = StdRandom.uniform(-5000.0, 5001.0);
            assertEquals(as.floor(num), s.floor(num), 0.000001);
        }

    }
}
