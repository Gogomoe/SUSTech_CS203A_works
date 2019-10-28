import java.io.*;
import java.util.StringTokenizer;

public class C {


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
        Node left;
        Node right;

        Node(int no) {
            this.no = no;
        }

        void post() {
            if (left != null) {
                left.post();
            }
            if (right != null) {
                right.post();
            }
            out.print(no + " ");
        }
    }

    static int n;
    static int[] pre;
    static int[] inorder;
    static int start;

    private static void process() {
        n = scanner.nextInt();
        pre = new int[n];
        inorder = new int[n];
        for (int i = 0; i < n; i++) {
            pre[i] = scanner.nextInt();
        }
        for (int i = 0; i < n; i++) {
            inorder[i] = scanner.nextInt();
        }

        start = 0;
        getNode(0, n).post();
        out.println();
    }

    private static Node getNode(int L, int R) {
        if (L >= R || start >= n) {
            return null;
        }
        Node top = new Node(pre[start++]);
        int index = indexOf(inorder, top.no, L, R);
        top.left = getNode(L, index);
        top.right = getNode(index + 1, R);
        return top;
    }

    private static int indexOf(int[] arr, int v, int L, int R) {
        for (int i = L; i < R; i++) {
            if (arr[i] == v) {
                return i;
            }
        }
        return -1;
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
