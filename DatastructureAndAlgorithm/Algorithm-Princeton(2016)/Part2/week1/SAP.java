/******************************************************************************
 *  Name:    Xiaoyue Li
 *  NetID:   N/A
 *  Precept: P01
 *
 *  Partner Name:    N/A
 *  Partner NetID:   N/A
 *  Partner Precept: N/A
 * 
 *  Description:  compute the length of the shortest path between any tow vertices
 ******************************************************************************/
package week1;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.Queue;

public class SAP {
    
    private static final int MAX = Integer.MAX_VALUE;
    private final Digraph graph; 
    
    // constructor takes a digraph (not necessarily a DAG)
    public SAP(Digraph G) {
        graph = new Digraph(G.V());
        boolean[] marked = new boolean[G.V()];
        for (int idx = 0; idx < G.V(); idx++) marked[idx] = false;
        
        for (int ver = 0; ver < G.V(); ver++) {
            if (!marked[ver]) iniDFS(ver, marked, G, graph);
        }
    }
    
    // copy the old graph to the new graph by deep first search
    private void iniDFS(int ver, boolean[] visited, Digraph old, Digraph newg) {
        visited[ver] = true;
        for (int next: old.adj(ver)) {
            if (!visited[next]) {
                newg.addEdge(ver, next);
                iniDFS(next, visited, old, newg);
            }
            else {
                newg.addEdge(ver, next);
            }
        }
    }
    
    // length of shortest ancestral path between v and w; -1 if no such path
    public int length(int v, int w) {
        
        return ancestorInfo(v, w)[0];
    }
    
    // compute the common ancestor on the shortest pathway between the two vertices
    private int[] ancestorInfo(int v, int w) {
                
        int[] disV = disBFS(v);
        int[] disW = disBFS(w);
        
        int shorest = MAX;
        int ancestor = -1;
        for (int ver = 0; ver < graph.V(); ver++) {
            if (disV[ver] != MAX && disW[ver] != MAX && disV[ver] + disW[ver] < shorest) {
                shorest = disV[ver] + disW[ver];
                ancestor = ver;
            }
        }
        
        if (shorest == MAX) shorest = -1;
        
        return new int[]{shorest, ancestor};
    }
    
    // compute the distance of each vertex to the root vertex by broad first search
    private int[] disBFS(int ver) {
        Queue<Integer> que = new Queue<Integer>();
        
        int[] dis = new int[graph.V()];
        for (int idx = 0; idx < graph.V(); idx++) dis[idx] = MAX;
        
        // mark the root vertex's distance as zero
        que.enqueue(ver);
        dis[ver] = 0;
        while (!que.isEmpty()) {
            int ori = que.dequeue();
            for (int next: graph.adj(ori)) {
                
                // if the next vertex has not been visited, bfs it
                // if the current vertex to the next vertex is closer than 
                // other vertex to, update the next vertex's distance value
                // and enqueue it to update the related vertex distance value
                if (dis[next] == MAX || dis[ori] + 1 < dis[next]) {
                    dis[next] = dis[ori] + 1;
                    que.enqueue(next);
                }
            }
        }
        
        return dis;
    }

    // a common ancestor of v and w that participates in a shortest ancestral path; 
    // -1 if no such path
    public int ancestor(int v, int w) {
        
        return ancestorInfo(v, w)[1];
    }

    // length of shortest ancestral path between any vertex in
    // v and any vertex in w; 
    // -1 if no such path
    public int length(Iterable<Integer> v, Iterable<Integer> w) {

        return ancestorInfo(v, w)[0];
    }
    
    // compute the shortest way between any two vertex
    private int[] ancestorInfo(Iterable<Integer> v, Iterable<Integer> w) {
        
        if (v == null || w == null) {
            throw new NullPointerException();
        }
        
        int[] disMapV = disBFS(v);
        int[] disMapW = disBFS(w);
        
        int shorest = MAX;
        int ancestor = -1;
        for (int ver = 0; ver < graph.V(); ver++) {
            if (disMapV[ver] != MAX && disMapW[ver] != MAX  && 
                    disMapV[ver]+ disMapW[ver] < shorest) {
                shorest = disMapV[ver] + disMapW[ver];
                ancestor = ver;
            }
        }
        
        if (shorest == MAX) shorest = -1;
        return new int[]{shorest, ancestor};
        
    }
    
    // compute the distance of each other vertices to the given vertices
    // by seeing each given vertex all as root vertex
    private int[] disBFS(Iterable<Integer> oris) {
        Queue<Integer> que = new Queue<Integer>();
        int[] dis = new int[graph.V()];
        for (int idx = 0; idx < graph.V(); idx++) dis[idx] = MAX;
        
        // mark the distance of each given vertex as zero
        for (int ori: oris) {
            dis[ori] = 0;
            que.enqueue(ori);
        }
        
        // compute the distance between any other vertex to the given vertices
        // if there is a shorter way to a target vertex from any given vertices
        // mark the shorter way as the target vertex's distance
        while (!que.isEmpty()) {
            int ori = que.dequeue();
            for (int next: graph.adj(ori)) {
                if (dis[next] == MAX || dis[ori] + 1 < dis[next]) {
                    dis[next] = dis[ori] + 1;
                    que.enqueue(next);
                }
            }
        }
        
        return dis;
    }

    // a common ancestor that participates in shortest ancestral path; 
    // -1 if no such path
    public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {

        return ancestorInfo(v, w)[1];
    }

    // do unit testing of this class
    public static void main(String[] args) {
        

    }
 }

