// Running algorithms in threads to keep the application responding while visualizing the algorithm
// And to allow interrupting.

public class AlgoThread extends Thread {
    public String algorithm;
    public boolean stop = false;

    @Override
    public void run() {
        Visualizer v = new Visualizer();
        switch (algorithm) {
            case "Kruskal" -> VisualizeAlgorithm(v, new KruskalMST(new Graph(v.minAxis, v.maxAxis)));
            case "Prim" -> VisualizeAlgorithm(v, new PrimMST(new Graph(v.minAxis, v.maxAxis)));
            case "DFS" -> VisualizeAlgorithm(v, new DFS(new Graph(v.minAxis, v.maxAxis)));
            case "BFS" -> VisualizeAlgorithm(v, new BFS(new Graph(v.minAxis, v.maxAxis)));
            case "Graham’s Algorithm" -> VisualizeAlgorithm(v, new GrahamAlgorithm(new Graph(v.minAxis, v.maxAxis)));
        }
    }

    private void VisualizeAlgorithm(Visualizer v, Algorithm algo) {
        if (algo instanceof KruskalMST)
            v.Init("Implementing Kruskal MST...");
        if (algo instanceof PrimMST)
            v.Init("Implementing PRIM MST...");
        if (algo instanceof DFS)
            v.Init("Implementing DFS...");
        if (algo instanceof BFS)
            v.Init("Implementing BFS...");
        if (algo instanceof GrahamAlgorithm)
            v.Init("Implementing Graham's Algorithm...");
        algo.Visualize(v, this);
        v.Done();
    }
}