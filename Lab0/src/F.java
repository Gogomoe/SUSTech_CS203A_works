import java.util.Scanner;

public class F {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int t = scanner.nextInt();
        for (int i = 0; i < t; i++) {
            solve();
        }
    }

    private static void solve() {
        int num = scanner.nextInt();
        int k = 2;
        int amount = 3;
        while (true) {
            if (num < amount) {
                System.out.println("impossible");
                return;
            }
            if ((num - amount) % k == 0) {
                System.out.println(k);
                return;
            }
            k++;
            amount += k;
        }
    }
}
