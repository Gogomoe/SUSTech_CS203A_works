import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class F {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        for (int i = 0; i < t; i++) {
            calc(scanner);
        }
    }

    private static void calc(Scanner scanner) {
        int n = scanner.nextInt();
        int[] as = new int[n];
        int[] bs = new int[n];
        int[][] ss = new int[n][];
        for (int i = 0; i < n; i++) {
            as[i] = scanner.nextInt();
            bs[i] = scanner.nextInt();
            ss[i] = new int[2];
            ss[i][0] = as[i];
            ss[i][1] = bs[i];
        }

        ss = Arrays.stream(ss).sorted(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                int dis1 = Integer.signum(o1[1] - o1[0]);
                int dis2 = Integer.signum(o2[1] - o2[0]);
                if (dis1 > dis2) {
                    return -1;
                } else if (dis1 < dis2) {
                    return 1;
                } else {
                    if (dis1 == 0) {
                        return 0;
                    } else if (dis1 == 1) {
                        return o1[0] - o2[0];
                    } else {
                        return o2[1] - o1[1];
                    }
                }
            }
        }).toArray(int[][]::new);

        int min = 0;
        int k = 0;
        int count = 0;
        for (int i = 0; i < ss.length; i++) {
            count += ss[i][0];
            count += ss[i][1];
            k -= ss[i][0];
            min = Math.min(k, min);
            k += ss[i][1];
        }
        System.out.println((count - Math.abs(min) - (k - min)) / 2);
    }

}
