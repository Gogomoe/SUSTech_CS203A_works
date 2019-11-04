import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class B {

    private static QuickReader scanner = new QuickReader(System.in);

    public static void main(String[] args) {
        int t = scanner.nextInt();
        for (int i = 0; i < t; i++) {
            boolean res = process();
            System.out.println("Case #" + (i + 1) + ": " + (res ? "YES" : "NO"));
        }
    }

    private static final Tree NULL = new Tree(-1);

    private static class Tree {

        int n;
        int value;
        Tree parent;
        Tree left;
        Tree right;

        public Tree(int n) {
            this.n = n;
        }

        public boolean isMin() {
            if (left != NULL && right != NULL) {
                return value <= left.value && value <= right.value && left.isMin() && right.isMin();
            } else if (left != NULL) {
                return value <= left.value && left.isMin();
            } else {
                return true;
            }
        }

        public boolean isMax() {
            if (left != NULL && right != NULL) {
                return value >= left.value && value >= right.value && left.isMax() && right.isMax();
            } else if (left != NULL) {
                return value >= left.value && left.isMax();
            } else {
                return true;
            }
        }

    }

    private static boolean process() {
        int n = scanner.nextInt();
        Tree[] trees = new Tree[n + 1];
        for (int i = 1; i <= n; i++) {
            trees[i] = new Tree(i);
            trees[i].left = NULL;
            trees[i].right = NULL;
        }

        for (int i = 1; i <= n; i++) {
            trees[i].value = scanner.nextInt();
        }
        for (int i = 1; i <= n - 1; i++) {
            int parent = scanner.nextInt();
            int child = scanner.nextInt();
            Tree pn = trees[parent];
            Tree cn = trees[child];
            if (pn.left == NULL && pn.right == NULL) {
                pn.left = cn;
            } else if (pn.right == NULL) {
                pn.right = cn;
            } else {
                return false;
            }
            cn.parent = pn;
        }

        Tree root = null;
        for (int i = 1; i <= n; i++) {
            if (trees[i].parent == null) {
                root = trees[i];
            }
        }

        ArrayDeque<Tree> queue = new ArrayDeque<>();
        queue.add(root);
        while (queue.peek() != NULL) {
            Tree t = queue.poll();
            queue.addLast(t.left);
            queue.addLast(t.right);
        }
        while (!queue.isEmpty()) {
            if (queue.poll() != NULL) {
                return false;
            }
        }

        return root.isMax() || root.isMin();

    }

    static class QuickReader {
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
