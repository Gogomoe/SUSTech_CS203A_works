import java.io.*;
import java.util.StringTokenizer;

public class A {

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
        int[][] roads = new int[n][n];

        for (int i = 0; i < m; i++) {
            roads[scanner.nextInt() - 1][scanner.nextInt() - 1]++;
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < roads[i].length; j++) {
                out.print(roads[i][j] + " ");
            }
            out.println();
        }
    }

    private static PrintWriter out = new PrintWriter(System.out);

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
}
