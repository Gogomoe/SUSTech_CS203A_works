import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class F_1 {

    static class Link {
        Link pred;
        Link succ;
        int num;

        public Link(int num) {
            this.num = num;
        }
    }

    static Scanner scanner;

    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[][] arr = new int[n][];
        for (int i = 0; i < n; i++) {
            arr[i] = new int[2];
            arr[i][0] = i;
            arr[i][1] = scanner.nextInt();
        }

        arr = Arrays.stream(arr).sorted(Comparator.comparingInt(it -> it[1])).toArray(int[][]::new);
        Link head = new Link(-1);
        Link tail = new Link(-2);
        Link[] links = new Link[n];

        Link last = head;
        for (int i = 0; i < n; i++) {
            Link now = new Link(arr[i][1]);
            links[arr[i][0]] = now;

            last.succ = now;
            now.pred = last;

            last = now;
        }
        last.succ = tail;
        tail.pred = last;

        for (int i = 0; i < n - 1; i++) {
            Link now = links[i];
            Link pred = now.pred;
            Link succ = now.succ;
            int diffPred = Math.abs(now.num - pred.num);
            int diffSucc = Math.abs(now.num - succ.num);

            if (pred == head) {
                System.out.print(diffSucc + " ");
            } else if (succ == tail) {
                System.out.print(diffPred + " ");
            } else {
                System.out.print(Math.min(diffSucc, diffPred) + " ");
            }

            pred.succ = succ;
            succ.pred = pred;
        }
    }
}
