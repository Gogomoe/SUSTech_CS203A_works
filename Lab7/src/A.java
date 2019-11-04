import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class A {
    private static QuickReader scanner = new QuickReader(System.in);

    public static void main(String[] args) {
        int t = scanner.nextInt();
        for (int i = 0; i < t; i++) {
            process();
        }
    }

    private static class Tree {
        int n;
        Tree parent;
        Tree left;
        Tree right;

        public Tree(int n) {
            this.n = n;
        }

    }

    private static void process() {
        int n = scanner.nextInt();
        Tree[] trees = new Tree[n + 1];
        for (int i = 1; i <= n; i++) {
            trees[i] = new Tree(i);
        }
        Tree NULL = new Tree(-1);
        trees[0] = NULL;
        for (int i = 1; i <= n; i++) {
            int l = scanner.nextInt();
            int r = scanner.nextInt();
            Tree lt = trees[l];
            Tree rt = trees[r];
            trees[i].left = lt;
            trees[i].right = rt;
            if (lt != NULL) {
                lt.parent = trees[i];
            }
            if (rt != NULL) {
                rt.parent = trees[i];
            }
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
                System.out.println("No");
                return;
            }
        }
        System.out.println("Yes");
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
