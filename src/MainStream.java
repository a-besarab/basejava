import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class MainStream {
    public static void main(String[] args) {
        System.out.println(minValue(new int[]{1, 2, 3, 3, 2, 9, 8, 6, 2}));
        List<Integer> list = Arrays.asList(1, 2, 3, 3, 2, 9, 8, 6, 2);
        System.out.println(oddOrEven(list));
    }

    private static int minValue(int[] values) {
        AtomicInteger minValue = new AtomicInteger();
        Arrays.stream(values)
                .distinct()
                .sorted()
                .forEach((p) -> minValue.set(p + minValue.get() * 10));
        return minValue.get();
    }

    private static List<Integer> oddOrEven(List<Integer> integers) {
        List<Integer> list = new ArrayList<>();
        int sum = integers.stream().mapToInt(element -> element).sum();
        if (sum % 2 != 0) {
            integers.stream()
                    .filter(p -> p % 2 == 0)
                    .forEach(list::add);
            return list;
        } else {
            integers.stream()
                    .filter(p -> p % 2 != 0)
                    .forEach(list::add);
            return list;
        }
    }
}