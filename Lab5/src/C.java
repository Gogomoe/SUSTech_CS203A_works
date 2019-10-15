import java.util.Scanner;

public class C {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int test = scanner.nextInt();
        int count = 0;
        out:
        for (int i = 0; i < test; i++) {
            char[] str1 = scanner.next().toCharArray();
            char[] str2 = scanner.next().toCharArray();

            int length = (int) Math.ceil(str1.length / 3.0);
            if (str2.length < length) {
                continue;
            }
            long hash1 = hash(str1, 0, length);
            long hash2 = hash(str2, 0, length);
            int from = 0;
            if (check(str1, str2, length, hash1, hash2, from)) {
                count++;
                continue;
            }
            for (from++; from <= str2.length - length; from++) {
                hash2 = hashFrom(hash2, str2[from - 1], str2[from - 1 + length], length);
                if (check(str1, str2, length, hash1, hash2, from)) {
                    count++;
                    continue out;
                }
            }
        }
        System.out.println(count);
    }

    private static boolean check(char[] str1, char[] str2, int length, long hash1, long hash2, int from) {
        if (hash1 != hash2) {
            return false;
        }
        for (int i = 0; i < length; i++) {
            if (str1[i] != str2[from + i]) {
                return false;
            }
        }
        return true;
    }

    private static final int d = 193;
    private static final int p = 1610612741;

    private static long hash(char[] chars, int start, int end) {
        long hash = 0;
        for (int i = start; i < end; i++) {
            hash = (hash * d + chars[i]) % p;
        }
        return hash;
    }

    private static final long[] dpow = new long[3400];

    static {
        dpow[0] = 1;
        for (int i = 0; i < dpow.length - 1; i++) {
            dpow[i + 1] = (dpow[i] * d) % p;
        }
    }

    private static long hashFrom(long last, char remove, char it, int length) {
        return (((last - remove * dpow[length - 1]) * d + it) % p + p) % p;
    }
}
