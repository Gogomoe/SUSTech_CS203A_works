public class UnionFindSet {

    int n;
    int[] parents;

    public UnionFindSet(int n) {
        this.n = n;
        this.parents = new int[n];
        for (int i = 0; i < n; i++) {
            this.parents[i] = i;
        }
    }

    public int find(int it) {
        if (parents[it] == it) {
            return it;
        }
        int parent = find(parents[it]);
        parents[it] = parent;
        return parent;
    }

    public boolean connected(int a, int b) {
        return find(a) == find(b);
    }

    public void union(int a, int b) {
        int pa = find(a);
        int pb = find(b);
        if (pa == pb) {
            return;
        }
        parents[pa] = pb;
    }
}
