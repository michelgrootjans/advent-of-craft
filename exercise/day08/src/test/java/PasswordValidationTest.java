import static org.assertj.core.api.Assertions.assertThat;

import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.constraints.Chars;
import net.jqwik.api.constraints.LowerChars;
import net.jqwik.api.constraints.NotBlank;
import net.jqwik.api.constraints.NumericChars;
import net.jqwik.api.constraints.UpperChars;
import org.junit.jupiter.api.Test;

class PasswordValidationTest {
    private final String valid_password = "ABCdef1*";
    private final PasswordValidator validator = new PasswordValidator();

    @Test
    void valid_password() {
        assertThat(validator.validate(valid_password)).isTrue();
    }

    @Property
    void valid_lowercase(@ForAll @LowerChars @NotBlank String lower) {
        assertThat(validator.validate(lower.repeat(5) + "A" + "1" + "*")).isTrue();
    }

    @Property
    void valid_uppercase(@ForAll @UpperChars @NotBlank String upper) {
        assertThat(validator.validate(upper.repeat(5) + "a" + "1" + "*")).isTrue();
    }

    @Property
    void valid_number(@ForAll @NumericChars @NotBlank String number) {
        assertThat(validator.validate(number.repeat(5) + "a" + "A" + "*")).isTrue();
    }

    @Property
    void valid_special_character(@ForAll @Chars({'.', '*', '#', '@', '$', '%', '&'}) @NotBlank String special_character) {
        assertThat(validator.validate(special_character.repeat(5) + "a" + "A" + "1")).isTrue();
    }

    @Test
    void less_than_8_characters() {
        assertThat(validator.validate(valid_password.substring(0, 7))).isFalse();
    }

    @Test
    void no_uppercase() {
        assertThat(validator.validate(valid_password.toLowerCase())).isFalse();
    }

    @Test
    void no_lowercase() {
        assertThat(validator.validate(valid_password.toUpperCase())).isFalse();
    }

    @Test
    void no_number() {
        assertThat(validator.validate(valid_password.replace("1", "a"))).isFalse();
    }

    @Test
    void no_special_character() {
        assertThat(validator.validate(valid_password.replace("*", "a"))).isFalse();
    }
}
