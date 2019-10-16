import java.util.Arrays;

public class KMP {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(getNext("axyabcaxy".toCharArray())));
        System.out.println(Arrays.toString(getNext("abxabyabxabyabx".toCharArray())));
    }

    private static int[] getNext(char[] chars) {
        int[] next = new int[chars.length];
        next[0] = -1;
        for (int j = 0; j < chars.length - 1; j++) {
            int k = next[j];
            while (k != -1) {
                if (chars[k] == chars[j]) {
                    next[j + 1] = k + 1;
                    break;
                }
                k = next[k];
            }
        }
        return next;
    }

}
