import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class F_1 {

    private static Node[] nodes;

    public static void main(String[] args) {
        int n = scanner.nextInt();

        nodes = new Node[n];
        for (int i = 0; i < n; i++) {
            nodes[i] = new Node(i, scanner.nextInt(), scanner.nextInt(), scanner.nextInt(), scanner.nextInt());
        }


        for (int i = 0; i < nodes.length; i++) {
            for (int j = 0; j < nodes.length; j++) {
                if (i == j) {
                    continue;
                }
                Node a = nodes[i];
                Node b = nodes[j];
                long ax = a.x;
                long ay = a.y;
                long bx = b.x;
                long by = b.y;

                if ((ax - bx) * (ax - bx) + (ay - by) * (ay - by) <= a.r * a.r) {
                    a.out.add(j);
                    b.in.add(i);
                }
            }
        }

        Graph g1 = new Graph(n);
        Graph g2 = new Graph(n);
        for (Node node : nodes) {
            int no = node.no;
            for (Integer out : node.out) {
                g1.addEdge(new DirectedEdge(no, out));
                g2.addEdge(new DirectedEdge(out, no));
            }
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

        Map<Integer, BigNode> map = new HashMap<>();
        BigNode[] bigNodes = new BigNode[res.size()];
        for (int i = 0; i < res.size(); i++) {
            ArrayList<Integer> nodes = res.get(i);
            bigNodes[i] = new BigNode(i, nodes);
            for (Integer node : nodes) {
                map.put(node, bigNodes[i]);
            }
        }

        for (Node node : nodes) {
            for (Integer out : node.out) {
                BigNode from = map.get(node.no);
                BigNode to = map.get(out);
                if (from != to){
                    from.out.add(to.no);
                    to.in.add(from.no);
                }
            }
        }

        int cost = 0;
        for (BigNode bigNode : bigNodes) {
            if (bigNode.in.size() == 0) {
                cost += bigNode.cost;
            }
        }

        System.out.println(cost);

    }

    private static class BigNode {
        private final int no;
        private final int cost;
        private Set<Integer> inside;
        private ArrayList<Integer> out = new ArrayList<>();
        private ArrayList<Integer> in = new ArrayList<>();

        public BigNode(int no, ArrayList<Integer> inside) {
            this.no = no;
            this.inside = new HashSet(inside);
            this.cost = inside.stream().mapToInt(it -> nodes[it].t).min().getAsInt();
        }
    }

    private static class Node {
        private final int no;
        private final int x;
        private final int y;
        private final int r;
        private final int t;

        private ArrayList<Integer> out = new ArrayList<>();
        private ArrayList<Integer> in = new ArrayList<>();

        public Node(int no, int x, int y, int r, int t) {
            this.no = no;
            this.x = x;
            this.y = y;
            this.r = r;
            this.t = t;
        }

    }

    private static class Kosaraju {

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
