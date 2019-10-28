import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class B {

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

        ArrayList<Node> list = new ArrayList<>();

        Node(int no) {
            this.no = no;
        }

        void deep(Node from) {
            for (int i = 0; i < list.size(); i++) {
                Node it = list.get(i);
                if (it != from) {
                    it.deep = deep + 1;
                    it.deep(this);
                }
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
        nodes[0].deep = 0;
        nodes[0].deep(null);
        for (int i = 0; i < n; i++) {
            System.out.print(nodes[i].deep + " ");
        }
        System.out.println();
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
