import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class B {

    public static void main(String[] args) {
        int t = scanner.nextInt();
        for (int i = 0; i < t; i++) {
            process();
        }
        out.close();
    }

    private static class Monster {
        int id, x, y, s;
        List<Monster> near = new ArrayList<>();

        boolean leftContact = false;
        boolean rightContact = false;

        public Monster(int id, int x, int y, int s) {
            this.id = id;
            this.x = x;
            this.y = y;
            this.s = s;
        }

        public boolean contact(int x, int y, int r) {
            return (x - this.x) * (x - this.x) + (y - this.y) * (y - this.y) <= (s + r) * (s + r);
        }
    }

    private static Monster[] monsters;
    private static boolean[] searched;

    private static void process() {
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int k = scanner.nextInt();

        monsters = new Monster[k];
        searched = new boolean[k];

        for (int i = 0; i < k; i++) {
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            int s = scanner.nextInt();
            Monster it = new Monster(i, x, y, s);
            monsters[i] = it;
            it.leftContact = it.contact(x, m, 0) || it.contact(0, y, 0);
            it.rightContact = it.contact(x, 0, 0) || it.contact(n, y, 0);
            for (int j = 0; j < i; j++) {
                Monster it2 = monsters[j];
                if (it2.contact(x, y, s)) {
                    it2.near.add(it);
                    it.near.add(it2);
                }
            }
        }
        for (int i = 0; i < k; i++) {
            Monster it = monsters[i];
            if (searched[it.id] || !it.leftContact) {
                continue;
            }
            if (block(it, null)) {
                out.println("No");
                return;
            }
        }
        out.println("Yes");
    }

    private static boolean block(Monster it, Monster from) {
        searched[it.id] = true;
        if (it.rightContact) {
            return true;
        }
        for (Monster monster : it.near) {
            if (searched[monster.id] || monster == from) {
                continue;
            }
            if (block(monster, it)) {
                return true;
            }
        }
        return false;
    }

    private static PrintWriter out = new PrintWriter(System.out);

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
}
