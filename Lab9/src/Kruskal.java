import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;

public class Kruskal {

    private ArrayList<Edge> mst;

    public Kruskal(EdgeWeightGraph graph) {
        int nodes = graph.nodes;
        mst = new ArrayList<>();

        MinPQ pq = MinPQ.fromArray(graph.edgeList);
        UnionFindSet uf = new UnionFindSet(nodes);

        while (pq.size > 0 && mst.size() < nodes - 1) {
            Edge e = pq.deleteMin();
            int a = e.p1, b = e.p2;
            if (uf.connected(a, b)) {
                continue;
            }
            uf.union(a, b);
            mst.add(e);
        }
    }

    private static class EdgeWeightGraph {

        private final int nodes;
        private int edges;

        private ArrayList<Edge>[] adj;
        private ArrayList<Edge> edgeList = new ArrayList<>();

        EdgeWeightGraph(int nodes) {
            this.nodes = nodes;
            this.edges = 0;
            adj = new ArrayList[nodes];
            for (int i = 0; i < adj.length; i++) {
                adj[i] = new ArrayList<>();
            }
        }

        void addEdge(Edge e) {
            edgeList.add(e);
            adj[e.p1].add(e);
            adj[e.p2].add(e);
            edges++;
        }
    }

    private static class Edge {
        private final int p1;
        private final int p2;
        private final int weight;

        public Edge(int p1, int p2, int weight) {
            this.p1 = p1;
            this.p2 = p2;
            this.weight = weight;
        }

        public String toString() {
            return p1 + "<->" + p2 + " " + weight;
        }

    }

    private static class UnionFindSet {

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

    private static class MinPQ {

        private Edge[] arr;
        private int size;

        private static final Comparator<Edge> comp = new Comparator<Edge>() {
            @Override
            public int compare(Edge o1, Edge o2) {
                return o1.weight - o2.weight;
            }
        };

        static MinPQ fromArray(Collection<Edge> edges) {
            MinPQ p = new MinPQ();
            p.arr = new Edge[edges.size() + 1];
            p.size = edges.size();

            int pos = 1;
            for (Edge edge : edges) {
                p.arr[pos] = edge;
                pos++;
            }
            for (int i = p.size / 2; i >= 1; i--) {
                p.sink(i);
            }
            return p;
        }

        private void swim(int index) {
            while (index > 1 && comp.compare(arr[index], arr[index / 2]) < 0) {
                swap(index, index / 2);
                index /= 2;
            }
        }

        private void sink(int index) {
            while (index * 2 <= size) {
                int j = index * 2;
                if (j + 1 <= size && comp.compare(arr[j + 1], arr[j]) < 0) {
                    j++;
                }
                if (comp.compare(arr[index], arr[j]) > 0) {
                    swap(index, j);
                } else {
                    break;
                }
                index = j;
            }
        }

        private Edge deleteMin() {
            Edge min = arr[1];
            swap(1, size);
            size--;
            sink(1);
            return min;
        }

        private void insert(Edge x) {
            size++;
            arr[size] = x;
            swim(size);
        }

        private void swap(int i1, int i2) {
            Edge temp = arr[i1];
            arr[i1] = arr[i2];
            arr[i2] = temp;
        }

    }

    public static void main(String[] args) {
        int nodes = 8;
        int edges = 16;

        EdgeWeightGraph graph = new EdgeWeightGraph(nodes);
        int[][] data = new int[][]{
                {4, 5, 35},
                {4, 7, 37},
                {5, 7, 28},
                {0, 7, 16},
                {1, 5, 32},
                {0, 4, 38},
                {2, 3, 17},
                {1, 7, 19},
                {0, 2, 26},
                {1, 2, 36},
                {1, 3, 29},
                {2, 7, 34},
                {6, 2, 40},
                {3, 6, 52},
                {6, 0, 58},
                {6, 4, 93}
        };
        for (int[] datum : data) {
            graph.addEdge(new Edge(datum[0], datum[1], datum[2]));
        }

        Kruskal kruskal = new Kruskal(graph);

        ArrayList<Edge> mst = new ArrayList<>(kruskal.mst);
        Comparator<Edge> comparator = Comparator.comparingInt(it -> it.p1);
        comparator = comparator.thenComparingInt(it -> it.p2);
        mst.sort(comparator);

        assert mst.size() == nodes - 1;
        assert mst.get(0).toString().equals("0<->2 26");
        assert mst.get(1).toString().equals("0<->7 16");
        assert mst.get(2).toString().equals("1<->7 19");
        assert mst.get(3).toString().equals("2<->3 17");
        assert mst.get(4).toString().equals("4<->5 35");
        assert mst.get(5).toString().equals("5<->7 28");
        assert mst.get(6).toString().equals("6<->2 40");

    }


}
