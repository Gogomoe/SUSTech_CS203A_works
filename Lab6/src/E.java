import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class E {

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
        int deep;

        int red = 0;
        int blue = 0;

        ArrayList<Node> list = new ArrayList<>();

        Node(int no) {
            this.no = no;
        }

        void calcColor(Node from) {
            for (Node node : list) {
                if (node == from) {
                    continue;
                }
                node.calcColor(this);
                this.red += node.red;
                this.blue += node.blue;
            }
        }

    }

    private static void process() {
        int n = scanner.nextInt();
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

        for (int i = 0; i < n; i++) {
            int color = scanner.nextInt();
            if (color == 1) {
                nodes[i].red++;
            } else if (color == 2) {
                nodes[i].blue++;
            }
        }

        int count = 0;
        nodes[0].calcColor(null);
        int red = nodes[0].red;
        int blue = nodes[0].blue;

        for (int i = 1; i < n; i++) {
            Node node = nodes[i];
            int r = node.red;
            int b = node.blue;
            int nr = red - r;
            int nb = blue - b;
            if ((r == 0 && nb == 0) || (b == 0 && nr == 0)) {
                count++;
            }
        }
        System.out.println(count);

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
