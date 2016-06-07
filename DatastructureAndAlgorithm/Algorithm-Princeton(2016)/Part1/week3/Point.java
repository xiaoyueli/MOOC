package week3;

import java.util.Arrays;

/******************************************************************************
 *  Compilation:  javac Point.java
 *  Execution:    java Point
 *  Dependencies: none
 *  
 *  An immutable data type for points in the plane.
 *  For use on Coursera, Algorithms Part I programming assignment.
 *
 ******************************************************************************/

import java.util.Comparator;
import edu.princeton.cs.algs4.StdDraw;

public class Point implements Comparable<Point> {

    private final int x;     // x-coordinate of this point
    private final int y;     // y-coordinate of this point

    /**
     * Initializes a new point.
     *
     * @param  x the <em>x</em>-coordinate of the point
     * @param  y the <em>y</em>-coordinate of the point
     */
    public Point(int x, int y) {
        /* DO NOT MODIFY */
        this.x = x;
        this.y = y;
    }

    /**
     * Draws this point to standard draw.
     */
    public void draw() {
        /* DO NOT MODIFY */
        StdDraw.point(x, y);
    }

    /**
     * Draws the line segment between this point and the specified point
     * to standard draw.
     *
     * @param that the other point
     */
    public void drawTo(Point that) {
        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    /**
     * Returns the slope between this point and the specified point.
     * Formally, if the two points are (x0, y0) and (x1, y1), then the slope
     * is (y1 - y0) / (x1 - x0). For completeness, the slope is defined to be
     * +0.0 if the line segment connecting the two points is horizontal;
     * Double.POSITIVE_INFINITY if the line segment is vertical;
     * and Double.NEGATIVE_INFINITY if (x0, y0) and (x1, y1) are equal.
     *
     * @param  that the other point
     * @return the slope between this point and the specified point
     */
    public double slopeTo(Point that) {
        /* YOUR CODE HERE */
        double slope;
        if (that == null) {
            throw new NullPointerException();
        }
        if (x == that.x && y == that.y) {
            slope = Double.NEGATIVE_INFINITY;
        }
        else if (x == that.x) {
            slope = Double.POSITIVE_INFINITY;
        }
        else if (y == that.y) {
            slope = 0.0;
        }
        else {
            slope = 1.0 * (y - that.y) / (x - that.x);
        }
        
        return slope;
    }

    /**
     * Compares two points by y-coordinate, breaking ties by x-coordinate.
     * Formally, the invoking point (x0, y0) is less than the argument point
     * (x1, y1) if and only if either y0 < y1 or if y0 = y1 and x0 < x1.
     *
     * @param  that the other point
     * @return the value <tt>0</tt> if this point is equal to the argument
     *         point (x0 = x1 and y0 = y1);
     *         a negative integer if this point is less than the argument
     *         point; and a positive integer if this point is greater than the
     *         argument point
     */
    public int compareTo(Point that) {
        /* YOUR CODE HERE */
        if (that == null) {
            throw new NullPointerException();
        }
        if (y < that.y || y == that.y && x < that.x) {
            return -1;
        }
        else if (y == that.y && x == that.x) {
            return 0;
        }
        else {
            return 1;
        }
    }

    /**
     * Compares two points by the slope they make with this point.
     * The slope is defined as in the slopeTo() method.
     *
     * @return the Comparator that defines this ordering on points
     */
    public Comparator<Point> slopeOrder() {
        /* YOUR CODE HERE */
        return new BySlope();
    }

    /**
     * Implements specific comparator of by slope.
     * Compares the slopes of the point invoking to the two argument points.
     * if equal return 0, else if slope to p1 is less than slope to p2, return -1, otherwise 1; 
     */
    private class BySlope implements Comparator<Point> {
        
        public int compare(Point p1, Point p2) {
            if (p1 == null || p2 == null) {
                throw new NullPointerException();
            }
            
            if (slopeTo(p1) == slopeTo(p2)) {
                return 0;
            }
            else if (slopeTo(p1) < slopeTo(p2)) {
                return -1;
            }
            else {
                return 1;
            }
        }
    }
    
    /**
     * Returns a string representation of this point.
     * This method is provide for debugging;
     * your program should not rely on the format of the string representation.
     *
     * @return a string representation of this point
     */
    public String toString() {
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }

    /**
     * Unit tests the Point data type.
     */
    public static void main(String[] args) {
        /* YOUR CODE HERE */
        Point[] ps = {new Point(0, 1), 
                new Point(-1, 1),
                new Point(1, 2),
                new Point(2, 3),
                new Point(4, -1),
                new Point(1, 2),
                new Point(1, 0),
                new Point(-2, -1),
                new Point(3, 2)}; 
        Arrays.sort(ps, new Point(0, 0).slopeOrder());
        for (int idx = 0; idx < ps.length; idx++) {
            System.out.print(ps[idx] + " ");
        }
        
        System.out.println();
        System.out.println(new Point(0, 0).compareTo(new Point(0, 0)));
    }
}
