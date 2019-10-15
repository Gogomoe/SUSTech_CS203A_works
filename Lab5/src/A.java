import java.util.Scanner;

public class A {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        for (int i = 0; i < t; i++) {
            int len = scanner.next().length();
            System.out.println(len * (len + 1) / 2);
        }
    }
}
