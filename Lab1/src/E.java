import java.util.Scanner;

public class E {

    private static int x1;
    private static int y1;
    private static int x2;
    private static int y2;
    private static int s;
    private static char[] chars;

    private static int periodX;
    private static int periodY;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        x1 = scanner.nextInt();
        y1 = scanner.nextInt();
        x2 = scanner.nextInt();
        y2 = scanner.nextInt();
        s = scanner.nextInt();
        String path = scanner.next();
        chars = path.toCharArray();

        periodX = 0;
        periodY = 0;
        for (char dir : chars) {
            if (dir == 'U') {
                periodY += 1;
            } else if (dir == 'D') {
                periodY -= 1;
            } else if (dir == 'L') {
                periodX -= 1;
            } else if (dir == 'R') {
                periodX += 1;
            }
        }
        if (distance(x1, y1, x2, y2) + s == distance(x1, y1, x2 + periodX, y2 + periodY)) {
            System.out.println(-1);
            return;
        }

        long l = 1;
        long r = 1000000000000000000l;
        long mid = -2;


        while (l < r) {
            mid = l + (r - l) / 2;
            if (remainDistance(mid) > 0) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }

        if (remainDistance(r) > 0) {
            System.out.println(-1);
            return;
        }
        System.out.println(r);

    }

    private static long remainDistance(long mid) {
        long periods = mid / s;
        long remain = mid % s;
        long finalX = x2 + periodX * periods;
        long finalY = y2 + periodY * periods;
        for (int i = 0; i < remain; i++) {
            char dir = chars[i];
            if (dir == 'U') {
                finalY += 1;
            } else if (dir == 'D') {
                finalY -= 1;
            } else if (dir == 'L') {
                finalX -= 1;
            } else if (dir == 'R') {
                finalX += 1;
            }
        }

        long distance = distance(x1, y1, finalX, finalY);
        return distance - mid;
    }

    private static long distance(int x1, int y1, long x2, long y2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }
}
