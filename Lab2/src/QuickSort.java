import java.util.Arrays;

public class QuickSort {

    public static void main(String[] args) {

        for (int i = 0; i < 10; i++) {
            int size = 20 + (int) (Math.random() * (50));
            int[] ints = new int[size];
            for (int j = 0; j < size; j++) {
                ints[j] = (int) (Math.random() * (1000000));
            }

            int[] copy = Arrays.stream(ints).toArray();
            quickSort(copy, 0, size);

            System.out.println(Arrays.equals(
                    copy,
                    Arrays.stream(ints).sorted().toArray()
            ));
        }
    }

    private static void quickSort(int[] arr, int l, int r) {
        if (l + 1 >= r) {
            return;
        }
        int pos = partition(arr, l, r);
        quickSort(arr, l, pos);
        quickSort(arr, pos + 1, r);
    }

    private static int partition(int[] arr, int l, int r) {
        int pos = l + (int) (Math.random() * (r - l));
        int pivot = arr[pos];
        arr[pos] = arr[l];
        int i = l, j = r - 1;
        while (i < j) {
            while (arr[j] >= pivot && i < j) {
                --j;
            }
            arr[i] = arr[j];
            while (arr[i] <= pivot && i < j) {
                ++i;
            }
            arr[j] = arr[i];
        }
        arr[i] = pivot;
        return i;
    }

    private static void swap(int[] arr, int i, int j) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

}
