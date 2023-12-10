package games;

import java.util.List;

public class FizzBuzz {
    public static final int MIN = 0;
    public static final int MAX = 100;
    public static final int FIZZ = 3;
    public static final int BUZZ = 5;
    public static final int FIZZBUZZ = 15;

    private FizzBuzz() {
    }

    public static String convert(Integer input) {
        if (isOutOfRange(input)) {
            throw new OutOfRangeException();
        }
        return convertSafely(input);
    }

    private static String convertSafely(Integer input) {
        if (is(FIZZBUZZ, input)) {
            return "FizzBuzz";
        }
        if (is(FIZZ, input)) {
            return "Fizz";
        }

        List<Foo> conditions = List.of(
            new BuzzRule()
        );

        return conditions.stream()
            .filter(foo -> foo.matches(input))
            .findFirst()
            .map(Foo::bar).orElse(input.toString());
    }

    private static boolean is(Integer divisor, Integer input) {
        return input % divisor == 0;
    }

    private static boolean isOutOfRange(Integer input) {
        return input <= MIN || input > MAX;
    }

    private interface Foo {
        boolean matches(Integer input);

        String bar();
    }

    private static class BuzzRule implements Foo {
        @Override
        public boolean matches(Integer input) {
            return input % 5 == 0;
        }

        @Override
        public String bar() {
            return "Buzz";
        }
    }
}
