import java.util.Scanner;

public class B {

    static class Link {
        Link next;
        Link front;
        int id;

        public Link(int id) {
            this.id = id;
        }

        @Override
        public String toString() {
            return "Link{" +
                    "id=" + id +
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
        Link[] links = new Link[n];
        Link head = new Link(-1);
        Link tail = new Link(-2);

        Link last = head;
        for (int i = 0; i < n; i++) {
            int id = scanner.nextInt();
            Link node = new Link(id);
            links[id] = node;

            node.front = last;
            last.next = node;

            last = node;
        }
        last.next = tail;
        tail.front = last;


        for (int i = 0; i < m; i++) {
            Link x1 = links[scanner.nextInt()];
            Link y1 = links[scanner.nextInt()];
            Link x2 = links[scanner.nextInt()];
            Link y2 = links[scanner.nextInt()];

            Link f1 = x1.front;
            Link f2 = x2.front;
            Link n1 = y1.next;
            Link n2 = y2.next;

            if (f2 == y1) {
                f1.next = x2;
                x2.front = f1;

                y2.next = x1;
                x1.front = y2;

                y1.next = n2;
                n2.front = y1;
                continue;
            }

            f1.next = x2;
            x2.front = f1;

            f2.next = x1;
            x1.front = f2;

            n1.front = y2;
            y2.next = n1;

            n2.front = y1;
            y1.next = n2;

        }

        Link now = head.next;
        while (now != tail) {
            System.out.print(now.id + " ");
            now = now.next;
        }
        System.out.println();
    }
}
