import java.io.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class D {

    public static void main(String[] args) {
        int t = scanner.nextInt();
        for (int i = 0; i < t; i++) {
            process();
        }
        out.close();
    }

    private static void process() {
        int n = scanner.nextInt();
        int m = scanner.nextInt();

        ArrayList<Integer>[] maps = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            maps[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            int left = scanner.nextInt();
            int right = scanner.nextInt();

            maps[left].add(right);
            maps[right].add(left);
        }

        boolean[] searched = new boolean[n + 1];
        boolean[] odd = new boolean[n + 1];

        int oddCount = 0;
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        deque.addLast(1);
        odd[1] = true;
        searched[1] = true;
        oddCount++;


        while (!deque.isEmpty()) {
            int city = deque.removeFirst();
            for (Integer next : maps[city]) {
                if (searched[next]) {
                    continue;
                }
                odd[next] = !odd[city];
                if (odd[next]) {
                    oddCount++;
                }
                deque.addLast(next);
                searched[next] = true;
            }
        }

        boolean resIsOdd = oddCount <= (n - oddCount);
        Arrays.fill(searched, false);

        out.println(Math.min(oddCount, n - oddCount));

        deque.addLast(1);
        searched[1] = true;

        while (!deque.isEmpty()) {

            int city = deque.removeFirst();
            if ((resIsOdd && odd[city]) || (!resIsOdd && !odd[city])) {
                out.print(city + " ");
            }

            for (Integer next : maps[city]) {
                if (searched[next]) {
                    continue;
                }
                deque.addLast(next);
                searched[next] = true;
            }
        }
        out.println();
    }


    private static PrintWriter out = new PrintWriter(System.out);

    private static QuickReader scanner = new QuickReader(System.in);

    private static class QuickReader {
        BufferedReader reader;
        StringTokenizer tokenizer;

        QuickReader(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream), 32768);
            tokenizer = null;
        }

        String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }
    }
}
