import java.util.Scanner;

public class B {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        for (int i = 0; i < t; i++) {
            process(scanner);
        }
    }

    private static void process(Scanner scanner) {
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        String s = scanner.next();
        String t = scanner.next();

        boolean res = t.matches(s.replace("*", ".*"));
        if (res) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }
}
