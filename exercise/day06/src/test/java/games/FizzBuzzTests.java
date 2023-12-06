package games;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.stream.Stream;

class FizzBuzzTests {
    private static Stream<Arguments> valid_cases() {
        return Stream.of(
            Arguments.of(1, "1"),
            Arguments.of(2, "2"),
            Arguments.of(3, "Fizz"),
            Arguments.of(4, "4"),
            Arguments.of(5, "Buzz"),
            Arguments.of(6, "Fizz"),
            Arguments.of(7, "7"),
            Arguments.of(8, "8"),
            Arguments.of(9, "Fizz"),
            Arguments.of(10, "Buzz"),
            // ...
            Arguments.of(100, "Buzz"),

            Arguments.of(3 * 1, "Fizz"),
            Arguments.of(3 * 2, "Fizz"),
            Arguments.of(3 * 3, "Fizz"),
            Arguments.of(3 * 4, "Fizz"),
            Arguments.of(3 * 5, "FizzBuzz"),
            Arguments.of(3 * 6, "Fizz"),

            Arguments.of(5 * 1, "Buzz"),
            Arguments.of(5 * 2, "Buzz"),
            Arguments.of(5 * 3, "FizzBuzz"),
            Arguments.of(5 * 4, "Buzz"),
            Arguments.of(5 * 5, "Buzz"),

            Arguments.of(15 * 1, "FizzBuzz"),
            Arguments.of(15 * 2, "FizzBuzz"),
            Arguments.of(15 * 3, "FizzBuzz"),
            Arguments.of(15 * 4, "FizzBuzz"),
            Arguments.of(15 * 5, "FizzBuzz")
        );
    }

    private static Stream<Arguments> out_of_range_cases() {
        return Stream.of(
            Arguments.of(-1),
            Arguments.of(0),
            Arguments.of(101),
            Arguments.of(102)
        );
    }

    @ParameterizedTest(name="{0} => {1}")
    @MethodSource
    void valid_cases(Integer input, String expectedOutput) throws OutOfRangeException {
        assertThat(FizzBuzz.convert(input))
            .isEqualTo(expectedOutput);
    }

    @ParameterizedTest(name="{0} is out of range")
    @MethodSource
    void out_of_range_cases(Integer input) {
        assertThatThrownBy(() -> FizzBuzz.convert(input))
            .isInstanceOf(OutOfRangeException.class);
    }
}