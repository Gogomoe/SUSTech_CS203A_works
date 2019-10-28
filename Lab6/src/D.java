import java.util.Scanner;

public class D {
    private static class MinPQ {

        private int[] arr;
        private int size;

        static MinPQ fromArray(int[] array) {
            MinPQ p = new MinPQ();
            p.arr = new int[array.length + 1];
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

        private int deleteMin() {
            int min = arr[1];
            swap(1, size);
            size--;
            sink(1);
            return min;
        }

        private void insert(int x) {
            size++;
            arr[size] = x;
            swim(size);
        }

        private void swap(int i1, int i2) {
            int temp = arr[i1];
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
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }
        MinPQ p = MinPQ.fromArray(arr);

        int sum = 0;
        for (int i = 0; i < n - 1; i++) {
            int s = p.deleteMin() + p.deleteMin();
            sum += s;
            p.insert(s);
        }

        System.out.println(sum);
    }
}