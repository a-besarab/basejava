import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class MainStream {
    public static void main(String[] args) {
        System.out.println(minValue(new int[]{1, 2, 3, 3, 2, 9, 8, 6, 2}));
        List<Integer> list = Arrays.asList(2, 3, 2, 3);
        System.out.println(oddOrEven(list));
    }

    private static OptionalInt minValue(int[] values) {
        return Arrays.stream(values)
                .distinct()
                .sorted()
                .reduce((p1, p2) -> p1 * 10 + p2);
    }

    private static List<Integer> oddOrEven(List<Integer> integers) {

//        Map<Boolean, List<Integer>> partition = integers.stream()
//                .collect(Collectors.partitioningBy(p -> p % 2 != 0));
//        return partition.get(integers.stream().mapToInt(element -> element).sum() % 2 == 0);

        AtomicInteger sum = new AtomicInteger(0);
        Map<Boolean, List<Integer>> partition = integers.stream().peek(p -> sum.set(sum.get() + p))
                .collect(Collectors.partitioningBy(p -> p % 2 != 0));
        return partition.get((sum.get() % 2) == 0);
    }
}