package games;

import java.util.List;
import java.util.function.Supplier;

public class FizzBuzz {
    public static final int MIN = 0;
    public static final int MAX = 100;
    public static final int FIZZ = 3;
    public static final int BUZZ = 5;
    public static final int FIZZBUZZ = 15;

    private static final List<Foo> conditions = List.of(
        new RangeRule(),
        new FizzBuzzRule(),
        new FizzRule(),
        new Converter(BUZZ, () -> "Buzz")
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

    private interface Foo {
        boolean matches(Integer input);

        String bar();
    }

    private static class Converter implements Foo {
        private final int divisor;
        private final Supplier<String> result;

        public Converter(int divisor, Supplier<String> result) {
            this.divisor = divisor;
            this.result = result;
        }

        @Override
        public boolean matches(Integer input) {
            return is(divisor, input);
        }

        @Override
        public String bar() {
            return result.get();
        };
    }

    private static class FizzRule implements Foo {
        @Override
        public boolean matches(Integer input) {
            return is(FIZZ, input);
        }

        @Override
        public String bar() {
            return "Fizz";
        }
    }

    private static class FizzBuzzRule implements Foo {
        @Override
        public boolean matches(Integer input) {
            return is(FIZZBUZZ, input);
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
