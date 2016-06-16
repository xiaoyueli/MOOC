package week5;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.SET;

public class PointSET {
    
    private SET<Point2D> pset;      // store points
    private Node rNode;             // red-black tree
    private final boolean red = true;
    private final boolean black = false;
    
    
    private class Node {
        private Point2D point;
        private Node leftChild, rightChild;
        private boolean color;
        
        public Node(Point2D p) {
            point = p;
            leftChild = null;
            rightChild = null;
            this.color = red;
        }
    }
    
    public PointSET() {
        // construct an empty set of points
        pset = new SET<Point2D>();
        rNode = null;
    }
    
    public boolean isEmpty() {
        // is the set empty? 
        return pset.size() == 0;
    }
    
    public int size() {
        // number of points in the set
        return pset.size();
    }
    
    public void insert(Point2D p) {
        // add the point to the set (if it is not already in the set)
        if (p == null) {
            throw new NullPointerException();
        }
        
        if (!pset.contains(p)) {
            rNode = rbbstInsert(rNode, p);
        }
    }
    
    private Node rbbstInsert(Node root, Point2D p) {
        // insert the point stored as node in red-black tree according to red-black tree properties.
        // after insert node according to normal BST rule, adjust the tree to red-black tree.
        // the expected status of a node is only the left son is red node, no other adjacent node is red.
        // if the right son is red node, left rotate the root
        // if the left son and left son' left son is red, right rotate the root
        // if both son is red, just mark both son to black and mark its self to red.
        
        if (p == null) {
            throw new NullPointerException();
        }
        
        if (root == null) {
            root = new Node(p);
            pset.add(p);
        }
        
        int comp = p.compareTo(root.point);
        if (comp < 0) root.leftChild = rbbstInsert(root.leftChild, p);
        else if (comp > 0) root.rightChild = rbbstInsert(root.rightChild, p);
        
        if (!isRed(root.leftChild) && isRed(root.rightChild)) root = rbbstLeftRotate(root);
        if (isRed(root.leftChild) && isRed(root.leftChild.leftChild)) root = rbbstRightRotate(root);
        if (isRed(root.leftChild) && isRed(root.rightChild)) adjustColor(root);
        
        
        return root;
    }
    
    private boolean isRed(Node node) {

        if (node == null) return false;
        return node.color;
    }
    
    private Node rbbstLeftRotate(Node root) {
        // left rotate the root
        // after rotated, its left son should be red
        
        if (root == null) {
            throw new NullPointerException();
        }
        assert isRed(root.rightChild);
        Node temp = root.rightChild;
        root.rightChild = temp.leftChild;
        temp.leftChild = root;
        temp.color = root.color;
        root.color = red;

        
        return temp;
    }
    
    private Node rbbstRightRotate(Node root) {
        // right rotate the root
        // after rotated, its right son should be red
        if (root == null) {
            throw new NullPointerException();
        }
        assert isRed(root.leftChild);
        Node temp = root.leftChild;
        root.leftChild = temp.rightChild;
        temp.rightChild = root;
        temp.color = root.color;
        root.color = red;
        return temp;
        
    }
    
    private void adjustColor(Node root) {
        // mark both sons of the root to black node
        // mark its self to red node
        
        if (root == null) {
            throw new NullPointerException();
        }
        assert isRed(root);
        assert isRed(root.leftChild) && isRed(root.rightChild);
        root.leftChild.color = black;
        root.rightChild.color = black;
        root.color = red;
    }
    
    public boolean contains(Point2D p) {
        // does the set contain point p? 
        if (p == null) {
            throw new NullPointerException();
        }
        
        return rbbstSearch(rNode, p);
    }
    
    private boolean rbbstSearch(Node root, Point2D p) {
        
        if (root == null) {
            return false;
        }
        
        int cmp = p.compareTo(root.point);
        if (cmp < 0) return rbbstSearch(root.leftChild, p);
        else if (cmp > 0) return rbbstSearch(root.rightChild, p);
        else return true;
    }
    
    public void draw() {
        // draw all points to standard draw
        if (rNode != null) {
            drawPoint(rNode);
        }
    }
    
    private void drawPoint(Node node) {
        if (node != null) {
            if (node.leftChild != null) {
                drawPoint(node.leftChild);
            }
            node.point.draw();
            if (node.rightChild != null) {
                drawPoint(node.rightChild);
            }
        }
    }
    
    public Iterable<Point2D> range(RectHV rect) {
        // all points that are inside the rectangle
        
        if (rect == null) {
            throw new NullPointerException();
        }
        
        SET<Point2D> inSet = new SET<Point2D>();
        checkPoints(rect, rNode, inSet);
        
        return inSet;
    }
    
    private void checkPoints(RectHV rec, Node root, SET<Point2D> set) {
        // if the point of the root is under the rectangle, only check the right side of the root
        // if the point of the root is upper the rectangle, only check the left side of the root
        // otherwise check both side of the root
        
        if (root == null) return;
        if (rec.contains(root.point)) {
            set.add(root.point);
            checkPoints(rec, root.leftChild, set);
            checkPoints(rec, root.rightChild, set);
        }
        else {
            if (root.point.y() < rec.ymin()) {
                checkPoints(rec, root.rightChild, set);
            }
            else if (root.point.y() > rec.ymax()) {
                checkPoints(rec, root.leftChild, set);
            }
            else {
                checkPoints(rec, root.leftChild, set);
                checkPoints(rec, root.rightChild, set);
            }
        }
    }
    
    public Point2D nearest(Point2D p) {
        // a nearest neighbor in the set to point p; null if the set is empty 
        if (p == null) {
            throw new NullPointerException();
        }
        
        if (pset.isEmpty()) return null;
        
        Node node = nearestNode(rNode, p, rNode);
        
        return node.point;
    }
    
    private Node nearestNode(Node root, Point2D point, Node nearNode) {
        // inOrder travel all the node in the tree
        // update the nearest node by comparing the root to the nearest node so far

        if (root == null) return nearNode;
    
        if (root.leftChild != null) {
            nearNode = nearestNode(root.leftChild, point, nearNode);
        }
        if (point.distanceTo(root.point) < point.distanceTo(nearNode.point)) {
            nearNode = root;
        }
        if (root.rightChild != null) {
            nearNode = nearestNode(root.rightChild, point, nearNode);
        }
        
        return nearNode;
    }

    public static void main(String[] args) {
        // unit testing of the methods (optional) 
    }
    
 }
