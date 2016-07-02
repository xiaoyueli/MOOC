package week3;

import java.util.Arrays;
import edu.princeton.cs.algs4.StdOut;

public class BruteCollinearPoints {
    
    private Node head = new Node();     // linked list for line segments containing 4 points
    private int size = 0;               // total number of satisfied line segments.
    
    /**
     * Single linked list structure for storing line segment object.
     */
    private class Node {
        private LineSegment item;
        private Node next;
        
        Node(LineSegment ls) {
            item = ls;
            next = null;
        }    
        Node() {
            item = null;
            next = null;
        }
    }
    
    /**
     * Examines 4 points exactly once by 4 nested loops.
     * If the slopes of one point to the three others are equal, then the four points
     * are collinear.
     * If the line segment order is p1->p2->p3->p4, adds either p1->p4 or p4->p1 to the collection.
     */
    public BruteCollinearPoints(Point[] points) {
        // finds all line segments containing 4 points
        if (points == null) {
            throw new NullPointerException();
        }
        Point[] copy = new Point[points.length];
        for (int idx = 0; idx < points.length; idx++) {
            copy[idx] = points[idx];
        }
        
        Arrays.sort(copy);  // sort points by position to guarantee the first point examined is lowest
                            // and the last point examined is highest.
        Node rear = head;
        for (int p1 = 0; p1 < copy.length; p1++) {
            for (int p2 = p1 + 1; p2 < copy.length; p2++) {
                if (copy[p1].compareTo(copy[p2]) == 0) {
                    throw new IllegalArgumentException();
                }
                for (int p3 = p2 + 1; p3 < copy.length; p3++) {
                    if (copy[p2].compareTo(copy[p3]) == 0) {
                        throw new IllegalArgumentException();
                    }
                    for (int p4 = p3 + 1; p4 < copy.length; p4++) {
                        if (copy[p3].compareTo(copy[p4]) == 0) {
                            throw new IllegalArgumentException();
                        }
                        if (copy[p4].slopeTo(copy[p3]) ==
                                copy[p4].slopeTo(copy[p2]) &&
                                copy[p4].slopeTo(copy[p2]) ==
                                copy[p4].slopeTo(copy[p1])) {
                            rear.next = new Node(new LineSegment(copy[p1], copy[p4]));
                            rear = rear.next; 
                            size++;
                        }
                    }
                }
            }
        }

    }
    
    public int numberOfSegments() {
        // the number of line segments
        return size;
    }
    
    public LineSegment[] segments() {
        // the line segments
        LineSegment[] ls = new LineSegment[size];
        Node temp = head;
        for (int idx = 0; idx < size; idx++) {
            ls[idx] = temp.next.item;
            temp = temp.next;
        }
        return ls;
    }
    
    public static void main(String[] args) {
        // test data
        // read the N points from a file
        // print and draw the line segments
        Point[] points = {new Point(10000, 0),
                new Point(0, 10000),
                new Point(3000, 7000),
                new Point(7000, 3000),
                new Point(20000, 21000),
                new Point(3000, 4000),
                new Point(14000, 15000),
                new Point(6000, 7000)};
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
    }
 }
