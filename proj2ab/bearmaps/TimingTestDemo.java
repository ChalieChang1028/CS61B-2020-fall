package bearmaps;

import edu.princeton.cs.algs4.Stopwatch;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertEquals;

/**
 * Created by hug. Demonstrates how you can use either
 * System.currentTimeMillis or the Princeton Stopwatch
 * class to time code.
 */
public class TimingTestDemo {
    public static void main(String[] args) {
        /*long start = System.currentTimeMillis();
        int sum = 0;
        for (int i = 0; i < 100000; i += 1) {
            for (int j = 0; j < 10000; j += 1) {
                sum = sum + i + j;
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("Total time elapsed: " + (end - start)/1000.0 +  " seconds.");
*/
        List<Point> points = new ArrayList<>();
        Random random = new Random();
        random.setSeed(2);
        for(int i = 0; i < 1000000; i++) {
            double randX = random.nextDouble();
            double randY = random.nextDouble();
            points.add(new Point(randX, randY));
        }
        //NaivePointSet naivePointSet = new NaivePointSet(points);
        KDTree kdTree = new KDTree(points);


        Stopwatch sw = new Stopwatch();
        for(int j = 0; j < 1000000; j++) {
            double testRandX = random.nextDouble();
            double testRandY = random.nextDouble();
            //Point naiveBest = naivePointSet.nearest(testRandX, testRandY);
            Point kdTreeBest = kdTree.nearest(testRandX, testRandY);
        }

        System.out.println("Total time elapsed: " + sw.elapsedTime() +  " seconds.");
    }
}
