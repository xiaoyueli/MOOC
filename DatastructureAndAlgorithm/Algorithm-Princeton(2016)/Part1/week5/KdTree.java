package week5;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.SET;

public class KdTree {
    
    private static boolean x = true;    // flag for comparing points by x-coordinate
    private static boolean y = false;   // flag for comparing points by y-coordinate
    private SET<Point2D> kdSet;         // set for storing points
    private Node rNode;                 // kdTree

    private class Node {
        private Point2D point;
        private Node left, right;
        private boolean status;
        
        public Node(Point2D p, boolean sta) {
            point = p;
            status = sta;   // flag of axis 
            left = null;
            right = null;
        }
    }
    
    public KdTree() {
        // construct an empty set of points
        kdSet = new SET<Point2D>();
        rNode = null;
    }
    
    public boolean isEmpty() {
        // is the set empty? 
        return kdSet.isEmpty();
    }
    
    public int size() {
        // number of points in the set
        
        return kdSet.size();
    }
    
    public void insert(Point2D p) {
        // add the point to the set (if it is not already in the set)
        
        if (p == null) {
            throw new NullPointerException();
        }
        if (!kdSet.contains(p)) {
            rNode = kdTreeInsert(rNode, p, x);
        }
        
    }
    
    private Node kdTreeInsert(Node root, Point2D p, boolean sta) {
        // add the point to kdTree
        // compare points by x-coordinate if sta == x, otherwise by y-coordinate
        // if p is smaller than root point go left, otherwise go right;
        
        if (root == null) {
            kdSet.add(p);
            root = new Node(p, sta);
        }
        else {
            if (root.status == x) {
                if (p.x() < root.point.x()) root.left = kdTreeInsert(root.left, p, y);
                else root.right = kdTreeInsert(root.right, p, y);
            }
            else {
                if (p.y() < root.point.y()) root.left = kdTreeInsert(root.left, p, x);
                else root.right = kdTreeInsert(root.right, p, x);
            }
        }
        
        return root;
        
    }
    
    public boolean contains(Point2D p) {
        // does the set contain point p?
        
        if (p == null) {
            throw new NullPointerException();
        }
        
        return kdTreeSearch(rNode, p);
    }
    
    private boolean kdTreeSearch(Node root, Point2D point) {
        // search the point in kdTree
        // compare the point according to the axis flag of root
        // if the axis flag of root is x, compare by x-coordinate, otherwise by y-coordinate
        
        if (root == null) return false;
        
        if (point.compareTo(root.point) == 0) return true;
        
        if (root.status == x) {
            if (point.x() < root.point.x()) return kdTreeSearch(root.left, point);
            else return kdTreeSearch(root.right, point);
        }
        else {
            if (point.y() < root.point.y()) return kdTreeSearch(root.left, point);
            else return kdTreeSearch(root.right, point);
        }
        
    }
    
    public void draw() {
        // draw all points to standard draw 
    }
    
    public Iterable<Point2D> range(RectHV rect) {
        // all points that are inside the rectangle
        
        SET<Point2D> inSet = new SET<Point2D>();
        checkRange(rect, rNode, inSet);
        return inSet;
    }
    
    private void checkRange(RectHV rec, Node root, SET<Point2D> set) {
        // add points overlapped by rec to set
        
        if (root == null) return;
        if (rec.contains(root.point)) {
            set.add(root.point);
            checkRange(rec, root.left, set);
            checkRange(rec, root.right, set);
        }
        else {
            if (root.status == x) {
                if (root.point.x() < rec.xmin()) checkRange(rec, root.right, set);
                else if (root.point.x() > rec.xmax()) checkRange(rec, root.left, set);
                else {
                    checkRange(rec, root.left, set);
                    checkRange(rec, root.right, set);
                }
            }
            else {
                if (root.point.y() < rec.ymin()) checkRange(rec, root.right, set);
                else if (root.point.y() > rec.ymax()) checkRange(rec, root.left, set);
                else {
                    checkRange(rec, root.left, set);
                    checkRange(rec, root.right, set);
                }
            }
            
        }
    }
    
    public Point2D nearest(Point2D p) {
        // a nearest neighbor in the set to point p; null if the set is empty 
        if (p == null) {
            throw new NullPointerException();
        }
        Node nearest = checkNearest(rNode, p, rNode);
        if (nearest == null) return null;
        return nearest.point;
    }
    
    private Node checkNearest(Node root, Point2D p, Node near) {
        // if the point is closer to root than to recored nearest node, update the root as the new nearest node
        // always check the side toward the query point first
        // if the distance of the nearest point in the first side checked is smaller than smallest distance of the query point
        // to the current axis, cut off the other side checking processing, otherwise check both sides of the root.
        
        if (root == null) return near;
        if (p.distanceTo(root.point) < p.distanceTo(near.point)) near = root;
        double dis;
        if (root.status == x) {
            dis = Math.abs(p.x() - root.point.x());
            if (p.x() < root.point.x()) {
                near = checkNearest(root.left, p, near);
                if (p.distanceTo(near.point) > dis) {
                    near = checkNearest(root.right, p, near);
                }
            }
            else {
                near = checkNearest(root.right, p, near);
                if (p.distanceTo(near.point) > dis) {
                    near = checkNearest(root.left, p, near);
                }
            }
        }
        else {
            dis = Math.abs(p.y() - root.point.y());
            if (p.y() < root.point.y()) {
                near = checkNearest(root.left, p, near);
                if (p.distanceTo(near.point) > dis) {
                    near = checkNearest(root.right, p, near);
                }
            }
            else {
                near = checkNearest(root.right, p, near);
                if (p.distanceTo(near.point) > dis) {
                    near = checkNearest(root.left, p, near);
                }
            }
        }
        return near;
    }
   
    public static void main(String[] args) {
        // unit testing of the methods (optional) 
    }
 }
