import java.io.*;
import java.util.StringTokenizer;

public class A {

    private static QuickReader scanner = new QuickReader(System.in);
    private static PrintWriter out = new PrintWriter(System.out);


    public static void main(String[] args) {
        int t = scanner.nextInt();
        for (int i = 0; i < t; i++) {
            process();
        }
        out.close();
    }

    private static void process() {
        int n = scanner.nextInt();
        int k = scanner.nextInt();
        long line = 1;

        if (k == 1) {
            System.out.println(1);
            return;
        }

        while (n > line) {
            n -= line;
            line *= k;
        }

        out.println(n + (line / k) - (int) Math.ceil((double) n / k));

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
