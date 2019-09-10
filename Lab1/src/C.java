import java.util.Scanner;

public class C {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();

        if (n >= 4) {
            System.out.println(0);
        } else if (n <= 1) {
            System.out.println(1 % m);
        } else if (n == 2) {
            System.out.println(2 % m);
        } else { // n == 3
            long ans = 1 % m;
            for (int i = 2; i <= 720; i++) {
                ans = (ans * (i % m)) % m;
            }
            System.out.println(ans);
        }

    }
}
