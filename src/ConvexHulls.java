import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Stack;

public class ConvexHulls {
    private ArrayList<Point> points;
    private ArrayList<Point> cornerPoints;
    private Boolean isCornerPointsCalculated = false;

    private enum Orientation {
        COUNTERCLOCKWISE,
        CLOCKWISE,
        STRAIGHT
    }

    public ConvexHulls(ArrayList<Point> points) {
        this.points = points;
    }

    private Orientation Get3PointsOrientation(Point p, Point q, Point r) {
        int det = 1 * q.x() * r.y() + 1 * r.x() * p.y() + p.x() * q.y() * 1
                - p.y() * q.x() * 1 - 1 * q.y() * r.x() - 1 * p.x() * r.y();
        if (det == 0)
            return Orientation.STRAIGHT;
        return det > 0 ? Orientation.COUNTERCLOCKWISE : Orientation.CLOCKWISE;
    }

    private void GrahamAlgorithm() {
        ArrayList<Point> sortedPoints = new ArrayList<>(points);
        Collections.sort(sortedPoints, Comparator.comparingInt((Point a) -> a.x()));
        Stack<Point> topSide = new Stack<>();
        Stack<Point> bottomSide = new Stack<>();
        topSide.push(sortedPoints.get(0));
        topSide.push(sortedPoints.get(1));
        bottomSide.push(sortedPoints.get(0));
        bottomSide.push(sortedPoints.get(1));
        for (int i = 2; i < sortedPoints.size(); i++) {
            while (topSide.size() > 1 && 
                    Get3PointsOrientation(topSide.get(topSide.size() - 2), topSide.get(topSide.size() - 1), sortedPoints.get(i)) 
                            == Orientation.COUNTERCLOCKWISE) {
                topSide.pop();
            }
            topSide.push(sortedPoints.get(i));
            while (bottomSide.size() > 1 &&
                    Get3PointsOrientation(bottomSide.get(bottomSide.size() - 2), bottomSide.get(bottomSide.size() - 1), sortedPoints.get(i))
                            == Orientation.CLOCKWISE) {
                bottomSide.pop();
            }
            bottomSide.push(sortedPoints.get(i));
        }

    }

    public ArrayList<Point> getCornerPoints() {
        if (isCornerPointsCalculated) {
            return cornerPoints;
        }
        GrahamAlgorithm();
        return cornerPoints;
    }
}
