/******************************************************************************
 *  Name:    Xiaoyue Li
 *  NetID:   N/A
 *  Precept: P01
 *
 *  Partner Name:    N/A
 *  Partner NetID:   N/A
 *  Partner Precept: N/A
 * 
 *  Description:  compute the sum of a graph
 ******************************************************************************/
package week1;

public class Outcast {
    
    
    private final WordNet wordnet;
    public Outcast(WordNet wordnet) {
        // constructor takes a WordNet object
        this.wordnet = wordnet;
        
    }
    
    public String outcast(String[] nouns) {  
        // given an array of WordNet nouns, return an outcast
        
        int[] disSum = new int[nouns.length];
        for (int idx = 0; idx < nouns.length; idx++) disSum[idx] = 0;
        
        for (int ori = 0; ori < nouns.length; ori ++ ) {
            for (int des = ori + 1; des < nouns.length; des++) {
                int dis = wordnet.distance(nouns[ori], nouns[des]);
                disSum[ori] += dis;
                disSum[des] += dis;
            }
        }
        
        int max = Integer.MIN_VALUE;
        int maxNoun = -1;
        for (int idx = 0; idx < nouns.length; idx++) {
            if (disSum[idx] > max) {
                max = disSum[idx];
                maxNoun = idx;
            }
        }
        
        
        return nouns[maxNoun];
    }
    
    public static void main(String[] args) {
        // see test client below
    }
 }
