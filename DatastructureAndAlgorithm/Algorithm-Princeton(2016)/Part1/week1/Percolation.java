package week1;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/**
 *  Using one WQUUF object with virtual top and an additional an array tracks the
 *  root's status of percolation for each component.
 *  Using a flag marks if the table is percolated. 
 *  Using a 2-D array tracks if the cell is opened.
 * 
 */

public class Percolation {
    private int[][] table;          // convert the 2-D coordinate to WQUUF object index.
    private boolean[][] tOpen;      // track if the cell is opened.
    private boolean[] tFull;        // track if the root of each component of WQUUF object is connected with bottom.
    private final int size;         // store the size of the table.
    private WeightedQuickUnionUF uftop;     // WQUUF object with one virtual top.
    private boolean isPer = false;          // track if the table is percolated.

    public Percolation(int n) {
        // create N-by-N grid, with all sites blocked

        if (n <= 0) {
            throw new IllegalArgumentException();
        }
        size = n;
        uftop = new WeightedQuickUnionUF(size * size + 1);
        tOpen = new boolean[size][size];
        table = new int[size][size];
        tFull = new boolean[size * size];
        int idx = 0;
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                tFull[idx] = false;
                table[row][col] = idx++;
                tOpen[row][col] = false;
            }
        }
    }
    
    public void open(int i, int j) {
        // open site (row i, column j) if it is not open already
        int row = i - 1;
        int col = j - 1;
        boolean per = false;
        if (row < 0 || row >= size || col < 0 || col >= size) {
            throw new IndexOutOfBoundsException();
        }
        
        if (!tOpen[row][col]) {
            tOpen[row][col] = true;
            
            if (row - 1 < 0) {
                // connect the cell with virtual top if it is on the first row.
                uftop.union(table[row][col], size * size);
            }
            else if (row - 1 >= 0 && tOpen[row - 1][col] && !uftop.connected(table[row][col], table[row-1][col])) {
                // get the percolation status of the top neighbor's root,
                // mark the local flag as true if the component the neighbor belonged is connected with bottom.
                int root = uftop.find(table[row - 1][col]);
                if (tFull[root]) {
                    per = true;
                }
                uftop.union(table[row][col], table[row - 1][col]);
            }
            
            if (col - 1 >= 0 && tOpen[row][col -1] && !uftop.connected(table[row][col], table[row][col - 1])) {
                // get the percolation status of the left neighbor's root,
                // mark the local flag as true if the component the neighbor belonged is connected with bottom.
                int root = uftop.find(table[row][col - 1]);
                if (tFull[root]) {
                    per = true;
                }                        
                uftop.union(table[row][col], table[row][col - 1]); 
            }
            
            if (col + 1 < size && tOpen[row][col + 1] && !uftop.connected(table[row][col], table[row][col + 1])) {
                // get the percolation status of the right neighbor's root,
                // mark the local flag as true if the component the neighbor belonged is connected with bottom.
                int root = uftop.find(table[row][col + 1]);
                if (tFull[root]) {
                    per = true;
                }
                uftop.union(table[row][col], table[row][col + 1]);
            }
            
            if (row + 1 < size && tOpen[row + 1][col] && !uftop.connected(table[row][col], table[row + 1][col])) {
                // get the percolation status of the bottom neighbor's root,
                // mark the local flag as true if the component the neighbor belonged is connected with bottom.
                int root = uftop.find(table[row + 1][col]);
                if (tFull[root]) {
                    per = true;
                }
                uftop.union(table[row][col], table[row + 1][col]);
            }    
            else if (row + 1 >= size) {             
                // set the percolation status of the cell's root as true,
                // if the cell is on the last row.
                int root = uftop.find(table[row][col]);
                tFull[root] = true;
                per = true;
                
            }
            
            // get the root of the new component the cell belonged,
            int root = uftop.find(table[row][col]);
            if (uftop.connected(root, size * size) && per) {
                // if the new component is connected with virtual top and local percolation flag is true,
                // update the global percolation flag as true.
                isPer = true;
            }
            else if (per) {
                // update the root's percolation status of the new component as true. 
                tFull[root] = true;
            }
        }
    }
    
    public boolean isOpen(int i, int j) {
        // is site (row i, column j) open?  
        int row = i - 1;
        int col = j - 1;
        if (row < 0 || row >= size || col < 0 || col >= size) {
            throw new IndexOutOfBoundsException();
        }
        return tOpen[row][col];
    }
    
    public boolean isFull(int i, int j) {
        // is site (row i, column j) full?
        int row = i - 1;
        int col = j - 1;
        if (row < 0 || row >= size || col < 0 || col >= size) {
            throw new IndexOutOfBoundsException();
        }
        return uftop.connected(table[row][col], size * size);
    }
    
    public boolean percolates() {
        // does the system percolate?
        return isPer;
    }
    
    public static void main(String[] args) {
        // test client (optional)
    }
}
