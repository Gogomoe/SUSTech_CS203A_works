import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B {

    private static QuickReader scanner = new QuickReader(System.in);

    private static class QuickReader {
        public BufferedReader reader;
        public StringTokenizer tokenizer;

        public QuickReader(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream), 32768);
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

    public static void main(String[] args) {
        int n = scanner.nextInt();
        Stack stack = new Stack();
        cases:
        for (int i = 0; i < n; i++) {
            stack.clear();
            scanner.nextInt();
            char[] chars = scanner.next().toCharArray();
            for (char c : chars) {
                if (c == '(' || c == '[' || c == '{') {
                    stack.push(c);
                } else {
                    if (stack.isEmpty()) {
                        System.out.println("NO");
                        continue cases;
                    }
                    char last = stack.pop();
                    if ((last == '(' && c != ')') || (last == '[' && c != ']') || (last == '{' && c != '}')) {
                        System.out.println("NO");
                        continue cases;
                    }
                }
            }
            if (stack.isEmpty()) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
    }

    private static class Stack {
        char[] chars = new char[30000];
        int pos = -1;

        void push(char c) {
            pos++;
            chars[pos] = c;
        }

        char pop() {
            char c = chars[pos];
            pos--;
            return c;
        }

        void clear() {
            pos = -1;
        }

        boolean isEmpty() {
            return pos == -1;
        }
    }
}
