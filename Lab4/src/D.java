import java.io.*;
import java.util.StringTokenizer;

public class D {

    private static QuickReader scanner = new QuickReader(System.in);
    private static PrintStream out = new PrintStream(new BufferedOutputStream(System.out, 32768));

    public static void main(String[] args) {
        int t = scanner.nextInt();
        for (int i = 0; i < t; i++) {
            process();
        }
        out.close();
    }

    private static void process() {
        int n = scanner.nextInt();
        int[] arr = new int[n];
        int[] min = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }
        int minNum = arr[n - 1];
        for (int i = n - 1; i >= 0; i--) {
            minNum = Math.min(arr[i], minNum);
            min[i] = minNum;
        }

        Stack stack = new Stack(n);
        for (int i = 0; i < n; i++) {
            int it = arr[i];
            int minim = min[i];
            while (!stack.isEmpty() && stack.last() < minim) {
                out.print(stack.last() + " ");
                stack.removeLast();
            }
            if (it == minim) {
                out.print(it + " ");
                continue;
            }
            stack.addLast(it);
        }
        while (!stack.isEmpty()) {
            out.print(stack.last() + " ");
            stack.removeLast();
        }
        out.println();
    }

    private static class Stack {

        public Stack(int size) {
            maxSize = size;
            ints = new int[size];
        }

        int[] ints;
        int maxSize;
        int front = 0;
        int tail = 0;

        public void addLast(int x) {
            ints[tail] = x;
            tail = (tail + 1) % maxSize;
        }

        public void removeFirst() {
            front = (front + 1) % maxSize;
        }

        public void removeLast() {
            tail = (maxSize + tail - 1) % maxSize;
        }


        public int first() {
            return ints[front];
        }

        public int last() {
            return ints[(maxSize + tail - 1) % maxSize];
        }

        public boolean isEmpty() {
            return front == tail;
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
