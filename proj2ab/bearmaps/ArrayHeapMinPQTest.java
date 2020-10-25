package bearmaps;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import static org.junit.Assert.*;

public class ArrayHeapMinPQTest {
  /*  @Test
    public void testAddSize() {
        ArrayHeapMinPQ<Integer> minHeap = new ArrayHeapMinPQ<>();
        minHeap.add(999, 1);
        minHeap.add(888, 2);
        minHeap.add(777, 3);
        minHeap.add(666, 4);
        minHeap.add(555, 5);
        minHeap.add(444, 6);
        assertEquals(6, minHeap.size());
        assertEquals(999, (int) minHeap.removeSmallest());
        assertEquals(888, (int) minHeap.removeSmallest());
        assertEquals(4, minHeap.size());
        assertTrue(minHeap.contains(666));
        assertFalse(minHeap.contains(999));
        assertEquals(777, (int) minHeap.getSmallest());
        minHeap.changePriority(777, 7);
        minHeap.changePriority(444, 1);
        assertEquals(444, (int) minHeap.removeSmallest());
        assertEquals(666, (int) minHeap.removeSmallest());
    }*/

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        ExtrinsicMinPQ<Integer> minHeap = new ArrayHeapMinPQ<>();
        for (int i = 0; i < 200000; i += 1) {
            minHeap.add(i, 100000 - i);
        }
        long end = System.currentTimeMillis();
        System.out.println("Total time elapsed: " + (end - start) / 1000.0 +  " seconds.");

        long start2 = System.currentTimeMillis();
        for (int j = 0; j < 10000; j += 1) {
            minHeap.changePriority(StdRandom.uniform(0, minHeap.size()), j);
        }
        long end2 = System.currentTimeMillis();
        System.out.println("Total time elapsed: " + (end2 - start2) / 1000.0 +  " seconds.");

        long start3 = System.currentTimeMillis();
        for (int k = 0; k < 20000; k+= 1) {
            minHeap.removeSmallest();
        }
        long end3 = System.currentTimeMillis();
        System.out.println("Total time remove: " + (end3 - start3) / 1000.0 + "seconds. ");

    }
}