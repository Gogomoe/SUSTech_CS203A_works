import java.util.ArrayList;

public class FindTheFirstLEZeroInDescTest {

    public static void main(String[] args) {

        for (int i = 1; i < 20; i++) {
            ArrayList<Integer> list = new ArrayList<>();
            list.add(2);
            list.add(2);
            list.add(1);

            for (int j = 0; j < i; j++) {
                list.add(0);
            }

            list.add(-1);
            list.add(-2);

            System.out.println(findTheFirstLEZeroInDesc(list.stream().mapToInt(it -> it).toArray()));
        }
    }

    private static int findTheFirstLEZeroInDesc(int[] arr) {
        int l = 0;
        int r = arr.length;
        int mid = -2;

        while (l < r) {
            mid = l + (r - l) / 2;
            if (arr[mid] > 0) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return r;
    }

    /**
     * 在升序数组中找到第一个大于等于 {@code value} 的位置 <br/>
     * 理解思路，大于等于 {@code value} 的值都是可行的，大于等于 {@code value} 时向左搜索，
     * 确保 {@code end} 始终在可行域内，{@code mid} 可行时才减小 {@code end} ，且 {@code end} 只能变为可行的 {@code mid}
     * 最后 {@code begin == end} 恒成立
     */
    public static int lowerBound(int[] nums, int begin, int end, int value) {
        while (begin < end) {
            int mid = begin + (end - begin) / 2;
            if (nums[mid] < value) {
                begin = mid + 1;
            } else {
                end = mid;
            }
        }
        return begin;
    }
}
