package games;

import static org.assertj.core.api.Assertions.assertThat;

import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.constraints.IntRange;
import net.jqwik.api.constraints.Negative;

public class FizzBuzzPropertyBasedTests {
    @Property
    void multiples_of_3(@ForAll @IntRange(min = 1, max = 33) int input) {
        assertThat(FizzBuzz.convert(input * 3))
            .satisfiesExactly(s -> assertThat(s).contains("Fizz"));
    }

    @Property
    void multiples_of_5(@ForAll @IntRange(min = 1, max = 20) int input) {
        assertThat(FizzBuzz.convert(input * 5))
            .satisfiesExactly(s -> assertThat(s).contains("Buzz"));
    }

    @Property
    void multiples_of_15(@ForAll @IntRange(min = 1, max = 6) int input) {
        assertThat(FizzBuzz.convert(input * 15))
            .satisfiesExactly(s -> assertThat(s).isEqualTo("FizzBuzz"));
    }

    @Property
    void negative_numbers(@ForAll @Negative int input) {
        assertThat(FizzBuzz.convert(input).isEmpty())
            .isTrue();
    }

    @Property
    void larger_than_100(@ForAll @IntRange(min = 101) int input) {
        assertThat(FizzBuzz.convert(input).isEmpty())
            .isTrue();
    }
}
