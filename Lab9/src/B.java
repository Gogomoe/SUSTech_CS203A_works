import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class B {

    public static void main(String[] args) {
        int n = scanner.nextInt();
        int m = scanner.nextInt();

        EdgeWeightGraph graph = new EdgeWeightGraph(n);

        for (int i = 0; i < m; i++) {
            int from = scanner.nextInt() - 1;
            int to = scanner.nextInt() - 1;
            int weight = scanner.nextInt();

            graph.addEdge(new Edge(from, to, weight));
        }


        Kruskal kruskal = new Kruskal(graph);

        int cost = 0;
        for (Edge edge : kruskal.mst) {
            cost += edge.weight;
        }

        System.out.println(cost);
    }

    private static class Kruskal {

        private PriorityQueue<Edge> pq;
        private ArrayList<Edge> mst;

        public Kruskal(EdgeWeightGraph graph) {
            int nodes = graph.nodes;
            mst = new ArrayList<>();
            pq = new PriorityQueue<>(nodes, Comparator.comparingInt(it -> it.weight));
            UnionFindSet uf = new UnionFindSet(nodes);

            pq.addAll(graph.edgeList);

            while (!pq.isEmpty() && mst.size() < nodes - 1) {
                Edge e = pq.poll();
                int a = e.p1, b = e.p2;
                if (uf.connected(a, b)) {
                    continue;
                }
                uf.union(a, b);
                mst.add(e);
            }
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

    private static QuickReader scanner = new QuickReader(System.in);

    private static class QuickReader {
        public BufferedReader reader;
        public StringTokenizer tokenizer;

        public QuickReader(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream), 32768);
            tokenizer = null;
        }

        public String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

        public long nextLong() {
            return Long.parseLong(next());
        }

        public double nextDouble() {
            return Double.parseDouble(next());
        }

    }
}
