import java.util.Arrays;
import java.util.Scanner;

public class B {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        int n = scanner.nextInt();
        scanner.nextLine();
        String moneys = scanner.nextLine();
        int[] prices = new int[n];
        for (int i = 0; i < n; i++) {
            prices[i] = scanner.nextInt();
        }
        prices = Arrays.stream(prices).sorted().toArray();

        for (String s : moneys.split(" ")) {
            int money = Integer.parseInt(s);
            int maxPos = lowerBound(prices, 0, n, money);

            if (maxPos >= n) {
                System.out.println(money - prices[n - 1]);
                continue;
            }

            if (prices[maxPos] == money) {
                System.out.println("Meow");
            } else {
                maxPos--;
                if (maxPos < 0) {
                    System.out.println(money);
                    continue;
                }
                System.out.println(money - prices[maxPos]);
            }
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
