import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

import static java.lang.Math.abs;
import static java.lang.Math.min;

public class F {

    static class InputReader {
        public BufferedReader reader;
        public StringTokenizer tokenizer;

        public InputReader(InputStream stream) {
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
        InputReader scanner = new InputReader(System.in);
        int n = scanner.nextInt();

        int[][] links = new int[n][];
        int[][] arr = new int[n][];
        for (int i = 0; i < n; i++) {
            arr[i] = new int[4];
            links[i] = arr[i];

            arr[i][0] = scanner.nextInt();
            arr[i][1] = i;
        }
        arr = Arrays.stream(arr).sorted(Comparator.comparingInt(it -> it[0])).toArray(int[][]::new);

        int[] last = arr[0];
        for (int i = 1; i < n; i++) {
            int[] now = arr[i];

            last[2] = now[1];  //  0   1   2   3
            now[3] = last[1];  // num ind succ pred

            last = now;
        }
        arr[0][3] = -1;
        arr[n - 1][2] = -1;

        for (int i = 0; i < n - 1; i++) {
            int[] now = links[i];

            if (now[3] == -1) {
                int[] succ = links[now[2]];
                System.out.print(abs(now[0] - succ[0]) + " ");
                succ[3] = -1;
            } else if (now[2] == -1) {
                int[] pred = links[now[3]];
                System.out.print(abs(now[0] - pred[0]) + " ");
                pred[2] = -1;
            } else {
                int[] pred = links[now[3]];
                int[] succ = links[now[2]];
                pred[2] = succ[1];
                succ[3] = pred[1];
                System.out.print(min(abs(now[0] - succ[0]), abs(now[0] - pred[0])) + " ");
            }

        }
    }
}


