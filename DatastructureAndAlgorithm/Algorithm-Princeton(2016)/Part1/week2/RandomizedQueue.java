package week2;

import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {
    
    private Item[] randQue;
    private int size;
    
    public RandomizedQueue() {
        // construct an empty randomized queue
        randQue = (Item[]) new Object[1];
        size = 0;
    }
    
    public boolean isEmpty() {
        // is the queue empty?
        return size == 0;
    }
    
    public int size() {
        // return the number of items on the queue
        return size;
    }
    
    private void resizing(int length) {
        Item[] temp = (Item[]) new Object[length];
        for (int idx = 0; idx < size; idx++) {
            temp[idx] = randQue[idx];
        }
        randQue = temp;
    }
    
    public void enqueue(Item item) {
        // add the item
        if (item == null) {
            throw new NullPointerException();
        }
        if (size == randQue.length) {
           resizing(size * 2); 
        }
        randQue[size++] = item;
        
    }
    
    public Item dequeue() {
        // remove and return a random item
        if (size == 0) {
            throw new NoSuchElementException();
        }
        int idx = StdRandom.uniform(size);
        Item temp = randQue[idx];
        randQue[idx] = randQue[size - 1];
        randQue[--size] = null;
        
        if (size == randQue.length / 4 && size != 0) {
            resizing(randQue.length / 2);
        }
        return temp;
    }
    
    public Item sample() {
        // return (but do not remove) a random item
        if (size == 0) {
            throw new NoSuchElementException();
        }
        int idx = StdRandom.uniform(size);
        return randQue[idx];
    }
    
    private class RandQueIterator implements Iterator<Item> {
        
        private int amount = size;
        
        private Item[] copy;
        
        public RandQueIterator() {
            copy = (Item[]) new Object[size];
            for (int idx = 0; idx < size; idx++) {
                copy[idx] = randQue[idx];
            }
        }
        
        public boolean hasNext() {
            return amount != 0;
        }
        
        public void remove() {
            throw new UnsupportedOperationException();
        }
        
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            int idx = StdRandom.uniform(amount);
            Item temp = copy[idx];
            copy[idx] = copy[amount - 1];
            copy[--amount] = temp;
            return temp;
            
        }
        
    }
    
    public Iterator<Item> iterator() {
        // return an independent iterator over items in random order
        return new RandQueIterator();
    }
    
    public static void main(String[] args) {
        // unit testing
        RandomizedQueue<Integer> rq = new RandomizedQueue<Integer>();
        rq.enqueue(323);
        rq.enqueue(570);
        rq.enqueue(828);
        rq.enqueue(296);

        for (int i : rq) {
            System.out.println(i + " ");
            for (int j : rq) {
                System.out.print(j + " ");
            }
            System.out.println();
        }
       
        


    }
 }