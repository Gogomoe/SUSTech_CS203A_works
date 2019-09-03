import java.util.*;

public class B {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[][] arr = new int[9][];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = new int[9];
        }

        List<Integer> xs = new ArrayList<>();
        List<Integer> ys = new ArrayList<>();

        List<Integer> xs2 = new ArrayList<>();
        List<Integer> ys2 = new ArrayList<>();

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                String str = scanner.next();
                if (str.equals("|")) {
                    j--;
                } else if (str.equals("x")) {
                    xs.add(j);
                    ys.add(i);
                } else {
                    arr[i][j] = Integer.parseInt(str);
                }
            }
            scanner.next();
        }

        Set<Integer> nums = new HashSet<>();
        for (int i = 1; i <= 9; i++) {
            nums.add(i);
        }
        Set<Integer> all = new HashSet<>();
        Set<Integer> set = new HashSet<>();
        while (xs.size() != 0) {
            for (int i = 0; i < xs.size(); i++) {

                int x = xs.get(i);
                int y = ys.get(i);

                set.clear();
                for (int j = 0; j < 9; j++) {
                    set.add(arr[j][x]);
                }
                all.addAll(nums);
                all.removeAll(set);
                if (all.size() == 1) {
                    arr[y][x] = all.toArray(new Integer[0])[0];
                    continue;
                }

                set.clear();
                for (int j = 0; j < 9; j++) {
                    set.add(arr[y][j]);
                }
                all.addAll(nums);
                all.removeAll(set);
                if (all.size() == 1) {
                    arr[y][x] = all.toArray(new Integer[0])[0];
                    continue;
                }

                set.clear();
                int sx = x / 3 * 3;
                int sy = y / 3 * 3;
                for (int j = 0; j < 3; j++) {
                    for (int k = 0; k < 3; k++) {
                        set.add(arr[sy + j][sx + k]);
                    }
                }
                all.addAll(nums);
                all.removeAll(set);
                if (all.size() == 1) {
                    arr[y][x] = all.toArray(new Integer[0])[0];
                    continue;
                }

                xs2.add(x);
                ys2.add(y);

            }
            if (xs2.size() == xs.size()) {
                System.out.println("The test data is incorrect!");
                return;
            }
            xs.clear();
            ys.clear();
            xs.addAll(xs2);
            ys.addAll(ys2);
            xs2.clear();
            ys2.clear();
        }


        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j]);
                System.out.print(" ");
                if (j % 3 == 2) {
                    System.out.print("| ");
                }
            }
            System.out.println();
            if (i % 3 == 2) {
                System.out.println();
            }
        }

    }
}
