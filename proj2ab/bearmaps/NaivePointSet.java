package bearmaps;
import java.util.*;

public class NaivePointSet implements PointSet{

    private List<Point> points;
    public NaivePointSet(List<Point> points) {
        this.points = points;
    }

    @Override
    public Point nearest(double x, double y) {
        Point ans = points.get(0), myP = new Point(x, y);
        double min = Point.distance(myP, points.get(0));
        for (Point p : points) {
            double tep = Point.distance(myP, p);
            if (tep < min) {
                min = tep;
                ans = p;
            }
        }
        return ans;
    }
}
