import java.util.Scanner;

public class C {
    private static class MinPQ {

        private long[] arr;
        private int size;

        static MinPQ fromArray(long[] array) {
            MinPQ p = new MinPQ();
            p.arr = new long[array.length + 1];
            p.size = array.length;
            System.arraycopy(array, 0, p.arr, 1, array.length);
            for (int i = p.size / 2; i >= 1; i--) {
                p.sink(i);
            }
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

        private long deleteMin() {
            long min = arr[1];
            swap(1, size);
            size--;
            sink(1);
            return min;
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

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        for (int i = 0; i < t; i++) {
            process(scanner);
        }
    }

    private static void process(Scanner scanner) {
        int n = scanner.nextInt();
        long[] arr = new long[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }
        MinPQ p = MinPQ.fromArray(arr);

        long sum = 0;
        for (int i = 0; i < n - 1; i++) {
            long s = p.deleteMin() + p.deleteMin();
            sum += s;
            p.insert(s);
        }

        System.out.println(sum);
    }
}