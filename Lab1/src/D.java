import java.util.Scanner;

public class D {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        long m = scanner.nextLong();
        int size = 0;

        int full[] = new int[n];
        int arr[] = new int[n];

        int last = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            int num = scanner.nextInt();
            full[i] = num;
            if (num == last) {
                continue;
            }
            arr[size] = num;
            last = num;
            size++;
        }

        if (m == 0) {
            int zeroPos = lowerBound(full, 0, n, 0);
            if (zeroPos == n || full[zeroPos] != 0) {
                System.out.println(0);
            } else {
                int count = 0;
                if (zeroPos + 1 < n && full[zeroPos + 1] == 0) {
                    count++;
                }
                count += size - 1;
                System.out.println(count);
            }
        } else {
            int count = 0;

            int sqrt = (int) Math.sqrt(Math.abs(m));
            int sqrtPos = lowerBound(full, 0, n, sqrt);
            if (sqrtPos + 1 < n && ((long) full[sqrtPos]) * full[sqrtPos + 1] == m) {
                count++;
            }

            for (int i = 0; i < arr.length - 1; i++) {
                int num = arr[i];
                if (num == 0) {
                    continue;
                }
                long toSearch = m / num;
                if (toSearch > arr[size - 1] || toSearch < arr[0]) {
                    continue;
                }
                int toSearchI = (int) toSearch;
                int pos = lowerBound(arr, i + 1, size, toSearchI);
                if (((long) num) * arr[pos] == m) {
                    count++;
                }
            }
            System.out.println(count);
        }
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
