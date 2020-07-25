// Graph edge class, works like a triplet, implements comparable to allow sorting edges by weight.
public class Edge implements Comparable<Edge> {
    int from, to;
    double weight;

    Edge(int from, int to, double weight) { // Constructor
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge that) { // Edge with minimum weight comes first.
        if (this.weight > that.weight) return 1;
        else if (this.weight < that.weight) return -1;
        return 0;
    }
}