package bearmaps.utils.ps;


import java.util.List;

public class NaivePointSet implements PointSet {
    private List<Point> allPoints;

    public NaivePointSet(List<Point> points) {
        allPoints = points;
    }


    @Override
    public Point nearest(double x, double y) {
        Point checking = new Point(x, y);
        Point toReturn = null;
        double distanceSoFar = -1;

        for (Point p : allPoints) {
            double distance = Point.distance(p, checking);
            if (distance < distanceSoFar || distanceSoFar == -1.0) {
                toReturn = p;
                distanceSoFar = distance;
            }
        }
        return toReturn;
    }
}
