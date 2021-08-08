public class DFS extends Algorithm {
    private Graph graph;

    private boolean[] visited;

    public DFS(Graph graph) {
        this.graph = graph;
        visited = new boolean[graph.pointsCount];
    }

    @Override
    public void Visualize(Visualizer v, AlgoThread t) {
        graph.Visualize(v, t);
        for (int i = 0; i < graph.pointsCount; i++) visited[i] = false;
        DFSVisit(0, v, t);
    }

    private void DFSVisit(int s, Visualizer v, AlgoThread t) {
        if (t.stop) return;
        visited[s] = true;
        for (Pair<Integer, Double> u : graph.adjList.get(s)) {
            if (!visited[u.first]) {
                StdDraw.line(graph.points[s].x(), graph.points[s].y(), graph.points[u.first].x(), graph.points[u.first].y());
                StdDraw.pause(v.delay);
                v.Visit(graph.points[s], StdDraw.RED);
                DFSVisit(u.first, v, t);
            }
        }
        v.Visit(graph.points[s], StdDraw.GREEN);
    }
}
