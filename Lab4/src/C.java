import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class C {

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
        int m = scanner.nextInt();
        Queue queue = new Queue(m);
        Queue indexs = new Queue(m);
        int index = 0;

        for (int i = 0; i < m; i++) {
            int it = scanner.nextInt();
            index++;

            if (queue.isEmpty()) {
                queue.addLast(it);
                indexs.addLast(index);
            } else {
                while (!queue.isEmpty() && queue.last() < it) {
                    queue.removeLast();
                    indexs.removeLast();
                }
                queue.addLast(it);
                indexs.addLast(index);
            }

        }
        int result = queue.first();

        while (true) {
            int it = scanner.nextInt();
            index++;
            if (it == -1) {
                break;
            }

            if (index - indexs.first() >= m) {
                queue.removeFirst();
                indexs.removeFirst();
            }
            if (queue.isEmpty()) {
                queue.addLast(it);
                indexs.addLast(index);
            } else {
                while (!queue.isEmpty() && queue.last() < it) {
                    queue.removeLast();
                    indexs.removeLast();
                }
                queue.addLast(it);
                indexs.addLast(index);
            }
            result ^= queue.first();
        }

        System.out.println(result);
    }


    private static class Queue {

        public Queue(int size) {
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
}
