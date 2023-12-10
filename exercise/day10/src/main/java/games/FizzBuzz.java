package games;

import java.util.List;
import java.util.function.Supplier;

public class FizzBuzz {
    public static final int MIN = 0;
    public static final int MAX = 100;

    private static final List<FizzBuzzRule> conditions = List.of(
        new RangeRule(MIN, MAX),
        new IfDivisibleBy(15, () -> "FizzBuzz"),
        new IfDivisibleBy(3, () -> "Fizz"),
        new IfDivisibleBy(5, () -> "Buzz")
    );

    private FizzBuzz() {
    }

    public static String convert(Integer input) {
        return conditions.stream()
            .filter(rule -> rule.matches(input))
            .findFirst()
            .map(FizzBuzzRule::execute)
            .orElse(input.toString());
    }

    private interface FizzBuzzRule {
        boolean matches(Integer input);

        String execute();
    }

    private static class IfDivisibleBy implements FizzBuzzRule {
        private final int divisor;
        private final Supplier<String> result;

        public IfDivisibleBy(int divisor, Supplier<String> result) {
            this.divisor = divisor;
            this.result = result;
        }

        @Override
        public boolean matches(Integer input) {
            return input % divisor == 0;
        }

        @Override
        public String execute() {
            return result.get();
        }
    }

    private static class RangeRule implements FizzBuzzRule {
        private final int min;
        private final int max;

        public RangeRule(int min, int max) {
            this.min = min;
            this.max = max;
        }

        @Override
        public boolean matches(Integer input) {
            return isOutOfRange(input);
        }

        @Override
        public String execute() {
            throw new OutOfRangeException();
        }

        private boolean isOutOfRange(Integer input) {
            return input <= min || max < input;
        }
    }
}
