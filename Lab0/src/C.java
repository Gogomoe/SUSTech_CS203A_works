import java.util.*;

public class C {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();

        Comparator<String> comp = new Comparator<String>() {

            Map<String, Integer> map = new HashMap<>();
            Map<Character, Integer> color = new HashMap<>();

            {
                map.put("Z", 0);
                map.put("F", 1);
                map.put("B", 2);
                map.put("N", 3);
                map.put("W", 4);
                map.put("S", 5);
                map.put("E", 6);

                color.put('Y', 10);
                color.put('T', 30);
                color.put('W', 50);
            }

            @Override
            public int compare(String o1, String o2) {
                return toNum(o1) - toNum(o2);
            }

            private int toNum(String s) {
                if (map.containsKey(s)) {
                    return map.get(s);
                }
                return color.get(s.charAt(0)) + 10 - Integer.parseInt(s.substring(1));
            }
        }.reversed();

        for (int i = 0; i < n; i++) {
            String[] line = scanner.nextLine().split(" ");
            Arrays.sort(line, comp);
            System.out.println(String.join(" ", line));
        }


    }
}
