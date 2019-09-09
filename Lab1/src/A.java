import java.util.Scanner;

public class A {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        for (int i = 0; i < t; i++) {
            int n = scanner.nextInt();
            System.out.println((((long) n) * (n + 1) * (2 * n + 1) / 6 + ((long) n) * (1 + n) / 2) / 2);
        }
    }
}
