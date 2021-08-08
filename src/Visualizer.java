import java.awt.*;
import java.util.*;

// Main class for algorithms implementation and visualization.
public class Visualizer {
    public static final int minAxis = 5, maxAxis = 100, delay = 50;

    public Visualizer() {
        StdDraw.setCanvasSize(800, 600);
        StdDraw.setScale(0, minAxis + maxAxis);
        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.textLeft(1, maxAxis + 2, "Please select an algorithm from MenuBar!");
    }

    // Initializes the window for a new algorithm
    public void Init(String txt) {
        StdDraw.clear();
        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.textLeft(2, maxAxis + 2, txt);
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(0.005);
    }

    // Colors a point p, with color c
    public void Visit(Point p, Color c) {
        StdDraw.setPenColor(c);
        StdDraw.setPenRadius(0.015);
        StdDraw.point(p.x(), p.y());
        StdDraw.setPenRadius(0.005);
        StdDraw.setPenColor(StdDraw.BLACK);
    }

    // Displays a "Done" message on the right after the visualization is finished
    public void Done() {
        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.textRight(maxAxis + 2, maxAxis + 2, "DONE!");
        StdDraw.setPenColor(StdDraw.BLACK);
    }
}
