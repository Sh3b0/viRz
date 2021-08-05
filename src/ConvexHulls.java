import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Stack;

public class ConvexHulls {
    private ArrayList<Point> points;
    private ArrayList<Point> cornerPoints = new ArrayList<>();
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
    private Orientation GetLast3PointsOrientation(Stack<Point> stack, Point lastEl) {
        return Get3PointsOrientation(stack.get(stack.size() - 2), stack.get(stack.size() - 1), lastEl);
    }

    private void GrahamAlgorithm() {
        ArrayList<Point> sortedPoints = new ArrayList<>(points);
        Comparator<Point> comparator = (p1, p2) -> p1.x() > p2.x() || (p1.x() == p2.x() && p1.y() < p2.y()) ? 1 : -1;
        Collections.sort(sortedPoints, comparator);
        for (int i = 0; i < sortedPoints.size() - 1; i++){
            if (sortedPoints.get(i).x() == sortedPoints.get(i+1).x() && sortedPoints.get(i).y() == sortedPoints.get(i+1).y())
                sortedPoints.remove(i);

        }
        Stack<Point> topSide = new Stack<>();
        Stack<Point> bottomSide = new Stack<>();
        topSide.push(sortedPoints.get(0));
        topSide.push(sortedPoints.get(1));
        bottomSide.push(sortedPoints.get(0));
        bottomSide.push(sortedPoints.get(1));
        for (int i = 2; i < sortedPoints.size(); i++) {
            Orientation orientation = GetLast3PointsOrientation(topSide, sortedPoints.get(i));
            while (topSide.size() > 1 && orientation == Orientation.COUNTERCLOCKWISE) {
                topSide.pop();
                if (topSide.size() > 1)
                    orientation = GetLast3PointsOrientation(topSide, sortedPoints.get(i));
            }
            topSide.push(sortedPoints.get(i));
            orientation = GetLast3PointsOrientation(bottomSide, sortedPoints.get(i));
            while (bottomSide.size() > 1 && orientation == Orientation.CLOCKWISE)  {
                bottomSide.pop();
                if (bottomSide.size() > 1)
                    orientation = GetLast3PointsOrientation(bottomSide, sortedPoints.get(i));
            }
            bottomSide.push(sortedPoints.get(i));
        }
        cornerPoints.addAll(topSide);
        bottomSide.remove(0);
        bottomSide.pop();
        Collections.reverse(bottomSide);
        cornerPoints.addAll(bottomSide);
    }

    public ArrayList<Point> getCornerPoints() {
        if (isCornerPointsCalculated) {
            return cornerPoints;
        }
        GrahamAlgorithm();
        return cornerPoints;
    }
}
