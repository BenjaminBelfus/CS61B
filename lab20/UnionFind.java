public class UnionFind {

    /* TODO: Add instance variables here. */
    int[] a;


    /* Creates a UnionFind data structure holding N vertices. Initially, all
       vertices are in disjoint sets. */
    public UnionFind(int N) {
        // TODO: YOUR CODE
        a = new int[N+1];
        for (int i = 0; i < a.length; i++){
            a[i] = -1;
        }
    }

    /* Returns the size of the set V belongs to. */
    public int sizeOf(int v) {
        // TODO: YOUR CODE HERE
        return -a[find(v)];
    }

    /* Returns the parent of V. If V is the root of a tree, returns the
       negative size of the tree for which V is the root. */
    public int parent(int v) {
        // TODO: YOUR CODE HERE
        return a[v];
    }

    /* Returns true if nodes V1 and V2 are connected. */
    public boolean connected(int v1, int v2) {
        // TODO: YOUR CODE HERE
        return (find(v1) == find(v2));
    }

    /* Returns the root of the set V belongs to. Path-compression is employed
       allowing for fast search-time. If invalid vertices are passed into this
       function, throw an IllegalArgumentException. */
    public int find(int v) {
        // TODO: YOUR CODE HERE
        if (v > a.length) {
            throw new IllegalArgumentException();
        }
        else if (a[v] < 0) {
            return v;
        }else if (v < 0) {
            throw new IllegalArgumentException();
        }
        a[v] = find(a[v]);
        return a[v];
    }

    /* Connects two elements V1 and V2 together. V1 and V2 can be any element,
       and a union-by-size heuristic is used. If the sizes of the sets are
       equal, tie break by connecting V1's root to V2's root. Union-ing a vertex
       with itself or vertices that are already connected should not change the
       structure. */
    public void union(int v1, int v2) {
        // TODO: YOUR CODE HERE
        if (connected(v1, v2)) {
            return;
        }
        else if (a[find(v1)] < a[find(v2)]) { //upside down due to negative
            a[find(v1)] += a[find(v2)];
            a[find(v2)] = find(v1);

        }
        else {
            a[find(v2)] += a[find(v1)];
            a[find(v1)] = find(v2);
        }
    }
}