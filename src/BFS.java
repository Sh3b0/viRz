public class BFS extends Algorithm{
    private Graph graph;

    private boolean[] visited;

    public BFS(Graph graph) {
        this.graph = graph;
        visited = new boolean[graph.pointsCount];
    }

    @Override
    public void Visualize(Visualizer v, AlgoThread t) {
        graph.Visualize(v, t);
        for (int i = 0; i < graph.pointsCount; i++) visited[i] = false;
        Queue<Integer> q = new Queue<>();
        q.push(0);
        visited[0] = true;
        v.Visit(graph.points[0], StdDraw.GREEN);
        while (!q.empty()) {
            int cur = q.pop();
            for (Pair<Integer, Double> u : graph.adjList.get(cur)) {
                if (!visited[u.first]) {
                    visited[u.first] = true;
                    StdDraw.line(graph.points[cur].x(), graph.points[cur].y(), graph.points[u.first].x(), graph.points[u.first].y());
                    StdDraw.pause(v.delay);
                    v.Visit(graph.points[cur], StdDraw.BLUE);
                    v.Visit(graph.points[u.first], StdDraw.RED);
                    q.push(u.first);
                }
                if (t.stop) return;
            }
            v.Visit(graph.points[cur], StdDraw.GREEN);
        }
    }
}
