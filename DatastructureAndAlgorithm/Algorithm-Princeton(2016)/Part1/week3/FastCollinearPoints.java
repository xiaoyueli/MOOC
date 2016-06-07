package week3;

import java.util.Arrays;

import edu.princeton.cs.algs4.StdOut;

public class FastCollinearPoints {
    
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
     * Sort points by slope of each point as invoking point.
     * Find all line segments containing 4 points or more exactly once by
     * examining consecutive points who have the same slope to invoking point. 
     */
    public FastCollinearPoints(Point[] points) {
        // finds all line segments containing 4 or more points
        if (points == null) {
            throw new NullPointerException();
        }
        Point[] copy = new Point[points.length];
        for (int idx = 0; idx < points.length; idx++) {
            copy[idx] = points[idx];
        }
        Node rear = head;
        
        for (int ori = 0; ori < copy.length; ori++) {
            Arrays.sort(copy);
            Point cur = copy[ori];
            Arrays.sort(copy, cur.slopeOrder());
            double slope = Double.NEGATIVE_INFINITY;
            int cnt = 0;
            for (int other = 1; other < copy.length; other++) {
                if (cur.slopeTo(copy[other]) == Double.NEGATIVE_INFINITY) {
                    throw new IllegalArgumentException();
                }
                
                if (cur.slopeTo(copy[other]) == slope) {
                    cnt++;
                    if (other == copy.length - 1 && cnt >= 3) {
                        Arrays.sort(copy, other - cnt + 1, other);
                        if (cur.compareTo(copy[other - cnt + 1]) < 0) {
                            rear.next = new Node(new LineSegment(cur, copy[other]));
                            rear = rear.next;
                            size++;
                        }
                    }
                    
                }
                else {
                    if (cnt >= 3) {
                        Arrays.sort(copy, other - cnt, other - 1);
                        if (cur.compareTo(copy[other - cnt]) < 0) {
                            rear.next = new Node(new LineSegment(cur, copy[other - 1]));
                            rear = rear.next;
                            size++;
                        }
                    }
                    slope = cur.slopeTo(copy[other]);
                    cnt = 1;
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
        Node temp = head.next;
        for (int idx = 0; idx < size; idx++) {
            ls[idx] = temp.item;
            temp = temp.next;
        }
        return ls;
    }
    
    public static void main(String[] args) {

        // test data
        // read the N points from a file
        // print and draw the line segments
        Point[] points = {new Point(10000, 0),
                new Point(1044, 2913),
                new Point(1354, 2913),
                new Point(1811, 2913),
                new Point(14191, 2913),
                new Point(3000, 4000),
                new Point(14000, 15000),
                new Point(6000, 7000)};
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
        }
    }
 }
