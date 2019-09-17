import java.util.Arrays;
import java.util.Scanner;

public class C {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int[] full = new int[n];

        for (int i = 0; i < n; i++) {
            full[i] = scanner.nextInt();
        }

        full = Arrays.stream(full).sorted().toArray();

        int[] arr = new int[n];
        int[] counts = new int[n];
        int size = 0;
        int last = -1;
        for (int i = 0; i < n; i++) {
            if (full[i] == last) {
                counts[size - 1]++;
            } else {
                last = full[i];
                arr[size] = full[i];
                counts[size] = 1;
                size++;
            }
        }

        int div3 = (int) Math.ceil(m / 3.0);
        int i = 0;
        long count = 0;
        while (i < size && arr[i] <= div3) {
            int j = i + 1;
            int k = size - 1;
            while (j < k) {
                long sum = (long) arr[i] + arr[j] + arr[k];
                if (sum == m) {
                    count += (long) counts[i] * counts[j] * counts[k];
                    j++;
                } else if (sum < m) {
                    j++;
                } else {
                    k--;
                }
            }
            i++;
        }
        for (int j = 0; j < size; j++) {
            int countJ = counts[j];
            if (arr[j] * 3 == m) {
                if (countJ >= 3) {
                    count += (long) countJ * (countJ - 1) * (countJ - 2) / 6;
                }
                continue;
            }
            if (countJ < 2) {
                continue;
            }
            int remain = m - arr[j] * 2;
            int pos = lowerBound(arr, 0, size, remain);
            if (arr[j] * 2L + arr[pos] == m) {
                count += (long) countJ * (countJ - 1) / 2 * counts[pos];
            }
        }
        System.out.println(count);
    }

    public static int lowerBound(int[] nums, int begin, int end, int value) {
        while (begin < end) {
            int mid = begin + (end - begin) / 2;
            if (nums[mid] < value) {
                begin = mid + 1;
            } else {
                end = mid;
            }
        }
        return begin;
    }
}