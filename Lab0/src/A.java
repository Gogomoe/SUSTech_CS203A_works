import java.util.Scanner;

public class A {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 4; j++) {
                scanner.next();
            }
            System.out.println(scanner.next());
            for (int j = 0; j < 4; j++) {
                scanner.next();
            }
        }
    }
}
