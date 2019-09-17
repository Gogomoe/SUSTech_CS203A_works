import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class E {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int p = scanner.nextInt();
        int q = scanner.nextInt();

        int[][] ps = new int[n][];

        for (int i = 0; i < n; i++) {
            ps[i] = new int[2];
            ps[i][0] = scanner.nextInt();
            ps[i][1] = scanner.nextInt();
        }

        ps = Arrays.stream(ps).sorted(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return (o1[0] - o1[1]) - (o2[0] - o2[1]);
            }
        }.reversed()).toArray(int[][]::new);

        int[] hps = new int[n];
        int[] atks = new int[n];
        for (int i = 0; i < n; i++) {
            hps[i] = ps[i][0];
            atks[i] = ps[i][1];
        }

        int positive = -1;
        for (int i = 0; i < n; i++) {
            if (hps[i] - atks[i] > 0 && (i + 1) <= q) {
                positive = i;
            } else {
                break;
            }
        }
        long power = 0;
        for (int i = 0; i <= positive; i++) {
            power += hps[i];
        }
        for (int i = positive + 1; i < n; i++) {
            power += atks[i];
        }

        if (q == 0) {
            System.out.println(power);
            return;
        }

        long maxPower = power;
        for (int i = 0; i < n; i++) {
            long tmpPower = power;
            if (i <= positive) {
                long finalHP = ((long) hps[i]) << p;
                tmpPower -= hps[i];
                tmpPower += finalHP;
            } else if ((positive + 1) < q) {
                long finalHP = ((long) hps[i]) << p;
                tmpPower -= atks[i];
                tmpPower += finalHP;
            } else {
                long finalHP = ((long) hps[i]) << p;
                tmpPower -= hps[positive];
                tmpPower += atks[positive];
                tmpPower -= atks[i];
                tmpPower += finalHP;
            }
            maxPower = Math.max(tmpPower, maxPower);
        }
        System.out.println(maxPower);
    }

}
