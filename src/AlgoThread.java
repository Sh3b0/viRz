// Running algorithms in threads to keep the application responding while visualizing the algorithm
// And to allow interrupting.

public class AlgoThread extends Thread {
    public String algorithm;
    public boolean stop = false;

    @Override
    public void run() {
        Visualizer v = new Visualizer();
        switch (algorithm) {
            case "Kruskal" -> v.KruskalMST(this);
            case "Prim" -> v.PrimMST(this);
            case "DFS" -> v.DFS(this);
            case "BFS" -> v.BFS(this);
            case "Graham’s Algorithm" -> v.GrahamAlgorithm(this);
        }
    }
}