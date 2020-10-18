package bearmaps;
import java.util.*;

public class KDTree implements PointSet{
    private class Node {
        private Point point;
        private Node leftChild = null;
        private Node rightChild = null;

        public Node(Point p) {
            point = p;
        }

        public boolean compare(Point p, int o) {
            if (o % 2 == 0) {
                return Double.compare(p.getX(), point.getX()) >= 0;
            }
            return Double.compare(p.getY(), point.getY()) >= 0;
        }

        public Node add(Node head, Point p, int orientation) {
            if (head == null) {
                return new Node(p);
            }
            if (head.compare(p, orientation)) {
                head.rightChild = add(head.rightChild, p, orientation + 1);
            } else {
                head.leftChild = add(head.leftChild, p, orientation + 1);
            }
            return head;
        }

        public Node nearestHelper(Node head, Node best, Point p, int orientation) {
            Node good, bad;
            if (head == null) {
                return best;
            }
            if (Point.distance(p, head.point) < Point.distance(p, best.point)) {
                best = head;
            }
            if (head.compare(p, orientation)) {
                good = head.rightChild;
                bad = head.leftChild;
            } else {
                good = head.leftChild;
                bad = head.rightChild;
            }
            best = nearestHelper(good, best, p, orientation + 1);
            if (orientation % 2 == 0) {
                if (Double.compare(Point.distance(p, new Point(best.point.getX(), p.getY()))
                                    ,Point.distance(p, best.point)) < 0) {
                    best = nearestHelper(bad, best, p,orientation + 1);
                }
            } else {
                if (Double.compare(Point.distance(p, new Point(p.getX(), best.point.getY())),
                        Point.distance(p, best.point)) < 0) {
                    best = nearestHelper(bad, best, p, orientation + 1);
                }
            }
            return best;
        }
    }

    private Node head;
    public KDTree(List<Point> points) {
        head = new Node(points.get(0));
        for (int i = 1; i < points.size(); i++) {
            head.add(head, points.get(i), 0);
        }
    }

    @Override
    public Point nearest(double x, double y) {
        Node best = head.nearestHelper(head, head, new Point(x, y), 0);
        return best.point;
    }
}
