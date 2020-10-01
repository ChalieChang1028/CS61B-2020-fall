package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int n, c, top, bot;
    private boolean[] matrix;
    private WeightedQuickUnionUF u, backwash;

    public Percolation(int N) throws IllegalArgumentException {
        if (N <= 0) {
            throw new IllegalArgumentException("N cannot be smaller than 0!");
        }
        n = N;
        c = 0;
        top = n * n;
        bot = n * n + 1;
        matrix = new boolean[n * n + 1];
        u = new WeightedQuickUnionUF(n * n + 2);
        backwash = new WeightedQuickUnionUF(n * n + 1);
    }

    private int to1D(int row, int col) {
        return row * n + col;
    }

    public void open(int row, int col) throws IndexOutOfBoundsException {
        if (row < 0 || col < 0 || row >= n || col >= n) {
            throw new ArrayIndexOutOfBoundsException();
        }
        if (!isOpen(row, col)) {
            matrix[to1D(row, col)] = true;
            c++;
            if (row == 0) {
                u.union(top, to1D(row, col));
                backwash.union(top, to1D(row, col));
            }
            if (row == n - 1) {
                u.union(bot, to1D(row, col));
            }
            if (row - 1 >= 0 && isOpen(row - 1, col)) {
                u.union(to1D(row, col), to1D(row - 1, col));
                backwash.union(to1D(row, col), to1D(row - 1, col));
            }
            if (row + 1 < n && isOpen(row + 1, col)) {
                u.union(to1D(row, col), to1D(row + 1, col));
                backwash.union(to1D(row, col), to1D(row + 1, col));
            }
            if (col - 1 >= 0 && isOpen(row, col - 1)) {
                u.union(to1D(row, col), to1D(row, col - 1));
                backwash.union(to1D(row, col), to1D(row, col - 1));
            }
            if (col + 1 < n && isOpen(row, col + 1)) {
                u.union(to1D(row, col), to1D(row, col + 1));
                backwash.union(to1D(row, col), to1D(row, col + 1));
            }
        }
    }

    public boolean isOpen(int row, int col) throws IndexOutOfBoundsException {
        if (row < 0 || col < 0 || row >= n || col >= n) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return matrix[to1D(row, col)];
    }

    public boolean isFull(int row, int col) throws IndexOutOfBoundsException {
        if (row < 0 || col < 0 || row >= n || col >= n) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return isOpen(row, col)
                && backwash.connected(top, to1D(row, col))
                && u.connected(top, to1D(row, col));
    }

    public int numberOfOpenSites() {
        return c;
    }

    public boolean percolates() {
        return u.connected(top, bot);
    }

    public static void main(String[] args) {

    }
}
