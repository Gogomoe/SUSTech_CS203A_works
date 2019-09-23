import java.util.Scanner;

public class A {

    static class Link {
        Link next;
        int exponents;
        long coefficients;

        public Link(int exponents, long coefficients) {
            this.exponents = exponents;
            this.coefficients = coefficients;
        }
    }

    static Scanner scanner;

    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        for (int i = 0; i < t; i++) {
            Link head = new Link(-1, -1);
            buildChain(head);
            insertChain(head);

            int q = scanner.nextInt();
            Link last = head;
            for (int j = 0; j < q; j++) {
                int query = scanner.nextInt();
                last = query(last, query);
            }
            System.out.println();
        }

    }

    private static Link query(Link last, int query) {
        Link now = last.next;
        while (now != null) {
            if (now.exponents == query) {
                System.out.print(now.coefficients + " ");
                return now;
            }
            if (now.exponents > query) {
                System.out.print("0 ");
                return last;
            }
            last = now;
            now = now.next;
        }
        System.out.print("0 ");
        return last;
    }

    private static void buildChain(Link head) {
        int n = scanner.nextInt();
        Link last = head;
        for (int j = 0; j < n; j++) {
            int coefficients = scanner.nextInt();
            int exponents = scanner.nextInt();
            Link now = new Link(exponents, coefficients);
            last.next = now;
            last = now;
        }
    }

    private static void insertChain(Link head) {
        int m = scanner.nextInt();
        Link last = head;
        for (int i = 0; i < m; i++) {
            int coefficients = scanner.nextInt();
            int exponents = scanner.nextInt();
            Link toInsert = new Link(exponents, coefficients);
            last = insert(last, toInsert);
        }
    }

    private static Link insert(Link last, Link toInsert) {
        Link now = last.next;
        while (now != null) {
            if (now.exponents == toInsert.exponents) {
                now.coefficients += toInsert.coefficients;
                return now;
            }
            if (now.exponents > toInsert.exponents) {
                last.next = toInsert;
                toInsert.next = now;
                return toInsert;
            }
            last = now;
            now = now.next;
        }
        last.next = toInsert;
        return toInsert;
    }

}
