package hw2;

import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;

public class PercolationStats {
    private int n, c;
    private double[] ans;

    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) {
            throw new java.lang.IllegalArgumentException();
        }
        n = N;
        c = T;
        ans = new double[c];
        for (int i = 0; i < c; i++) {
            Percolation p = pf.make(n);
            int count = 0;
            while (!p.percolates()) {
                int x = StdRandom.uniform(0, n);
                int y = StdRandom.uniform(0, n);
                if (!p.isOpen(x, y)) {
                    p.open(x, y);
                    count++;
                }
            }
            ans[i] = 1.0 * count / (n * n);
        }

    }

    public double mean() {
        return StdStats.mean(ans);
    }

    public double stddev() {
        return StdStats.stddev(ans);
    }

    public double confidenceLow() {
        return mean() - 1.96 * stddev() / Math.sqrt(c);
    }

    public double confidenceHigh() {
        return mean() + 1.96 * stddev() / Math.sqrt(c);
    }
}
