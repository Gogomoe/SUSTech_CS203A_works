import java.util.ArrayList;
import java.util.Comparator;
import java.util.Objects;
import java.util.PriorityQueue;

public class Dijkstra {

    private DirectedEdge[] edgeTo;
    private int[] distTo;
    private PriorityQueue<IntPair> pq;

    private static final int INFINITY = -1;

    public Dijkstra(EdgeWeightGraph graph, int source) {
        int nodes = graph.nodes;
        edgeTo = new DirectedEdge[nodes];
        distTo = new int[nodes];
        pq = new PriorityQueue<>(nodes, Comparator.comparingInt(it -> it.weight));

        for (int i = 0; i < nodes; i++) {
            distTo[i] = INFINITY;
        }
        distTo[source] = 0;

        pq.add(new IntPair(source, 0));
        while (!pq.isEmpty()) {
            relax(graph, pq.poll().key);
        }
    }

    private void relax(EdgeWeightGraph graph, int node) {
        for (DirectedEdge e : graph.adj[node]) {
            if (distTo[e.to] == INFINITY || distTo[e.to] > distTo[e.from] + e.weight) {
                int oldDist = distTo[e.to];
                edgeTo[e.to] = e;
                distTo[e.to] = distTo[e.from] + e.weight;

                IntPair query = new IntPair(e.to, oldDist);
                if (pq.contains(query)) {
                    pq.remove(query);
                    pq.add(new IntPair(e.to, distTo[e.to]));
                } else {
                    pq.add(new IntPair(e.to, distTo[e.to]));
                }
            }
        }
    }

    private static class EdgeWeightGraph {

        private final int nodes;
        private int edges;

        private ArrayList<DirectedEdge>[] adj;

        EdgeWeightGraph(int nodes) {
            this.nodes = nodes;
            this.edges = 0;
            adj = new ArrayList[nodes];
            for (int i = 0; i < adj.length; i++) {
                adj[i] = new ArrayList<>();
            }
        }

        void addEdge(DirectedEdge e) {
            adj[e.from].add(e);
            edges++;
        }
    }

    private static class DirectedEdge {
        private final int from;
        private final int to;
        private final int weight;

        DirectedEdge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return from + "->" + to + " " + weight;
        }
    }

    private static class IntPair {
        final int key;
        final int weight;

        public IntPair(int left, int right) {
            this.key = left;
            this.weight = right;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            IntPair intPair = (IntPair) o;
            return key == intPair.key;
        }

        @Override
        public int hashCode() {
            return Objects.hash(key);
        }
    }

    public static void main(String[] args) {
        int nodes = 8;
        int edges = 15;

        EdgeWeightGraph graph = new EdgeWeightGraph(nodes);
        int[][] data = new int[][]{
                {4, 5, 35},
                {5, 4, 35},
                {4, 7, 37},
                {5, 7, 28},
                {7, 5, 28},
                {5, 1, 32},
                {0, 4, 38},
                {0, 2, 26},
                {7, 3, 39},
                {1, 3, 29},
                {2, 7, 34},
                {6, 2, 40},
                {3, 6, 52},
                {6, 0, 58},
                {6, 4, 93}
        };

        for (int[] datum : data) {
            graph.addEdge(new DirectedEdge(datum[0], datum[1], datum[2]));
        }

        Dijkstra d = new Dijkstra(graph, 0);
        assert d.distTo[0] == 0;
        assert d.distTo[1] == 105;
        assert d.distTo[2] == 26;
        assert d.distTo[3] == 99;
        assert d.distTo[4] == 38;
        assert d.distTo[5] == 73;
        assert d.distTo[6] == 151;
        assert d.distTo[7] == 60;

        assert d.edgeTo[1].from == 5;
        assert d.edgeTo[5].from == 4;
        assert d.edgeTo[4].from == 0;

        assert d.edgeTo[6].from == 3;
        assert d.edgeTo[3].from == 7;
        assert d.edgeTo[7].from == 2;
        assert d.edgeTo[2].from == 0;

        assert false;
    }
}
