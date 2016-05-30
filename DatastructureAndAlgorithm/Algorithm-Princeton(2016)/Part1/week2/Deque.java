package week2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    
    private Node head;
    private int size;
    
    private class Node {
        private Item item;
        private Node pre;
        private Node next;
        
        Node() {
            item = null;
            pre = null;
            next = null;
        }
        
        Node(Item it) {
            this();
            item = it;
        }
        
        Node(Item it, Node pre, Node next) {
            this(it);
            this.pre = pre;
            this.next = next;
        }
    }
    
    public Deque() {
        // construct an empty deque
        head = new Node();
        head.next = head;
        head.pre = head;
        size = 0;
    }
    
    public boolean isEmpty() {
        // is the deque empty?
        return size == 0;
    }
    
    public int size() {
        // return the number of items on the deque
        return size;
    }
    
    public void addFirst(Item item) {
        // add the item to the front
        
        if (item == null) {
            throw new NullPointerException();
        }
        
        if (size == 0) {
            head.next = new Node(item, head, head);
            head.pre = head.next;
        }
        else {
            head.next = new Node(item, head, head.next);
            head.next.next.pre = head.next;
        }
        
        size++;
        
    }

    public void addLast(Item item) {
        // add the item to the end
        
        if (item == null) {
            throw new NullPointerException();
        }
        
        if (size == 0) {
            addFirst(item);
        }
        else {
            head.pre = new Node(item, head.pre, head);
            head.pre.pre.next = head.pre;
            size++;
        }
        
        
    }
    
    public Item removeFirst() {
        // remove and return the item from the front
        if (size == 0) {
            throw new NoSuchElementException();
        }

        Node temp = head.next;
        head.next = head.next.next;
        head.next.pre = head;
        size--;
        return temp.item;
    }
    
    public Item removeLast() {
        // remove and return the item from the end
        if (size == 0) {
            throw new NoSuchElementException();
        }
        
        Node temp = head.pre;
        head.pre = head.pre.pre;
        head.pre.next = head;
        size--;
        return temp.item;
    }
    
    private class DequeIterator implements Iterator<Item> {
        
        private Node cur = head.next;
        
        public boolean hasNext() {
            return cur.item != null;
        }
        
        public void remove() {
            throw new UnsupportedOperationException();
        }
        
        public Item next() {
            if (!hasNext()) {
               throw new NoSuchElementException(); 
            }
            Item temp = cur.item;
            cur = cur.next;
            return temp;
        }
    }
    
    public Iterator<Item> iterator() {
        // return an iterator over items in order from front to end
        return new DequeIterator();
    }
    
    public static void main(String[] args) {
        // unit testing
    }
    
 }
