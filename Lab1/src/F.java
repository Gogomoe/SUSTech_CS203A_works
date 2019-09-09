import java.util.Scanner;

public class F {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        for (int i = 0; i < t; i++) {
            long n = scanner.nextLong();
            long count = 0;
            while (n != 0) {
                n = n / 5;
                count += n;
            }
            System.out.println(count);
        }
    }

}
