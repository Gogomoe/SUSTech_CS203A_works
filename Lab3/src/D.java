import java.util.Scanner;

public class D {

    static class Link {
        Link front;
        Link next;
        int index;
        int num;

        public Link(int index, int num) {
            this.index = index;
            this.num = num;
        }

        @Override
        public String toString() {
            return "Link{" +
                    "index=" + index +
                    '}';
        }
    }

    static Scanner scanner;

    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        for (int i = 0; i < t; i++) {
            process();
        }
    }

    private static void process() {
        int n = scanner.nextInt();
        int m = scanner.nextInt();

        Link head = new Link(-1, -1);
        Link last = head;
        for (int i = 0; i < n; i++) {
            Link now = new Link(i + 1, scanner.nextInt());
            last.next = now;
            now.front = last;

            last = now;
        }
        Link now = head.next;
        last.next = now;
        now.front = last;

        int move = m % n;
        int moveFront = n - move;
        if (move <= moveFront) {
            for (int i = 0; i < move; i++) {
                now = now.next;
            }
        } else {
            for (int i = 0; i < moveFront; i++) {
                now = now.front;
            }
        }
        Link toRemove = now.front;
        Link front = toRemove.front;

        front.next = now;
        now.front = front;
        n--;

        while (n > 1) {
            move = toRemove.num % n;
            moveFront = n - move;
            if (move <= moveFront) {
                for (int i = 0; i < move; i++) {
                    now = now.next;
                }
            } else {
                for (int i = 0; i < moveFront; i++) {
                    now = now.front;
                }
            }

            toRemove = now.front;
            front = toRemove.front;

            front.next = now;
            now.front = front;
            n--;
        }
        System.out.println(now.index);

    }
}
