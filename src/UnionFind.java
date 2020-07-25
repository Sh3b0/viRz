
// DisjointSet data structure, used for Kruskal MST to allow detecting and ignoring cycles.

public class UnionFind {
    // parent[a] = b means b is a parent of a, size[a] = the size of the component that a belongs to.
    private final int[] parent, size;
    UnionFind(int n) {
        parent = new int[n];
        size = new int[n];

        for (int i = 0; i < n; i++) {
            parent[i] = i;    // Each node is a parent of itself.
            size[i] = 1;      // Each component has size 1 by default.
        }
    }

    // Connects the two components storing p and q.
    void union(int p, int q) {
        int rp = root(p), rq = root(q);
        if (rp == rq) return;

        // Append the tree with min size, to the one with max size, to avoid long trees, update size.
        if (size[rp] < size[rq]) {
            parent[rp] = rq;
            size[rq] += size[rp];
        } else {
            parent[rq] = rp;
            size[rp] += size[rq];
        }

        // We joined two components, number of components decreases by one.
    }

    // Returns true if p and q are in the same component, false otherwise.
    boolean connected(int p, int q) {
        return root(p) == root(q);
    }

    int root(int i) {
        while (i != parent[i]) {
            parent[i] = parent[parent[i]]; // path compression
            i = parent[i];                 // going upwards in the tree
        }
        return i;
    }
}
