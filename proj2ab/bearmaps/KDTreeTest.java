package bearmaps;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import java.util.Random;

public class KDTreeTest {
        @Test
        public void randomizedTest() {
                List<Point> points = new ArrayList<>();
                Random random = new Random();
                random.setSeed(2);
                for(int i = 0; i < 100000; i++) {
                        double randX = random.nextDouble();
                        double randY = random.nextDouble();
                        points.add(new Point(randX, randY));
                }
                NaivePointSet naivePointSet = new NaivePointSet(points);
                KDTree kdTree = new KDTree(points);
                for(int j = 0; j < 10000; j++) {
                        double testRandX = random.nextDouble();
                        double testRandY = random.nextDouble();
                        Point naiveBest = naivePointSet.nearest(testRandX, testRandY);
                        Point kdTreeBest = kdTree.nearest(testRandX, testRandY);
                        assertEquals(naiveBest, kdTreeBest);
                }
        }
}
