import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.Math.pow;

public class D {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        for (int i = 0; i < n; i++) {
            System.out.println(count(scanner.nextLong()));
        }
    }

    private static ArrayList<Integer> nums = new ArrayList<>();

    static {
        nums.add(0);
        for (int i = 1; i <= 9; i++) {
            nums.add(i);
            nums.add(i * 11);
        }
    }

    private static int count(long n) {
        if (n < 100) {
            return (int) nums.stream().filter(it -> it <= n).count();
        }
        int length = getLen(n);

        int start = pow10(length / 2 - 1);

        if (length % 2 == 1) {
            int remain = (int) (n / pow10(length / 2 + 1));
            int head = (remain - start) * 10;
            int end = 0;
            for (int i = 0; i <= 9; i++) {
                if (remain * pow10L(length / 2 + 1) + i * pow10(length / 2) + reverse(remain) <= n) {
                    end++;
                }
            }
            return head + end + count(pow10L(length - 1) - 1);
        } else {
            int remain = (int) (n / pow10(length / 2));
            int head = (remain - start);
            int end = 0;
            if (remain * pow10L(length / 2) + reverse(remain) <= n) {
                end++;
            }
            return head + end + count(pow10L(length - 1) - 1);
        }
    }

    private static int reverse(int x) {
        int res = 0;
        while (x != 0) {
            res *= 10;
            int i = x % 10;
            res += i;
            x /= 10;
        }
        return res;
    }

    private static int getLen(long n) {
        return (int) Math.ceil(Math.log10(n + 0.5));
    }

    private static int pow10(int n) {
        return (int) pow(10, n);
    }

    private static long pow10L(int n) {
        return (long) pow(10, n);
    }
}
