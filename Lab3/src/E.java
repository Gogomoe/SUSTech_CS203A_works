import java.util.Scanner;

public class E {

    static class Link {
        Link next;
        int num;

        public Link(int num) {
            this.num = num;
        }
    }

    static Scanner scanner;

    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        for (int i = 0; i < t; i++) {
            process();
        }
    }

    private static void process() {
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int max = m;

        Link fast = new Link(m);
        Link slow = fast;
        int i = 0;

        while (true) {
            long num = (long) fast.num * fast.num;
            int length = (int) Math.ceil(Math.log10(num + 0.5));
            if (length > n) {
                m = (int) (num / pow10(length - n));
            } else {
                m = (int) num;
            }
            max = Math.max(max, m);

            Link now = new Link(m);
            fast.next = now;
            fast = now;

            i = (i + 1) % 2;
            if (i == 0) {
                slow = slow.next;
            }

            if (fast.num == slow.num) {
                System.out.println(max);
                return;
            }

        }
    }

    private static long[] pow10 = new long[18];

    static {
        long it = 1;
        for (int i = 0; i < pow10.length; i++) {
            pow10[i] = it;
            it *= 10;
        }
    }

    private static long pow10(int n) {
        return pow10[n];
    }

}
