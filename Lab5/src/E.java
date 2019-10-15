import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class E {

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


    private static boolean check(int length) {
        int len1 = str1.length - length + 1;
        int len2 = str2.length - length + 1;

        HashSet<Long> set1 = new HashSet<>();
        HashSet<Long> set2 = new HashSet<>();

        long hash = hash(str1, 0, length);
        set1.add(hash);
        for (int i = 1; i < len1; i++) {
            hash = hashFrom(hash, str1[i - 1], str1[i + length - 1], length);
            set1.add(hash);
        }

        hash = hash2(str1, 0, length);
        set2.add(hash);
        for (int i = 1; i < len1; i++) {
            hash = hashFrom2(hash, str1[i - 1], str1[i + length - 1], length);
            set2.add(hash);
        }

        long[][] tmp = new long[len2][];

        long h1 = hash(str2, 0, length);
        long h2 = hash2(str2, 0, length);
        tmp[0] = new long[]{h1, h2};
        for (int i = 1; i < len2; i++) {
            h1 = hashFrom(h1, str2[i - 1], str2[i + length - 1], length);
            h2 = hashFrom2(h2, str2[i - 1], str2[i + length - 1], length);
            tmp[i] = new long[]{h1, h2};
        }


        for (long[] longs : tmp) {
            if (set1.contains(longs[0]) && set2.contains(longs[1])) {
                return true;
            }
        }

        return false;
    }

    private static final long d = 301;
    private static final long p = 899999777;
    private static final long p2 = 1000000411;

    private static final long[] dpow1 = new long[100001];
    private static final long[] dpow2 = new long[100001];

    static {
        dpow1[0] = 1;
        for (int i = 0; i < dpow1.length - 1; i++) {
            dpow1[i + 1] = (dpow1[i] * d) % p;
        }
        dpow2[0] = 1;
        for (int i = 0; i < dpow2.length - 1; i++) {
            dpow2[i + 1] = (dpow2[i] * d) % p2;
        }
    }

    private static long hash(char[] chars, int start, int end) {
        long hash = 0;
        for (int i = start; i < end; i++) {
            hash = ((hash * d + chars[i]) % p + p) % p;
        }
        return hash;
    }

    private static long hashFrom(long last, char remove, char it, int length) {
        return (((last - remove * dpow1[length - 1]) * d + it) % p + p) % p;
    }

    private static long hash2(char[] chars, int start, int end) {
        long hash = 0;
        for (int i = start; i < end; i++) {
            hash = ((hash * d + chars[i]) % p2 + p2) % p2;
        }
        return hash;
    }

    private static long hashFrom2(long last, char remove, char it, int length) {
        return (((last - remove * dpow2[length - 1]) * d + it) % p2 + p2) % p2;
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
