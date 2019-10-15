public class Hash {

    public static void main(String[] args) {
        System.out.println(hash("world".toCharArray(), 0, 5));
        char[] chars = "this is beautiful world".toCharArray();
        long hash = hash(chars, 0, 5);
        for (int i = 5; i < chars.length; i++) {
            hash = hashFrom(hash, chars[i - 5], chars[i], 5);
        }
        System.out.println(hash);
    }

    private static final int d = 193;
    private static final int p = 1610612741;

    private static long hash(char[] chars, int start, int end) {
        long hash = 0;
        for (int i = start; i < end; i++) {
            hash = (hash * d + chars[i]) % p;
        }
        return hash;
    }

    private static final long[] dpow = new long[1000];

    static {
        dpow[0] = 1;
        for (int i = 0; i < dpow.length - 1; i++) {
            dpow[i + 1] = (dpow[i] * d) % p;
        }
    }

    private static long hashFrom(long last, char remove, char it, int length) {
        return (((last - remove * dpow[length - 1]) * d + it) % p + p) % p;
    }
}
