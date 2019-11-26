import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class E {

    public static void main(String[] args) {
        int t = scanner.nextInt();
        for (int i = 0; i < t; i++) {
            process();
        }
        out.close();
    }

    private static Comparator<Integer> max_comparator = new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            return -(o1 - o2);
        }
    };

    private static PriorityQueue<Integer> pq = new PriorityQueue<>(max_comparator);

    private static int[] income;
    private static int[] outcome;
    private static ArrayList<Integer>[] edge;

    private static void process() {
        int nodes = scanner.nextInt();
        int edges = scanner.nextInt();
        edge = new ArrayList[nodes + 1];
        for (int i = 0; i < edge.length; i++) {
            edge[i] = new ArrayList<>();
        }
        income = new int[nodes + 1];
        outcome = new int[nodes + 1];

        for (int i = 0; i < edges; i++) {
            int to = scanner.nextInt();
            int from = scanner.nextInt();
            addEdge(from, to);
        }

        ArrayList<Integer> result = new ArrayList<>(nodes);
        for (int i = 1; i <= nodes; i++) {
            if (income[i] == 0) {
                pq.add(i);
            }
        }
        while (!pq.isEmpty()) {
            int it = pq.poll();
            result.add(it);
            for (Integer to : edge[it]) {
                removeEdge(it, to);
                if (income[to] == 0) {
                    pq.add(to);
                }
            }
            edge[it].clear();
        }

        for (int i = result.size() - 1; i >= 0; i--) {
            out.print(result.get(i) + " ");
        }
        out.println();
    }

    private static void addEdge(int from, int to) {
        outcome[from]++;
        income[to]++;
        edge[from].add(to);
    }

    private static void removeEdge(int from, int to) {
        outcome[from]--;
        income[to]--;
//        edge[from].remove((Integer) to);
    }


    private static PrintWriter out = new PrintWriter(System.out);

    private static QuickReader scanner = new QuickReader(System.in);

    private static class QuickReader {
        BufferedReader reader;
        StringTokenizer tokenizer;

        QuickReader(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream), 32768);
            tokenizer = null;
        }

        String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }
    }
}
