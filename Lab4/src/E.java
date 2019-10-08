import java.io.*;
import java.util.StringTokenizer;

public class E {

    private static QuickReader scanner = new QuickReader(System.in);
    private static PrintStream out = new PrintStream(new BufferedOutputStream(System.out, 1024 * 1024));

    public static void main(String[] args) {
        int t = scanner.nextInt();
        for (int i = 0; i < t; i++) {
            out.println("Case " + (i + 1) + ":");
            process();
        }
        out.close();
    }

    private static void process() {
        int n = scanner.nextInt();
        int[] arr = new int[n + 1];
        int[] left = new int[n + 1];
        int[] right = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            arr[i] = scanner.nextInt();
        }
        Stack students = new Stack(n);
        Stack indexes = new Stack(n);
        for (int i = 1; i <= n; i++) {
            int it = arr[i];
            int rightIndex = 0;
            while (students.isNotEmpty() && students.last() < it) {
                int index = indexes.last();
                right[index] = rightIndex;
                rightIndex = index;
                students.removeLast();
                indexes.removeLast();
            }
            left[i] = rightIndex;
            students.addLast(it);
            indexes.addLast(i);
        }
        int rightIndex = 0;
        while (students.isNotEmpty()) {
            int index = indexes.last();
            right[index] = rightIndex;
            rightIndex = index;
            students.removeLast();
            indexes.removeLast();
        }

        students = new Stack(n);
        indexes = new Stack(n);
        for (int i = n; i >= 1; i--) {
            int it = arr[i];
            int leftIndex = 0;
            while (students.isNotEmpty() && students.last() < it) {
                int index = indexes.last();
                left[index] = leftIndex;
                leftIndex = index;
                students.removeLast();
                indexes.removeLast();
            }
            right[i] = leftIndex;
            students.addLast(it);
            indexes.addLast(i);
        }
        int leftIndex = 0;
        while (students.isNotEmpty()) {
            int index = indexes.last();
            left[index] = leftIndex;
            leftIndex = index;
            students.removeLast();
            indexes.removeLast();
        }

        for (int i = 1; i <= n; i++) {
            out.println(left[i] + " " + right[i]);
        }
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

        public boolean isNotEmpty() {
            return !isEmpty();
        }

    }


    private static class QuickReader {
        public BufferedReader reader;
        public StringTokenizer tokenizer;

        public QuickReader(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream), 1024 * 1024);
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
