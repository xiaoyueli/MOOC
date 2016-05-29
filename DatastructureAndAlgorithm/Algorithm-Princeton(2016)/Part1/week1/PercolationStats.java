package week1;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.StdOut;


public class PercolationStats {
    
    private int size;
    private double[] result;
    
    public PercolationStats(int n, int t) {
        // perform T independent experiments on an N-by-N grid
        if (n <= 0 || t <= 0) {
            throw new IllegalArgumentException();
        }
        size = t;
        result = new double[size];
        for (int idx = 0; idx < size; idx++) {
            Percolation per = new Percolation(n);
            int cnt = 0;
            while (!per.percolates()) {
                
                int row = StdRandom.uniform(1, n + 1);
                int col = StdRandom.uniform(1, n + 1);
                if (!per.isOpen(row, col)) {
                    per.open(row, col);
                    cnt++;
                }
               
            }
            result[idx] = cnt * 1.0 / (n * n);
        }
    }
    
    public double mean() {
        // sample mean of percolation threshold
        return StdStats.mean(result);
    }
    
    public double stddev() {
        // sample standard deviation of percolation threshold
        return StdStats.stddev(result);
    }
    
    public double confidenceLo() {
        // low  endpoint of 95% confidence interval
        return mean() - 1.96 * stddev() / Math.sqrt(size);
    }
    
    public double confidenceHi() {
        // high endpoint of 95% confidence interval
        return mean() + 1.96 * stddev() / Math.sqrt(size);
    }
    
    public static void main(String[] args) {
        // test client (described below)
        int tableSize = 200;
        int testTimes = 100;
        PercolationStats ps = new PercolationStats(tableSize, testTimes);
        StdOut.println("mean                    = " + ps.mean());
        StdOut.println("stddev                  = " + ps.stddev());
        StdOut.println("95% confidence interval = " + ps.confidenceLo() + ", " + ps.confidenceHi());
    }
}
