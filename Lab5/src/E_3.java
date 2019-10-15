import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class E_3 {

    private static char[] str1, str2;

    public static void main(String[] args) {
        QuickReader scanner = new QuickReader(System.in);
        str1 = scanner.next().toCharArray();
        str2 = scanner.next().toCharArray();

        if (str1.length > str2.length) {
            char[] tmp = str1;
            str1 = str2;
            str2 = tmp;
        }
        int L = 0, R = str1.length;
        while (L < R) {
            int mid = (L + R + 1) / 2;
            if (check(mid)) {
                L = mid;
            } else {
                R = mid - 1;
            }
        }
        System.out.println(L);
    }

    private static long[] hash1 = new long[100000], hash2 = new long[100000];

    private static boolean check(int length) {
        int len1 = str1.length - length + 1;
        int len2 = str2.length - length + 1;

        long hash = hash(str1, 0, length);
        hash1[0] = hash;
        for (int i = 1; i < len1; i++) {
            hash = hashFrom(hash, str1[i - 1], str1[i + length - 1], length);
            hash1[i] = hash;
        }

        hash = hash(str2, 0, length);
        hash2[0] = hash;
        for (int i = 1; i < len2; i++) {
            hash = hashFrom(hash, str2[i - 1], str2[i + length - 1], length);
            hash2[i] = hash;
        }

        Arrays.sort(hash2, 0, len2);

        for (int i = 0; i < len1; i++) {
            long it = hash1[i];
            int L = 0, R = len2;
            while (L < R) {
                int mid = (L + R) / 2;
                long other = hash2[mid];

                if (other > it) {
                    R = mid;
                } else if (other < it) {
                    L = mid + 1;
                } else {
                    return true;
                }
            }
        }

        return false;
    }

    private static final int d = 193;
    private static final long p = 1610612741;

    private static final long[] dpow = new long[100000];

    static {
        dpow[0] = 1;
        for (int i = 0; i < dpow.length - 1; i++) {
            dpow[i + 1] = (dpow[i] * d) % p;
        }
    }

    private static long hash(char[] chars, int start, int end) {
        long hash = 0;
        for (int i = start; i < end; i++) {
            hash = (hash * d + chars[i]) % p;
        }
        return hash;
    }

    private static long hashFrom(long last, char remove, char it, int length) {
        return (((last - remove * dpow[length - 1]) * d + it) % p + p) % p;
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
