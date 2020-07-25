// Max Priority Queue using a binary heap, wrote for a university assignment :D.

import java.util.ArrayList;

public class MaxPQ<T extends Comparable<T>> {

    private final ArrayList<T> x;   // Binary heap
    private int size;

    MaxPQ() {
        size = 0;
        x = new ArrayList<>();
        x.add(null);                // Just a placeholder to count from 1
    }

    void swim(int k) {

        // Iterative way
        /*
            while (k > 1 && x.get(k).compareTo(x.get(k / 2)) > 0) {
                swap(x, k, k / 2);
                k /= 2;
            }
        */

        // Recursive way
        if (k == 1) return;
        if (x.get(k).compareTo(x.get(k / 2)) > 0) swap(x, k, k / 2);
        swim(k / 2);

    }

    void sink(int k) {

        // Iterative way
        /*
            while (2 * k <= size) {
                int j = 2 * k;
                if (j < size && x.get(j).compareTo(x.get(j + 1)) < 0) j++;
                if (x.get(k).compareTo(x.get(j)) < 0) swap(x, k, j);
                else return;
                k = j;
            }
        */

        // Recursive way

        int l = 2 * k, r = 2 * k + 1;                               // left and right child of k.
        if (l == size) {                                            // If k has only left child.
            if (x.get(k).compareTo(x.get(l)) < 0) swap(x, k, l);
        } else if (l < size) {                                      // If k has both children.
            int g;                          // Will hold index of the greater of two children.
            if (x.get(l).compareTo(x.get(r)) > 0) g = 2 * k;
            else g = 2 * k + 1;
            if (x.get(k).compareTo(x.get(g)) < 0) swap(x, k, g);
            else return;
            sink(g);
        }
    }

    private void swap(ArrayList<T> x, int i, int j) {
        T tmp = x.get(i);
        x.set(i, x.get(j));
        x.set(j, tmp);
    }

    void push(T i) {
        x.add(i);
        swim(++size);
    }

    T pop() {
        if (size == 0) throw new NullPointerException("Queue is empty\n");
        T t = x.get(1);
        swap(x, 1, size);
        x.remove(size--);
        sink(1);
        return t;
    }

    public int size() {
        return size;
    }
}