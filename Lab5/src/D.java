import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class D {

    public static void main(String[] args) {
        QuickReader scanner = new QuickReader(System.in);
        int t = scanner.nextInt();
        for (int i = 0; i < t; i++) {
            process(scanner);
        }
    }

    private static void process(QuickReader scanner) {
        char[] chars = scanner.next().toCharArray();
        int len = chars.length;

        int[] next = new int[len + 1];
        next[0] = -1;
        for (int j = 0; j < chars.length; j++) {
            int k = next[j];
            while (k != -1) {
                if (chars[k] == chars[j]) {
                    next[j + 1] = k + 1;
                    break;
                }
                k = next[k];
            }
        }

        int L = len - next[len];
        if (len % L == 0) {
            if (len == L) {
                System.out.println(len);
            } else {
                System.out.println(0);
            }
        } else {
            System.out.println(L - len % L);
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
