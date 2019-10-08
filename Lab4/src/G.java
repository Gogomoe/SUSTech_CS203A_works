import java.io.*;
import java.util.StringTokenizer;

public class G {

    private static QuickReader scanner = new QuickReader(System.in);
    private static PrintStream out = new PrintStream(new BufferedOutputStream(System.out, 1024 * 1024));

    public static void main(String[] args) {
        int t = scanner.nextInt();
        for (int i = 0; i < t; i++) {
            process();
        }
        out.close();
    }

    private static Queue queue;

    private static void process() {
        String exp = scanner.next();
        queue = new Queue(exp.length());
        for (char c : exp.toCharArray()) {
            queue.addLast(c);
        }
        System.out.println(exp());
    }

    private static int exp() {
        int left = factor();
        char next = nextOperator();
        while (next != '\0') {
            left = doShift(left, next);
            next = nextOperator();
        }
        return left;
    }

    private static int doShift(int left, char operation) {
        char op = queue.dequeue();
        int right = factor();
        char next = nextOperator();
        while (next != '\0' && rightIsExpr(operation, next)) {
            right = doShift(right, next);
            next = nextOperator();
        }
        return binaryCalc(left, right, op);
    }

    private static int binaryCalc(int left, int right, char op) {
        switch (op) {
            case '*':
                return left * right;
            case '+':
                return left + right;
            case '-':
                return left - right;
            case '&':
                return left & right;
            case '^':
                return left ^ right;
            case '|':
                return left | right;
            default:
                throw new IllegalStateException("calc error: " + op);
        }
    }


    private static boolean rightIsExpr(char operation, char next) {
        return priority(operation) < priority(next);
    }

    private static int priority(char operation) {
        switch (operation) {
            case '*':
                return 10;
            case '+':
            case '-':
                return 9;
            case '&':
                return 8;
            case '^':
                return 7;
            case '|':
                return 6;
            default:
                throw new IllegalStateException("priority error: " + operation);
        }
    }

    private static char nextOperator() {
        if (queue.isEmpty()) {
            return '\0';
        }
        char next = queue.first();
        if (next == '*' || next == '+' || next == '-' || next == '&' || next == '^' || next == '|') {
            return next;
        }
        return '\0';
    }

    private static int factor() {
        char first = queue.dequeue();
        if (first >= '0' && first <= '9') {
            return first - '0';
        } else if (first == '+') {
            return factor();
        } else if (first == '-') {
            return -factor();
        } else if (first == '~') {
            return ~factor();
        } else if (first == '(') {
            int res = exp();
            queue.dequeue(); // eat )
            return res;
        } else {
            throw new IllegalStateException("factor error");
        }
    }

    private static class Queue {

        public Queue(int size) {
            maxSize = size;
            ints = new char[size];
        }

        char[] ints;
        int maxSize;
        int front = 0;
        int tail = 0;

        public void addLast(char x) {
            ints[tail] = x;
            tail = (tail + 1) % maxSize;
        }

        public char dequeue() {
            char res = first();
            removeFirst();
            return res;
        }

        public void removeFirst() {
            front = (front + 1) % maxSize;
        }

        public char first() {
            return ints[front];
        }

        public boolean isEmpty() {
            return front == tail;
        }

        public boolean isNotEmpty() {
            return !isEmpty();
        }

    }


    private static class QuickReader {
        public BufferedReader reader;
        public StringTokenizer tokenizer;

        public QuickReader(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream), 1024 * 1024);
            tokenizer = null;
        }

        public String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

        public long nextLong() {
            return Long.parseLong(next());
        }

        public double nextDouble() {
            return Double.parseDouble(next());
        }

    }
}
