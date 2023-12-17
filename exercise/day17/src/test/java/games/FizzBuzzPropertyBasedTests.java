package games;

import static java.util.function.Predicate.not;
import static org.assertj.core.api.Assertions.assertThat;

import net.jqwik.api.Arbitraries;
import net.jqwik.api.Arbitrary;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.Provide;
import net.jqwik.api.constraints.IntRange;
import net.jqwik.api.constraints.Negative;

import java.util.function.Predicate;

public class FizzBuzzPropertyBasedTests {
    @Property
    void normal_numbers(@ForAll("normal_numbers") @IntRange(min = 1, max = 100) int input) {
        assertThat(FizzBuzz.convert(input).get()).isEqualTo("" + input);
    }

    @Property
    void multiples_of_3(@ForAll("multiples_of_3") @IntRange(min = 1, max = 100) int input) {
        assertThat(FizzBuzz.convert(input).get()).isEqualTo("Fizz");
    }

    @Property
    void multiples_of_5(@ForAll("multiples_of_5") @IntRange(min = 1, max = 100) int input) {
        assertThat(FizzBuzz.convert(input).get()).isEqualTo("Buzz");
    }

    @Property
    void multiples_of_15(@ForAll("multiples_of_15") @IntRange(min = 1, max = 100) int input) {
        assertThat(FizzBuzz.convert(input).get()).isEqualTo("FizzBuzz");
    }

    @Property
    void negative_numbers(@ForAll @Negative int input) {
        assertThat(FizzBuzz.convert(input).isEmpty()).isTrue();
    }

    @Property
    void larger_than_100(@ForAll @IntRange(min = 101) int input) {
        assertThat(FizzBuzz.convert(input).isEmpty()).isTrue();
    }

    @Provide
    Arbitrary<Integer> normal_numbers() {
        return integers(not(multiple_of(3)), not(multiple_of(5)));
    }

    @Provide
    Arbitrary<Integer> multiples_of_3() {
        return integers(multiple_of(3), not(multiple_of(5)));
    }

    @Provide
    Arbitrary<Integer> multiples_of_5() {
        return integers(multiple_of(5), not(multiple_of(3)));
    }

    @Provide
    Arbitrary<Integer> multiples_of_15() {
        return integers(multiple_of(15));
    }

    private Arbitrary<Integer> integers(Predicate<Integer> predicate) {
        return Arbitraries.integers().filter(predicate);
    }

    private Arbitrary<Integer> integers(Predicate<Integer> predicate1, Predicate<Integer> predicate2) {
        return Arbitraries.integers()
            .filter(predicate1)
            .filter(predicate2);
    }

    private Predicate<Integer> multiple_of(int divisor) {
        return n -> n % divisor == 0;
    }
}
