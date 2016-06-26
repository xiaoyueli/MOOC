package week2;

import java.awt.Color;
import edu.princeton.cs.algs4.Picture;
import edu.princeton.cs.algs4.Queue;

public class SeamCarver {
    
    private static final boolean COL = false;
    private static final boolean ROW = true;
    private Picture picture;
    private double[][] tEnergy;     // store energy value of each pixel
    
    public SeamCarver(Picture picture) {
        // create a seam carver object based on the given picture
        
        if (picture == null) {
            throw new NullPointerException();
        }

        this.picture = buildPicture(picture);
     
    }
    
    // build a copy of the old picture object and return it
    private Picture buildPicture(Picture pic) {
        Picture p = new Picture(pic.width(), pic.height());
        for (int row = 0; row < pic.height(); row++) {
            for (int col = 0; col < pic.width(); col++) {
                p.set(col, row, pic.get(col, row));
            }
        }
        return p;
    }

    // pixel information collection
    private class Pixel {
        private int idx;        // the row/col idx of this pixel
        private double energy;  // the sum of energy from source to this pixel
        private Pixel parent;   // the last pixel on the shortest pathway
        Pixel(int idx, double value, Pixel par) {
            this.idx = idx;
            energy = value;
            parent = par;
        }
        
        public void update(double value, Pixel par) {
            energy = value;
            parent = par;
        }
        
    }
    
    public Picture picture() {
        // current picture
        return buildPicture(picture);
    }
    
    public int width() {
        // width of current picture
        return picture.width();
    }
    
    public int height() {
        // height of current picture
        return picture.height();
    }
    
    public double energy(int x, int y) {
        // energy of pixel at column x and row y

        if (x < 0 || y < 0 || x >= picture.width() || y >= picture.height())
            throw new IndexOutOfBoundsException();
        if (x == 0 || x == picture.width() - 1 ||
                y == 0 || y == picture.height() - 1) {
            return 1000;
        }
        
        Color up = picture.get(x, y - 1);
        Color down = picture.get(x, y + 1);
        Color right = picture.get(x - 1, y);
        Color left = picture.get(x + 1, y);
        double energyX = Math.pow(right.getBlue() - left.getBlue(), 2) + 
                Math.pow(right.getRed() - left.getRed(), 2) + 
                Math.pow(right.getGreen() - left.getGreen(), 2);
        double energyY = Math.pow(up.getBlue() - down.getBlue(), 2) + 
                Math.pow(up.getGreen() - down.getGreen(), 2) + 
                Math.pow(up.getRed() - down.getRed(), 2);
        
        return Math.sqrt(energyX + energyY);
    }
    
    // update the energy table according to the current picture object
    private void computeEnergy() {
        tEnergy = new double[picture.width()][picture.height()];
        for (int row = 0; row < picture.height(); row++) {
            for (int col = 0; col < picture.width(); col++) {
                tEnergy[col][row] = energy(col, row);
            }
        }
    }
    
    
    public int[] findHorizontalSeam() {
        // sequence of indices for horizontal seam
        if (picture.height() == 1)
            return new int[picture.width()];
       
        // update the energy table according to current picture object
        // initialize the distance table by set each pixel position to null
        computeEnergy();
        Pixel[][] disTable = new Pixel[picture.width()][picture.height()];
        for (int row = 0; row < picture.height(); row++) {
            for (int col = 0; col < picture.width(); col++) {
                disTable[col][row] = null;
            }
        }
        
        // travel all the pixels by BFS
        // enqueue the pixels of the first column as sources
        Queue<Pixel> que = new Queue<Pixel>();
        int rear = 0;
        for (int row = 1; row < picture.height() - 1; row++) {
            Pixel p = new Pixel(row, tEnergy[0][row], null);
            disTable[0][row] = p;
            que.enqueue(p);
            rear++;
            
        }
        
        int col = 0;
        while (!que.isEmpty()) {
            Pixel cur = que.dequeue();
            rear--;

            updateHEnergy(col, cur, disTable, que);
            if (rear == 0) {
                rear = que.size();
                col++;
            }
            if (col == picture.width() - 1) break;
        }
        
        // compute the minimum energy pixel
        double min = Double.MAX_VALUE;
        Pixel minEnery = null;
        while (!que.isEmpty()) {
            Pixel cur = que.dequeue();
            if (cur.energy < min) {
                min = cur.energy;
                minEnery = cur;
            }
        }
        
        // track the minimum energy path by visit each pixel's parent pixel
        int[] seam = new int[picture.width()];
        int idx = picture.width() - 1;
        while (minEnery != null) {
            seam[idx--] = minEnery.idx;
            minEnery = minEnery.parent;
        }
        
        return seam;
    }
    

    private void updateHEnergy(int col, Pixel cur, Pixel[][] table, Queue<Pixel> q) {
        // update the three downward pixel' energy value 
        if (col + 1 >= picture.width()) return;

        if (cur.idx - 1 >= 0) updatePixel(col + 1, cur.idx - 1, cur, table, q, ROW);
        updatePixel(col + 1, cur.idx, cur, table, q, ROW);
        if (cur.idx + 1 < picture.height()) updatePixel(col + 1, cur.idx + 1, cur, table, q, ROW);
    }
    
