import java.util.Scanner;

public class D {

    private static int[] arr;
    private static int[] tmp;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        arr = new int[n];
        tmp = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }
        long cost = sort(0, n);
        System.out.println(cost);
    }

    private static long sort(int l, int r) {
        if (l + 1 >= r) {
            return 0;
        }
        int mid = (l + r) / 2;
        long cost1 = sort(l, mid);
        long cost2 = sort(mid, r);

        long cost = 0;
        int i = l;
        int j = mid;
        int k = l;
        while (i < mid && j < r) {
            if (arr[i] <= arr[j]) {
                tmp[k] = arr[i];
                cost += (long) (j - mid) * arr[i];
                i++;
                k++;
            } else {
                tmp[k] = arr[j];
                cost += (long) (mid - i) * arr[j];
                j++;
                k++;
            }
        }
        while (i < mid) {
            tmp[k] = arr[i];
            cost += (long) (j - mid) * arr[i];
            i++;
            k++;
        }
        while (j < r) {
            tmp[k] = arr[j];
            j++;
            k++;
        }
        System.arraycopy(tmp, l, arr, l, r - l);
        return cost + cost1 + cost2;
    }

}