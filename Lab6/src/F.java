import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class F {

    private static QuickReader scanner = new QuickReader(System.in);
    private static PrintWriter out = new PrintWriter(System.out);

    public static void main(String[] args) {
        int t = scanner.nextInt();
        for (int i = 0; i < t; i++) {
            process();
        }
        out.close();
    }

    private static class Node {
        int no;
        boolean available = false;

        ArrayList<Node> list = new ArrayList<>();

        Node(int no) {
            this.no = no;
        }

        Tuple<Node, Integer> findFurthest(Node from, int distance) {
            Tuple<Node, Integer> max;
            if (this.available) {
                max = new Tuple<>(this, distance);
            } else {
                max = new Tuple<>(null, -1);
            }
            for (Node node : list) {
                if (node == from) {
                    continue;
                }
                Tuple<Node, Integer> it = node.findFurthest(this, distance + 1);
                if (it.right() > max.right()) {
                    max = it;
                }
            }
            return max;
        }

    }

    private static void process() {
        int n = scanner.nextInt();
        int k = scanner.nextInt();

        Node[] nodes = new Node[n];
        for (int i = 0; i < n; i++) {
            nodes[i] = new Node(i);
        }
        for (int i = 0; i < n - 1; i++) {
            int l = scanner.nextInt() - 1;
            int r = scanner.nextInt() - 1;

            Node ln = nodes[l];
            Node rn = nodes[r];
            ln.list.add(rn);
            rn.list.add(ln);
        }

        for (int i = 0; i < k; i++) {
            nodes[scanner.nextInt() - 1].available = true;
        }
        if (k == 1) {
            out.println(0);
            return;
        }

        Node start = nodes[0].findFurthest(null, 0).left();
        int distance = start.findFurthest(null, 0).right();
        out.println((distance + 1) / 2);
    }

    private static class Tuple<A, B> {

        private A a;
        private B b;

        public Tuple(A a, B b) {
            this.a = a;
            this.b = b;
        }

        public A left() {
            return a;
        }

        public B right() {
            return b;
        }
    }


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
