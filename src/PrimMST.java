import java.awt.*;
import java.util.HashMap;

public class PrimMST extends Algorithm{
    private Graph graph;


    public PrimMST(Graph graph) {
        this.graph = graph;
    }

    @Override
    public void Visualize(Visualizer v, AlgoThread t) {
        graph.Visualize(v, t);
        // Create a Priority Queue, visit first node, add its incidents to the queue.
        MaxPQ<Edge> Q = new MaxPQ<>();
        int visitedCount = 1;
        for (Pair<Integer, Double> i : graph.adjList.get(0))
            Q.push(new Edge(0, i.first, i.second));

        // To mark nodes in MST
        HashMap<Integer, Boolean> in_MST = new HashMap<>();

        // Keep adding the nearest unvisited node to MST, until all nodes are visited or all edges are processed.
        while (Q.size() > 0) {
            Edge e = Q.pop();

            if (in_MST.get(e.to) == null) {
                StdDraw.line(graph.points[e.from].x(), graph.points[e.from].y(), graph.points[e.to].x(), graph.points[e.to].y());
                StdDraw.pause(v.delay);
                visitedCount++;
            }

            in_MST.put(e.from, true);
            in_MST.put(e.to, true);
            v.Visit(new Point(graph.points[e.from].x(), graph.points[e.from].y()), StdDraw.RED);
            v.Visit(new Point(graph.points[e.to].x(), graph.points[e.to].y()), StdDraw.RED);

            if (t.stop) return;

            // To break when the algorithm visits all nodes
            if (visitedCount == graph.pointsCount) {
                v.Visit(new Point(graph.points[e.to].x(), graph.points[e.to].y()), Color.GREEN);
                break;
            }

            int u = e.to;
            if (graph.adjList.get(u) != null)
                for (Pair<Integer, Double> V : graph.adjList.get(u))
                    if (in_MST.get(V.first) == null)
                        Q.push(new Edge(u, V.first, V.second));

            if (e.from == 0) v.Visit(new Point(graph.points[e.from].x(), graph.points[e.from].y()), Color.GREEN);
        }
    }
}
