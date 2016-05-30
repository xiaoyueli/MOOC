package week2;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Subset {
    public static void main(String[] args) {
        
        int k = Integer.parseInt(args[0]);

        RandomizedQueue<String> rque = new RandomizedQueue<String>();
        while (!StdIn.isEmpty()) {
            String str = StdIn.readString(); 
            rque.enqueue(str);
            
        }
        
        while (k > 0) {
            StdOut.println(rque.dequeue());
            k--; 
        }
        

    }
 }
