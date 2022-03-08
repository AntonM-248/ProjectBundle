import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
    }

    public boolean canConstruct(String s, int k) {
        List<Character> chars = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            chars.add(s.charAt(i));
        }
        Map<Character, Long> quantities = chars.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        List<Integer> oddEvens = new ArrayList<>(Arrays.asList(0, 0));

        quantities.entrySet().forEach(e -> {
            if (e.getValue() % 2 != 0) {
                e.setValue(e.getValue() - 1);
                oddEvens.set(0, oddEvens.get(0) + 1);
            }
            oddEvens.set(1, (int) (oddEvens.get(1) + e.getValue()));
        });

        if (oddEvens.get(0) > k) {
            return false;
        } else if (oddEvens.get(0) + oddEvens.get(1) >= k) {
            return true;
        }
        return false;
    }
}
