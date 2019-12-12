import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;
import java.util.stream.Collectors;

public class Kosaraju {

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


    public static void main(String[] args) {
        int[][] data = {
                {0, 2},
                {1, 0},
                {2, 1},
                {3, 1},
                {3, 4},
                {4, 0},
                {4, 5},
                {4, 6},
                {5, 3},
                {5, 10},
                {6, 3},
                {7, 8},
                {8, 6},
                {8, 7},
                {9, 4},
                {9, 6},
                {9, 7},
                {10, 11},
                {11, 5}
        };

        Graph g1 = new Graph(12);
        Graph g2 = new Graph(12);

        for (int[] datum : data) {
            g1.addEdge(new DirectedEdge(datum[0], datum[1]));
            g2.addEdge(new DirectedEdge(datum[1], datum[0]));
        }

        Kosaraju kosaraju = new Kosaraju(g1, g2);
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        for (int i = 0; i < kosaraju.color.length; i++) {
            int color = kosaraju.color[i];
            while (color >= res.size()) {
                res.add(new ArrayList<>());
            }
            res.get(color).add(i);
        }

        res.sort(Comparator.comparingInt(ArrayList::size));
        for (ArrayList<Integer> re : res) {
            System.out.println(re.stream().map(Objects::toString).collect(Collectors.joining("-")));
        }
    }

}
