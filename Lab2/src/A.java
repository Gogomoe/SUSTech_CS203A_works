import java.util.Arrays;
import java.util.Scanner;

public class A {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        for (int i = 0; i < t; i++) {
            int count = scanner.nextInt();
            scanner.nextLine();
            int[] array = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).sorted().toArray();
            if ((count > 3 && array[count - 3] == array[count - 4]) || array[count - 3] == array[count - 2]){
                System.out.println("wa");
            } else{
                System.out.println(array[count - 3]);
            }
        }
    }

}
