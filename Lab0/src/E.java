import java.util.Scanner;

public class E {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int t = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < t; i++) {
            solve();
        }
    }

    private static void solve() {
        String line = scanner.nextLine();
        if (line.startsWith("(")) {
            long num = 1;

            int spiltPoint = line.indexOf(',');
            long y = Integer.parseInt(line.substring(1, spiltPoint)) - 1;
            long x = Integer.parseInt(line.substring(spiltPoint + 1, line.length() - 1)) - 1;

            while (x != 0 || y != 0) {
                long lineSize = Math.max(pow2(log2(x)), pow2(log2(y)));
                long blockSize = lineSize * lineSize;
                long divX = x / lineSize;
                long divY = y / lineSize;
                if (divY == 1 & divX == 0) {
                    num += blockSize;
                } else if (divY == 1 & divX == 1) {
                    num += blockSize * 2;
                } else if (divY == 0 & divX == 1) {
                    num += blockSize * 3;
                }
                x %= lineSize;
                y %= lineSize;
            }
            System.out.println(num);

        } else {
            long x = 1;
            long y = 1;

            long a = Long.parseLong(line) - 1;
            while (a != 0) {
                long blockSize = pow4(log4(a));
                long lineSize = sqrt(blockSize);

                int div = (int) ((a) / blockSize);
                if (div == 1) {
                    y += lineSize;
                } else if (div == 2) {
                    y += lineSize;
                    x += lineSize;
                } else if (div == 3) {
                    x += lineSize;
                }

                a %= blockSize;
            }
            System.out.println("(" + y + "," + x + ")");
        }
    }

    private static long log2(long a) {
        if (a == 0) {
            return 0;
        }
        return (long) (Math.log(a) / Math.log(2));
    }

    private static long pow2(long a) {
        return (long) Math.pow(2, a);
    }

    private static long log4(long a) {
        if (a == 0) {
            return 0;
        }
        return (long) (Math.log(a) / Math.log(4));
    }

    private static long pow4(long a) {
        return (long) Math.pow(4, a);
    }

    private static long sqrt(long a) {
        return (long) Math.sqrt(a);
    }
}
