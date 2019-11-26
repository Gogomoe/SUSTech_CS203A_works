import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class F {

    public static void main(String[] args) {
        int t = scanner.nextInt();
        for (int i = 0; i < t; i++) {
            process();
        }
    }

    private static int n;
    private static int[] dp;
    private static int[][] cube;
    private static boolean[][] adj;

    private static void process() {
        n = scanner.nextInt();
        cube = new int[n][3];
        adj = new boolean[n][n];
        dp = new int[n];
        for (int i = 0; i < n; i++) {
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            int c = scanner.nextInt();
            cube[i][0] = a;
            cube[i][1] = b;
            cube[i][2] = c;


        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    continue;
                }
                int a1 = cube[i][0];
                int b1 = cube[i][1];

                int a2 = cube[j][0];
                int b2 = cube[j][1];
                if ((a2 < a1 && b2 < b1) || (a2 < b1 && b2 < a1)) {
                    adj[i][j] = true;
                }
            }
        }
        int max = 0;
        for (int i = 0; i < n; i++) {
            max = Math.max(max, DP(i));
        }
        System.out.println(max);

    }


    private static int DP(int i) {
        if (dp[i] > 0) return dp[i];

        int max = 0;
        for (int j = 0; j < n; j++) {
            if (adj[i][j]) {
                max = Math.max(max, DP(j));
            }
        }
        dp[i] = max + cube[i][2];

        return dp[i];
    }


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