    private void updatePixel(int col, int row, Pixel cur, Pixel[][] table, Queue<Pixel> q, boolean flag) {
        // if the pixel on the position (col, row) has not been visited
        // build a new pixel and enqueue it
        // otherwise if the pixel exists and has higher energy than sum with the cur pixel
        // update it's energy value to new lower value and set the current pixel as it's new pre-pixel
        Pixel next = table[col][row];
        if (next == null) {
            if (flag)
                next = new Pixel(row, cur.energy + tEnergy[col][row], cur);
            else
                next = new Pixel(col, cur.energy + tEnergy[col][row], cur);
            table[col][row] = next;
            q.enqueue(next);
        }
        else if (cur.energy + tEnergy[col][row] < next.energy) {
            next.update(cur.energy + tEnergy[col][row], cur);
        }
    }
    
    public int[] findVerticalSeam() {
        // sequence of indices for vertical seam
        if (picture.width() == 1) {
            return new int[picture.height()];
        }
        
        // update the energy table according to current picture object
        // initialize the distance table by set each pixel position to null
        computeEnergy();
        Pixel[][] disTable = new Pixel[picture.width()][picture.height()];
        for (int row = 0; row < picture.height(); row++) {
            for (int col = 0; col < picture.width(); col++) {
                disTable[col][row] = null;
            }
        }
        
        // travel all the pixels by BFS
        // enqueue the pixels of the first column as sources
        Queue<Pixel> que = new Queue<Pixel>();
        int rear = 0;
        for (int col = 1; col < picture.width() - 1; col++) {
            Pixel p = new Pixel(col, tEnergy[col][0], null);
            disTable[col][0] = p;
            que.enqueue(p);
            rear++;
        }
        
        int row = 0;
        while (!que.isEmpty()) {
            Pixel cur = que.dequeue();
            rear--;

            updateVEnergy(row, cur, disTable, que);
            if (rear == 0) {
                rear = que.size();
                row++;
            }
            if (row == picture.height() - 1) break;
        }
        
        // compute the minimum energy pixel
        double min = Double.MAX_VALUE;
        Pixel minEnery = null;
        while (!que.isEmpty()) {
            Pixel cur = que.dequeue();
            if (cur.energy < min) {
                min = cur.energy;
                minEnery = cur;
            }
        }
        
        // track the minimum energy path by visit each pixel's parent pixel
        int[] seam = new int[picture.height()];
        int idx = picture.height() - 1;
        while (minEnery != null) {
            seam[idx--] = minEnery.idx;
            minEnery = minEnery.parent;
        }
        
        return seam;
    }
    
    private void updateVEnergy(int row, Pixel cur, Pixel[][] table, Queue<Pixel> q) {
        // update the three downward pixel' energy value 
        
        if (row + 1 >= picture.height()) return;
        
        if (cur.idx - 1 >= 0) updatePixel(cur.idx - 1, row + 1, cur, table, q, COL);
        updatePixel(cur.idx, row + 1, cur, table, q, COL);
        if (cur.idx + 1 < picture.width()) updatePixel(cur.idx + 1, row + 1, cur, table, q, COL);
        
        
    }
    
    public void removeHorizontalSeam(int[] seam) {
        // remove horizontal seam from current picture
        
        if (seam == null) throw new NullPointerException();
        if (seam.length != picture.width()) throw new IllegalArgumentException();
        if (picture.height() <= 1) throw new IllegalArgumentException();
        for (int idx = 0; idx < seam.length; idx++) {
            if (seam[idx] < 0 || seam[idx] >= picture.height())
                throw new IllegalArgumentException();
        }
        
        Picture pic = new Picture(picture.width(), picture.height() - 1);

        for (int col = 0; col < picture.width(); col++) {
            if (col != picture.width() - 1 && Math.abs(seam[col] - seam[col + 1]) > 1)
                throw new IllegalArgumentException();
            boolean flag = false;
            for (int row = 0; row < picture.height(); row++) {
                if (seam[col] == row) {
                    flag = true;
                    continue;
                }
                if (flag) pic.set(col, row - 1, picture.get(col, row));
                else pic.set(col, row, picture.get(col, row));
            }
        }

        picture = pic;
    }
    
    public void removeVerticalSeam(int[] seam) {
        // remove vertical seam from current picture
        
        if (seam == null) throw new NullPointerException();
        if (seam.length != picture.height()) throw new IllegalArgumentException();
        if (picture.width() <= 1) throw new IllegalArgumentException();
        for (int idx = 0; idx < seam.length; idx++) {
            if (seam[idx] < 0 || seam[idx] >= picture.width())
                throw new IllegalArgumentException();
        }
        
        Picture pic = new Picture(picture.width() - 1, picture.height());

        for (int row = 0; row < picture.height(); row++) {
            if (row != picture.height() - 1 && Math.abs(seam[row] - seam[row + 1]) > 1)
                throw new IllegalArgumentException();
            boolean flag = false;
            for (int col = 0; col < picture.width(); col++) {
                if (seam[row] == col) {
                    flag = true;
                    continue;
                }
                if (flag) pic.set(col - 1, row, picture.get(col, row));
                else pic.set(col, row, picture.get(col, row));
            }
        }

        picture = pic;
    }
    
 }