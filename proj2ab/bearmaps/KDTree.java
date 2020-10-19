package bearmaps;
import java.util.*;

public class KDTree implements PointSet {
    private class Node {
        private Point point;
        private boolean orientation;
        private Node leftChild = null;
        private Node rightChild = null;

        Node(Point p, boolean o) {
            point = p;
            orientation = o;
        }
    }

    private Node head;
    public KDTree(List<Point> points) {
        for (Point p : points) {
            head = add(head, p, false);
        }
    }

    private boolean compare(Point p, Point b, boolean o) {
        if (o) {
            return Double.compare(p.getY(), b.getY()) >= 0;
        }
        return Double.compare(p.getX(), b.getX()) >= 0;
    }

    private Node add(Node n, Point p, boolean orientation) {
        if (n == null) {
            return new Node(p, orientation);
        }
        if (compare(p, n.point, orientation)) {
            n.rightChild = add(n.rightChild, p, !orientation);
        } else {
            n.leftChild = add(n.leftChild, p, !orientation);
        }
        return n;
    }

    @Override
    public Point nearest(double x, double y) {
        return nearestHelper(head, new Point(x, y), head.point);
    }

    private Point nearestHelper(Node n, Point p, Point best) {
        Node good, bad;
        if (n == null) {
            return best;
        }
        if (Point.distance(p, n.point) < Point.distance(p, best)) {
            best = n.point;
        }
        if (compare(p, n.point, n.orientation)) {
            good = n.rightChild;
            bad = n.leftChild;
        } else {
            good = n.leftChild;
            bad = n.rightChild;
        }
        best = nearestHelper(good, p, best);

        double toBest = Point.distance(best, p);
        if (n.orientation) {
            if (Double.compare(Point.distance(p,
                                    new Point(p.getX(), n.point.getY())),
                                toBest) < 0) {
                best = nearestHelper(bad, p, best);
            }
        } else {
            if (Double.compare(Point.distance(p,
                                    new Point(n.point.getX(), p.getY())),
                                toBest) < 0) {
                best = nearestHelper(bad, p, best);
            }
        }
        return best;
    }
}
