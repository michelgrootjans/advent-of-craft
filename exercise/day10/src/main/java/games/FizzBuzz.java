package games;

import java.util.List;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class FizzBuzz {

    private static final List<FizzBuzzRule> rules = List.of(
        new MustBeBetween(0, 100),
        new IfDivisibleBy(15, () -> "FizzBuzz"),
        new IfDivisibleBy(3, () -> "Fizz"),
        new When(divisibleBy(5), () -> "Buzz")
    );

    private static Predicate<Integer> divisibleBy(int divider) {
        return blah -> blah % divider == 0;
    }

    private FizzBuzz() {
    }

    public static String convert(Integer input) {
        return rules.stream()
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

    private static class MustBeBetween implements FizzBuzzRule {
        private final int min;
        private final int max;

        public MustBeBetween(int min, int max) {
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

    private static class When implements FizzBuzzRule{
        private final Predicate<Integer> predicate;
        private final Supplier<String> result;

        public When(Predicate<Integer> predicate, Supplier<String> result) {
            this.predicate = predicate;
            this.result = result;
        }

        @Override
        public boolean matches(Integer input) {
            return predicate.test(input);
        }

        @Override
        public String execute() {
            return result.get();
        }
    }
}
