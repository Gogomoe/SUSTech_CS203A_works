import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class E {

    public static void main(String[] args) {
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int p = scanner.nextInt();
        int k = scanner.nextInt();

        EdgeWeightGraph graph = new EdgeWeightGraph(n);

        for (int i = 0; i < m; i++) {
            int from = scanner.nextInt() - 1;
            int to = scanner.nextInt() - 1;
            int weight = scanner.nextInt();

            graph.addEdge(new DirectedEdge(from, to, weight));
        }

        for (int i = 0; i < p; i++) {
            int from = scanner.nextInt() - 1;
            int to = scanner.nextInt() - 1;
            int weight = 0;

            graph.addPortal(new DirectedEdge(from, to, weight));
        }

        int source = scanner.nextInt() - 1;
        int desc = scanner.nextInt() - 1;

        Dijkstra d = new Dijkstra(graph, source, k);
        long min = -1;

        for (int i = 0; i <= k; i++) {
            if (d.distTo[desc][i] != -1) {
                if (min == -1) {
                    min = d.distTo[desc][i];
                }
                min = Math.min(min, d.distTo[desc][i]);
            }
        }
        System.out.println(min);
    }

    private static class Dijkstra {

        private DirectedEdge[][] edgeTo;
        private long[][] distTo;
        private PriorityQueue<IntTuple> pq;

        private static final int INFINITY = -1;

        public Dijkstra(EdgeWeightGraph graph, int source, int magic) {
            int nodes = graph.nodes;
            edgeTo = new DirectedEdge[nodes][magic + 1];
            distTo = new long[nodes][magic + 1];
            pq = new PriorityQueue<>(nodes, Comparator.comparingLong(it -> it.weight));

            for (int i = 0; i < nodes; i++) {
                for (int j = 0; j < magic + 1; j++) {
                    distTo[i][j] = INFINITY;
                }
            }
            distTo[source][magic] = 0;

            pq.add(new IntTuple(source, 0, magic));
            while (!pq.isEmpty()) {
                IntTuple next = pq.poll();
                relax(graph, next.node, next.magic);
            }
        }

        private void relax(EdgeWeightGraph graph, int node, int magic) {
            for (DirectedEdge e : graph.adj[node]) {
                if (distTo[e.to][magic] == INFINITY || distTo[e.to][magic] > distTo[e.from][magic] + e.weight) {
                    long oldDist = distTo[e.to][magic];
                    distTo[e.to][magic] = distTo[e.from][magic] + e.weight;
                    edgeTo[e.to][magic] = e;

                    IntTuple query = new IntTuple(e.to, oldDist, magic);
                    pq.remove(query);
                    pq.add(new IntTuple(e.to, distTo[e.to][magic], magic));
                }
            }
            if (magic > 0) {
                for (DirectedEdge e : graph.portal[node]) {
                    if (distTo[e.to][magic - 1] == INFINITY || distTo[e.to][magic - 1] > distTo[e.from][magic] + e.weight) {
                        long oldDist = distTo[e.to][magic - 1];
                        distTo[e.to][magic - 1] = distTo[e.from][magic] + e.weight;
                        edgeTo[e.to][magic - 1] = e;

                        IntTuple query = new IntTuple(e.to, oldDist, magic - 1);
                        pq.remove(query);
                        pq.add(new IntTuple(e.to, distTo[e.to][magic], magic - 1));
                    }
                }
            }

        }

    }

    private static class EdgeWeightGraph {

        private final int nodes;
        private int edges;
        private int portals;

        private ArrayList<DirectedEdge>[] adj;
        private ArrayList<DirectedEdge>[] portal;

        EdgeWeightGraph(int nodes) {
            this.nodes = nodes;
            this.edges = 0;
            this.portals = 0;

            adj = new ArrayList[nodes];
            portal = new ArrayList[nodes];

            for (int i = 0; i < adj.length; i++) {
                adj[i] = new ArrayList<>();
                portal[i] = new ArrayList<>();
            }
        }

        void addEdge(DirectedEdge e) {
            adj[e.from].add(e);
            edges++;
        }

        void addPortal(DirectedEdge e) {
            portal[e.from].add(e);
            portals++;
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

    private static class IntTuple {
        final int node;
        final long weight;
        final int magic;

        public IntTuple(int node, long weight, int magic) {
            this.node = node;
            this.weight = weight;
            this.magic = magic;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            IntTuple intTuple = (IntTuple) o;
            return node == intTuple.node &&
                    magic == intTuple.magic;
        }

        @Override
        public int hashCode() {
            return Objects.hash(node, magic);
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
