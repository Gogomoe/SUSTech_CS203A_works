import java.io.*;
import java.util.StringTokenizer;

public class A {

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

    public static void main(String[] args) {
        PrintStream out = new PrintStream(new BufferedOutputStream(System.out, 32768));
        int n = scanner.nextInt();
        Queue queue = new Queue();
        for (int i = 0; i < n; i++) {
            String op = scanner.next();
            switch (op) {
                case "E":
                    int next = scanner.nextInt();
                    queue.enQueue(next);
                    break;
                case "D":
                    queue.deQueue();
                    break;
                case "A":
                    out.println(queue.first());
                    break;
            }
        }

        for (int i = queue.front; i < queue.tail; i++) {
            out.print(queue.ints[i] + " ");
        }

        out.close();
    }

    private static class Queue {

        int[] ints = new int[20000000];
        int front = 0;
        int tail = 0;

        public void enQueue(int x) {
            ints[tail] = x;
            tail++;
        }

        public void deQueue() {
            front++;
        }

        public int first() {
            return ints[front];
        }
    }

}
