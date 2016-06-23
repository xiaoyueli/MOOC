/******************************************************************************
 *  Name:    Xiaoyue Li
 *  NetID:   N/A
 *  Precept: P01
 *
 *  Partner Name:    N/A
 *  Partner NetID:   N/A
 *  Partner Precept: N/A
 * 
 *  Description:  rooted DAG, DFS, BFS
 ******************************************************************************/
package week1;

import java.util.HashMap;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.SET;

public class WordNet {
    
    private static final int MAX = Integer.MAX_VALUE;   //flag
    private static final int MIN = Integer.MIN_VALUE;   //flag
    private final HashMap<String, SET<Integer>> nounDic;    // noun as key, the indices of synsets as value
    private final HashMap<Integer, String> synsetMap;       // indices of synsets as key, the strings as value
    private final SAP sap;                                  // SAP object
    
    // constructor takes the name of the two input files
    public WordNet(String synsets, String hypernyms) {
        
        if (synsets == null || hypernyms == null) {
            throw new NullPointerException();
        }
        
        nounDic = new HashMap<String, SET<Integer>>();
        synsetMap = new HashMap<Integer, String>();
        In info = new In(synsets);
        String line = info.readLine();
        
        // read infomation of synsets file
        // store each noun of synset separately 
        while (line != null) {
            
            String[] synInfo = line.split(",");
            int synIndex = Integer.parseInt(synInfo[0]);
            synsetMap.put(synIndex, synInfo[1]);
            String[] nouns = synInfo[1].split(" ");
            for (int idx = 0; idx < nouns.length; idx++) {
                if (nounDic.containsKey(nouns[idx])) {
                    nounDic.get(nouns[idx]).add(synIndex);
                }
                else {
                    SET<Integer> set = new SET<Integer>();
                    set.add(synIndex);
                    nounDic.put(nouns[idx], set);
                }
            }
            line = info.readLine();
        }
        
        // read information of hypernyms file
        // build a graph of synsets according to that file
        Digraph graph = new Digraph(synsetMap.size()); 
        info = new In(hypernyms);
        line = info.readLine();
        while (line != null) {
            String[] hyperInfo = line.split(",");
            int synIdx = Integer.parseInt(hyperInfo[0]);
            for (int idx = 1; idx < hyperInfo.length; idx++) {
                graph.addEdge(synIdx, Integer.parseInt(hyperInfo[idx]));
            }
            line = info.readLine();
        }
        
        // examine if the graph if a rooted DAG
        // throw an exception if not
        if (!checkDAG(graph)) {
            throw new IllegalArgumentException();
        }
        
        // build a SAP object according to the graph built 
        sap = new SAP(graph);

    }
    
    // check if the graph is a rooted DAG, return false if not
    private boolean checkDAG(Digraph graph) {
        
        // build a array tracing the root of each vertex
        // meanwhile as a visited flag
        int[] roots = new int[synsetMap.size()];
        for (int idx = 0; idx < synsetMap.size(); idx++) roots[idx] = MAX;

        // if the vertex has not been visited, dfs it 
        for (int ver = 0; ver < synsetMap.size(); ver++) {
            if (roots[ver] == MAX) {
                dfs(roots, ver, graph);
            }
        }
        
        // check if every vertex has the same root
        for (int ver = 0; ver < synsetMap.size() - 1; ver++) {
            if (roots[ver] == -1 || roots[ver] != roots[ver + 1])
                return false;
        }
        return true;
    }
    
    private void dfs(int[] marked, int ver, Digraph graph) {
        
        // mark the vertex to MIN to imply it has been visited
        marked[ver] = MIN;
        
        // if the current vertex's outdegree is zero that implies it is a root
        // mark it
        if (graph.outdegree(ver) == 0) {
            marked[ver] = ver;
        }
        else {
            
            // if the current vertex's son has not been visited, dfs it
            // if the son has been visited but without a valid value means there is 
            // a cycle, mark the current vertex's root as -1
            // if the sons of the current vertex have different root value
            // mark current vertex's root as -1
            int root = -2;
            for (int next: graph.adj(ver)) {
                if (marked[next] == MAX) dfs(marked, next, graph);
                if (marked[next] == MIN) {
                    root = -1;
                }
                else {
                    if (root == -2) root = marked[next];
                    else if (root != marked[next]) root = -1;    
                }
            }            
            marked[ver] = root;
        }
    }

    // returns all WordNet nouns
    public Iterable<String> nouns() {
        return nounDic.keySet();
    }

    // is the word a WordNet noun?
    public boolean isNoun(String word) {
        
        if (word == null) {
            throw new NullPointerException();
        }
        
        return nounDic.containsKey(word);
    }

    // distance between nounA and nounB (defined below)
    public int distance(String nounA, String nounB) {
        
        return closestAnce(nounA, nounB)[0];
 
    }
    
    // compute the two nouns to other vertices's shortest path
    // find out the smallest length of the common ancestor by
    // add the length of each one to the common ancestors
    private int[] closestAnce(String nounA, String nounB) {
        
        if (!isNoun(nounA) || !isNoun(nounB)) {
            throw new IllegalArgumentException();
        }
        
        // get the indices of synsets each nouns belonged to
        SET<Integer> setA = nounDic.get(nounA);
        SET<Integer> setB = nounDic.get(nounB);
        int dis = sap.length(setA, setB);
        int ancestor = sap.ancestor(setA, setB);
        
        return new int[]{dis, ancestor};
    }

    public String sap(String nounA, String nounB) {
        
        int ancestor = closestAnce(nounA, nounB)[1];
        if (ancestor == -1) return null;      
        return synsetMap.get(ancestor);
    }

    // do unit testing of this class
    public static void main(String[] args) {
        
    }
 }
