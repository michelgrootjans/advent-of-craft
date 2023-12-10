package games;

import java.util.List;

public class FizzBuzz {
    public static final int MIN = 0;
    public static final int MAX = 100;
    public static final int FIZZ = 3;
    public static final int BUZZ = 5;
    public static final int FIZZBUZZ = 15;
    private static List<Foo> conditions = List.of(
        new RangeRule(),
        new FizzBuzzRule(),
        new FizzRule(),
        new BuzzRule()
    );

    private FizzBuzz() {
    }

    public static String convert(Integer input) {

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
            return input % BUZZ == 0;
        }

        @Override
        public String bar() {
            return "Buzz";
        }
    }

    private static class FizzRule implements Foo {
        @Override
        public boolean matches(Integer input) {
            return input % FIZZ == 0;
        }

        @Override
        public String bar() {
            return "Fizz";
        }
    }

    private static class FizzBuzzRule implements Foo {
        @Override
        public boolean matches(Integer input) {
            return input % FIZZBUZZ == 0;
        }

        @Override
        public String bar() {
            return "FizzBuzz";
        }
    }

    private static class RangeRule implements Foo {
        @Override
        public boolean matches(Integer input) {
            return isOutOfRange(input);
        }

        @Override
        public String bar() {
            throw new OutOfRangeException();
        }

        private static boolean isOutOfRange(Integer input) {
            return input <= MIN || MAX < input;
        }
    }
}
