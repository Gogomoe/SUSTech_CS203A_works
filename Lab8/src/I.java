import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class I {

    public static void main(String[] args) {
        int t = scanner.nextInt();
        for (int i = 0; i < t; i++) {
            process();
        }
    }

    private static int n;
    private static long[] dp;
    private static LinkedList<Integer>[] adj;
    private static int[] a;
    private static int[] b;

    private static void process() {
        n = scanner.nextInt();
        int m = scanner.nextInt();
        adj = new LinkedList[n + 1];
        for (int i = 1; i <= n; i++) {
            adj[i] = new LinkedList<>();
        }
        dp = new long[n + 1];
        a = new int[n + 1];
        b = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            a[i] = scanner.nextInt();
            b[i] = scanner.nextInt();
        }
        for (int i = 1; i <= m; i++) {
            int from = scanner.nextInt();
            int to = scanner.nextInt();
            adj[from].add(to);
        }

        long res = 0;
        for (int i = 1; i <= n; i++) {
            res = (res + DP(i) * a[i] % 1000000007) % 1000000007;
        }
        System.out.println((1000000007 + res) % 1000000007);
    }

    private static long DP(int i) {
        if (dp[i] != 0) return dp[i];

        long res = 0;
        for (Integer j : adj[i]) {
            long it = DP(j);
            if (!adj[j].isEmpty()) {
                res = (res + it) % 1000000007;
            }
            res = (res + b[j]) % 1000000007;
        }
        dp[i] = res;

        return res;
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
