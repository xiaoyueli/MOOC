package week4;

import java.util.Comparator;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;

public class Solver {
    
    private MinPQ<SearchNode> minPQ;
    private MinPQ<SearchNode> minPQT;
    private final Board iniBoard;
    private int moves = 0;
    private boolean solved = false;
    private Stack<Board> solutionQ = new Stack<Board>();
    public Solver(Board initial) {
        // find a solution to the initial board (using the A* algorithm)
        if (initial == null) {
            throw new NullPointerException();
        }
        iniBoard = initial;
        minPQ = new MinPQ<SearchNode>(new PrioOfManhattan());
        minPQT = new MinPQ<SearchNode>(new PrioOfManhattan());
        SearchNode node = new SearchNode(iniBoard, null, 0);
        SearchNode nodeTwin = new SearchNode(iniBoard.twin(), null, 0);
        minPQ.insert(node);
        minPQT.insert(nodeTwin);
        slove();

    }
    
    private class SearchNode {
        private final Board board;
        private final SearchNode preNode;
        private final int move;
        private final int prio;
        public SearchNode(Board board, SearchNode preNode, int move) {
            this.board = board;
            this.preNode = preNode;
            this.move = move;
            this.prio = board.manhattan() + move;
        }
    }
    
    private class PrioOfManhattan implements Comparator<SearchNode> {
        public int compare(SearchNode node1, SearchNode node2) {
            if (node1 == null || node2 == null) {
                throw new NullPointerException();
            }
            if (node1.prio == node2.prio) {
                return 0;
            }
            else if (node1.prio < node2.prio) {
                return -1;
            }
            else return 1;
        }
    }
    
    public boolean isSolvable() {
        // is the initial board solvable?
        return solved;
    }
    
    public int moves() {
        // min number of moves to solve initial board; -1 if unsolvable
        return moves;
    }
    
    public Iterable<Board> solution() {
        // sequence of boards in a shortest solution; null if unsolvable
        if (solved) return solutionQ;
        else return null;
    }
    
    private void slove() {
        // sequence of boards in a shortest solution; null if unsolvable
        while (true) {
            SearchNode node = minPQ.min();
            if (node.board.isGoal()) {
                solved = true;
                moves = node.move;
                do {
                    solutionQ.push(node.board);
                    node = node.preNode;
                } while (node != null);
                break;
            }
            travelNeighbors(minPQ, node.move);

            SearchNode tNode = minPQT.min();
            if (tNode.board.isGoal()) {
                moves = -1;
                break;
            }
            travelNeighbors(minPQT, tNode.move);
        }
    }
    
    private void travelNeighbors(MinPQ<SearchNode> pq, int move) {
        // put each neighbor into minPQ.
        SearchNode curNode = pq.delMin();
        for (Board b: curNode.board.neighbors()) {
            if (curNode.preNode == null || !b.equals(curNode.preNode.board)) {
                SearchNode son = new SearchNode(b, curNode, move + 1);
                pq.insert(son);
            }
        }
    }
    
    public static void main(String[] args) {
        // solve a slider puzzle (given below)
    }
    
}
