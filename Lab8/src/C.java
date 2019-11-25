import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class C {

    public static void main(String[] args) {
        int n = scanner.nextInt();
        int m = scanner.nextInt();

        EdgeWeightGraph graph = new EdgeWeightGraph(n);

        for (int i = 0; i < m; i++) {
            int from = scanner.nextInt() - 1;
            int to = scanner.nextInt() - 1;
            int weight = scanner.nextInt();

            graph.addEdge(new DirectedEdge(from, to, weight));
        }

        Dijkstra d = new Dijkstra(graph, 0);
        System.out.println(d.distTo[n - 1]);
    }

    private static class Dijkstra {

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
                    distTo[e.to] = distTo[e.from] + e.weight;
                    edgeTo[e.to] = e;

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

    private static QuickReader scanner = new QuickReader(System.in);

    private static class QuickReader {
        BufferedReader reader;
        StringTokenizer tokenizer;

        QuickReader(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream), 32768);
            tokenizer = null;
        }

        String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }
    }
}
