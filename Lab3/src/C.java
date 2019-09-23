import java.util.Scanner;

public class C {

    static class Link {
        Link next;
        Link front;
        int num;

        public Link(int num) {
            this.num = num;
        }

        @Override
        public String toString() {
            return "Link{" +
                    "num=" + num +
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
        char[] chs = scanner.next().toCharArray();

        Link head = new Link(-1);
        Link end = new Link(-2);

        head.next = end;
        end.front = head;

        Link now = head.next;

        for (int i = 0; i < n; i++) {
            char ch = chs[i];
            if (ch == 'r') {
                i++;
                if (i >= n) {
                    break;
                }
                if (now != end) {
                    now.num = chs[i] - '0';
                } else {
                    Link insert = new Link(chs[i] - '0');
                    Link last = now.front;

                    last.next = insert;
                    insert.front = last;

                    now.front = insert;
                    insert.next = now;

                    now = insert;
                }
            } else if (ch == 'I') {
                now = head.next;
            } else if (ch == 'H') {
                if (now.front != head) {
                    now = now.front;
                }
            } else if (ch == 'L') {
                if (now.next != null) {
                    now = now.next;
                }
            } else if (ch == 'x') {
                if (now != end) {
                    Link last = now.front;
                    Link next = now.next;

                    last.next = next;
                    next.front = last;

                    now = next;
                }
            } else {
                Link insert = new Link(chs[i] - '0');
                Link last = now.front;

                last.next = insert;
                insert.front = last;

                now.front = insert;
                insert.next = now;
            }

//            Link out = head.next;
//            while (out != end) {
//                if (out == now) {
//                    System.out.print("\u001b[31m");
//                }
//                System.out.print(out.num);
//                if (out == now) {
//                    System.out.print("\u001b[0m");
//                }
//                out = out.next;
//            }
//            System.out.println();

        }

        Link out = head.next;
        while (out != end) {
            System.out.print(out.num);
            out = out.next;
        }
        System.out.println();

    }
}

