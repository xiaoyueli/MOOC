package week4;

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdRandom;

public class Board {
    
    private final int[][] board;
    private final int dimen;
    private int rBlock, cBlock;
    private boolean isGoal;
    
    public Board(int[][] blocks) {
        // construct a board from an N-by-N array of blocks
        // (where blocks[i][j] = block in row i, column j)
        dimen = blocks.length;
        board = new int[dimen][dimen];
        int orders = 1;
        isGoal = true;
        for (int row = 0; row < dimen; row++) {
            for (int col = 0; col < dimen; col++) {
            
                board[row][col] = blocks[row][col];
                if (blocks[row][col] == 0) {
                    rBlock = row;
                    cBlock = col;
                }
                else if (blocks[row][col] != orders) {
                    isGoal = false;
                }
                orders++;
            }
        }
    }
                                           
    public int dimension() {
        // board dimension N
        return dimen;
    }
    
    public int hamming() {
        // number of blocks out of place
        int orders = 1;
        int ham = -1;
        for (int row = 0; row < dimen; row++) {
            for (int col = 0; col < dimen; col++) {
                if (board[row][col] != orders++) {
                    ham++;
                }
            }
        }
        
        return ham;
    }
    
    public int manhattan() {
        // sum of Manhattan distances between blocks and goal

        int man = 0;
        
        int order = 1;
        for (int row = 0; row < dimen; row++) {
            for (int col = 0; col < dimen; col++) {
                int value = board[row][col];
                if (value != order && value != 0) {
                    int verValue = value / dimen;
                    if (value % dimen == 0) {
                        verValue -= 1;
                    }
                    int verOrder = order / dimen;
                    if (order % dimen == 0) {
                        verOrder -= 1;
                    }
                    
                    int herValue = value % dimen;
                    if (herValue == 0) {
                        herValue = dimen;
                    }
                    
                    int herOrder = order % dimen;
                    if (herOrder == 0) {
                        herOrder = dimen;
                    }
                    
                    man += Math.abs(verValue - verOrder) + Math.abs(herValue - herOrder); 
                }
                order++;
            }
        }
        return man;
    }
    
    public boolean isGoal() {
        // is this board the goal board?
        return isGoal;
    }
    
    public Board twin() {
        // a board that is obtained by exchanging any pair of blocks
        int[][] twined = copy();
        do {
            int row = StdRandom.uniform(dimen);
            int col = StdRandom.uniform(dimen);
            if (board[row][col] != 0) {
                int value = board[row][col];
                if (row - 1 >= 0 && board[row - 1][col] != 0) {
                    twined[row][col] = board[row - 1][col];
                    twined[row - 1][col] = value;
                    break;
                }       
            }
        } while (true);
        return new Board(twined);
    }
    
    private int[][] copy() {
        int[][] copyBoard = new int[dimen][dimen];
        for (int row = 0; row < dimen; row++) {
            for (int col = 0; col < dimen; col++) {
                copyBoard[row][col] = board[row][col];
            }
        }
        return copyBoard;
    }
    
    public boolean equals(Object y) {
        // does this board equal y?
        if (y == this) return true;
        if (y == null) return false;
        if (this.getClass() != y.getClass()) return false;
        int[][] that = ((Board) y).board;
        if (that.length != dimen) return false;
        for (int row = 0; row < dimen; row++) {
            for (int col = 0; col < dimen; col++) {
                if (board[row][col] != that[row][col]) {
                    return false;
                }
            }
        }
        return true;
    }
        
    public Iterable<Board> neighbors() {
        // all neighboring boards
        Stack<Board> pq = new Stack<Board>();
        
        Board neighbor;

        if (rBlock + 1 < dimen) {
            int[][] copy = copy();
            copy[rBlock][cBlock] = board[rBlock + 1][cBlock];
            copy[rBlock + 1][cBlock] = board[rBlock][cBlock];
            neighbor = new Board(copy);
            pq.push(neighbor);  
        }

        if (cBlock + 1 < dimen) {
            int[][] copy = copy();
            copy[rBlock][cBlock] = board[rBlock][cBlock + 1];
            copy[rBlock][cBlock + 1] = board[rBlock][cBlock];
            neighbor = new Board(copy);
            pq.push(neighbor);
        }

        if (rBlock - 1 >= 0) {
            int[][] copy = copy();
            copy[rBlock][cBlock] = board[rBlock - 1][cBlock];
            copy[rBlock - 1][cBlock] = board[rBlock][cBlock];
            neighbor = new Board(copy);
            pq.push(neighbor);
        }

        if (cBlock - 1 >= 0) {
            int[][] copy = copy();
            copy[rBlock][cBlock] = board[rBlock][cBlock - 1];
            copy[rBlock][cBlock - 1] = board[rBlock][cBlock];
            neighbor = new Board(copy);
            pq.push(neighbor);
        }

        return pq;
    }
    
    public String toString() {
        // string representation of this board (in the output format specified below)
        StringBuffer str = new StringBuffer(dimen + "\n");
        for (int row = 0; row < dimen; row++) {
            for (int col = 0; col < dimen; col++) {
                str.append(String.format("%2d", board[row][col]));
                if (col == dimen - 1) {
                    str.append("\n");
                }
                else {
                    str.append(" ");
                }
            }
        }
        return str.toString();
    }

    public static void main(String[] args) {
        // unit tests (not graded)
        int i = 12;
        System.out.printf("%2d", i);
    }
}
