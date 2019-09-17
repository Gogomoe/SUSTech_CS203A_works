import java.util.Scanner;

public class E_1 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int p = scanner.nextInt();
        int q = scanner.nextInt();

        int[] hps = new int[n];
        int[] atks = new int[n];
        
        for (int i = 0; i < n; i++) {
            hps[i] = scanner.nextInt();
            atks[i] = scanner.nextInt();
        }

        quickSort(hps, atks, 0, n);
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

    /**
     * 排序可能有点问题 TLE 了
     */
    private static void quickSort(int[] hps, int[] atks, int l, int r) {
        if (l + 1 >= r) {
            return;
        }
        int pos = partition(hps, atks, l, r);
        quickSort(hps, atks, l, pos);
        quickSort(hps, atks, pos + 1, r);
    }

    private static int partition(int[] hps, int[] atks, int l, int r) {
        int pos = l + (int) (Math.random() * (r - l));
        int pivot = hps[pos] - atks[pos];
        int pivotHp = hps[pos];
        int pivotATK = atks[pos];

        hps[pos] = hps[l];
        atks[pos] = atks[l];

        int i = l, j = r - 1;
        while (i < j) {
            while (hps[j] - atks[j] <= pivot && i < j) {
                --j;
            }
            hps[i] = hps[j];
            atks[i] = atks[j];
            while (hps[i] - atks[i] >= pivot && i < j) {
                ++i;
            }
            hps[j] = hps[i];
            atks[j] = atks[i];
        }
        hps[i] = pivotHp;
        atks[i] = pivotATK;
        return i;
    }
}
