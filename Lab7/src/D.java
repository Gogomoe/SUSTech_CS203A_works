import java.io.BufferedOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class D {

    private static class MinPQ {

        private long[] arr;
        private int size;
        private int maxSize;

        static MinPQ create(int size) {
            MinPQ p = new MinPQ();
            p.arr = new long[size + 1];
            p.size = 0;
            p.maxSize = size;
            return p;
        }

        private void swim(int index) {
            while (index > 1 && arr[index] < arr[index / 2]) {
                swap(index, index / 2);
                index /= 2;
            }
        }

        private void sink(int index) {
            while (index * 2 <= size) {
                int j = index * 2;
                if (j + 1 <= size && arr[j + 1] < arr[j]) {
                    j++;
                }
                if (arr[index] > arr[j]) {
                    swap(index, j);
                } else {
                    break;
                }
                index = j;
            }
        }

        private long peekMin() {
            return arr[1];
        }

        private void replaceMin(long num) {
            arr[1] = num;
            sink(1);
        }

        private void insert(long x) {
            size++;
            arr[size] = x;
            swim(size);
        }

        private void swap(int i1, int i2) {
            long temp = arr[i1];
            arr[i1] = arr[i2];
            arr[i2] = temp;
        }

    }

    private static PrintStream out = new PrintStream(new BufferedOutputStream(System.out, 32768));

    static {
//        System.setOut(new PrintStream(new BufferedOutputStream(System.out, 32768)));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        int k = scanner.nextInt();
        long last_ans = scanner.nextInt();
        MinPQ minPQ = MinPQ.create(k);
        for (int i = 1; i <= t; i++) {
            if (minPQ.size < k) {
                minPQ.insert(a(i + last_ans));
            } else {
                long min = minPQ.peekMin();
                long num = a(i + last_ans);
                if (num > min) {
                    minPQ.replaceMin(num);
                }
            }
            if (i % 100 == 0) {
                long ans = minPQ.peekMin();
                out.print(ans + " ");
                last_ans = ans;
            }
        }
        out.close();
    }

    private static long a(long n) {
        long ans = n;
        while (n != 0) {
            ans += n % 10;
            n /= 10;
        }
        return ans;
    }
}
