package games;

import static io.vavr.control.Option.none;
import static io.vavr.control.Option.some;

import io.vavr.control.Option;

import java.util.LinkedHashMap;
import java.util.Map;

public class FizzBuzz {
    public static final int MIN = 0;
    public static final int MAX = 100;
    public static final int FIZZ = 3;
    public static final int BUZZ = 5;
    public static final int FIZZBUZZ = 15;

    public static Option<String> convert(int input) {
        return isOutOfRange(input)
            ? none()
            : some(convertSafely(input));
    }

    private static final Map<Integer, String> mapping;

    static {
        mapping = new LinkedHashMap<>();
        mapping.put(FIZZBUZZ, "FizzBuzz");
        mapping.put(FIZZ, "Fizz");
        mapping.put(BUZZ, "Buzz");
    }

    private static String convertSafely(Integer input) {
        return mapping.entrySet()
            .stream()
            .filter(f -> is(f.getKey(), input))
            .findFirst()
            .map(Map.Entry::getValue)
            .orElseGet(input::toString);
    }

    private static boolean is(Integer divisor, Integer input) {
        return input % divisor == 0;
    }

    private static boolean isOutOfRange(Integer input) {
        return input <= MIN || MAX < input;
    }
}
