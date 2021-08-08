import java.util.Collections;

public class KruskalMST extends Algorithm{
    private Graph graph;


    public KruskalMST(Graph graph) {
        this.graph = graph;
    }

    @Override
    public void Visualize(Visualizer v, AlgoThread t) {
        graph.Visualize(v, t);
        // Firstly, sort all edges by minimum weight.
        Collections.sort(graph.edgeList);

        // Create a UnionFind DS of size pointsCount + 1, to allow connecting MST vertices and detecting cycles.
        UnionFind uf = new UnionFind(graph.pointsCount + 1);

        // Passing on all edges in order.
        for (Edge e : graph.edgeList) {
            // if the edge can be added without creating a cycle, we add it, mark both ends.
            if (!uf.connected(e.from, e.to)) {
                uf.union(e.from, e.to);
                StdDraw.line(graph.points[e.from].x(), graph.points[e.from].y(), graph.points[e.to].x(), graph.points[e.to].y());
                v.Visit(new Point(graph.points[e.from].x(), graph.points[e.from].y()), StdDraw.RED);
                v.Visit(new Point(graph.points[e.to].x(), graph.points[e.to].y()), StdDraw.RED);
                StdDraw.pause(v.delay);
            }
            if (t.stop) return;
        }
    }
}
