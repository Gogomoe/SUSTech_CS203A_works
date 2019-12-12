import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class C {

    public static void main(String[] args) {
        int n = scanner.nextInt();
        int m = scanner.nextInt();

        Graph g1 = new Graph(n);
        Graph g2 = new Graph(n);

        for (int i = 0; i < m; i++) {
            int from = scanner.nextInt() - 1;
            int to = scanner.nextInt() - 1;

            g1.addEdge(new DirectedEdge(from, to));
            g2.addEdge(new DirectedEdge(to, from));
        }

        Kosaraju kosaraju = new Kosaraju(g1, g2);

        if (Arrays.stream(kosaraju.color).max().getAsInt() == 0) {
            System.out.println("Bravo");
        } else {
            System.out.println("ovarB");
        }
    }

    public static class Kosaraju {

        private ArrayList<Integer> list = new ArrayList<>();

        private boolean[] visited;
        private int[] color;

        private Graph graph;
        private Graph reverseGraph;

        private void dfs1(int u) {
            visited[u] = true;
            for (DirectedEdge edge : graph.adj[u]) {
                int to = edge.to;
                if (!visited[to]) {
                    dfs1(to);
                }
            }
            list.add(u);
        }

        private void paint(int u, int c) {
            color[u] = c;
            for (DirectedEdge edge : reverseGraph.adj[u]) {
                int to = edge.to;
                if (color[to] == -1) {
                    paint(to, c);
                }
            }
        }

        public Kosaraju(Graph graph, Graph reversedGraph) {
            int nodes = graph.nodes;

            this.graph = graph;
            this.reverseGraph = reversedGraph;

            visited = new boolean[nodes];
            color = new int[nodes];
            Arrays.fill(color, -1);

            for (int i = 0; i < nodes; i++) {
                if (!visited[i]) {
                    dfs1(i);
                }
            }
            int count = 0;
            for (int i = nodes - 1; i >= 0; i--) {
                if (color[list.get(i)] == -1) {
                    paint(list.get(i), count++);
                }
            }
        }
    }

    private static class Graph {

        private final int nodes;
        private int edges;

        private ArrayList<DirectedEdge>[] adj;

        Graph(int nodes) {
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

        DirectedEdge(int from, int to) {
            this.from = from;
            this.to = to;
        }

        @Override
        public String toString() {
            return from + "->" + to;
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
