import static org.assertj.core.api.Assertions.assertThat;

import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.constraints.Chars;
import net.jqwik.api.constraints.LowerChars;
import net.jqwik.api.constraints.NotBlank;
import net.jqwik.api.constraints.NumericChars;
import net.jqwik.api.constraints.UpperChars;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class PasswordtValidationTest {
    private final String valid_password = "ABCdef1*";

    @Test
    void valid_password() {
        assertThat(isValid(valid_password)).isTrue();
    }

    @Property
    void valid_lowercase(@ForAll @LowerChars @NotBlank String lower) {
        assertThat(isValid(lower.repeat(5) + "A" + "1" + "*")).isTrue();
    }

    @Property
    void valid_uppercase(@ForAll @UpperChars @NotBlank String upper) {
        assertThat(isValid(upper.repeat(5) + "a" + "1" + "*")).isTrue();
    }

    @Property
    void valid_number(@ForAll @NumericChars @NotBlank String number) {
        assertThat(isValid(number.repeat(5) + "a" + "A" + "*")).isTrue();
    }

    @Property
    void valid_special_character(@ForAll @Chars({'.', '*', '#', '@', '$', '%', '&'}) @NotBlank String special_character) {
        assertThat(isValid(special_character.repeat(5) + "a" + "A" + "1")).isTrue();
    }

    @Test
    void less_than_8_characters() {
        assertThat(isValid(valid_password.substring(0, 7))).isFalse();
    }

    @Test
    void no_uppercase() {
        assertThat(isValid(valid_password.toLowerCase())).isFalse();
    }

    @Test
    void no_lowercase() {
        assertThat(isValid(valid_password.toUpperCase())).isFalse();
    }

    @Test
    void no_number() {
        assertThat(isValid(valid_password.replace("1", "a"))).isFalse();
    }

    @Test
    void no_special_character() {
        assertThat(isValid(valid_password.replace("*", "a"))).isFalse();
    }

    private boolean isValid(String password) {
        if (password.length() < 8) return false;
        if(failsUppercaseRule(password)) return false;
        if(failsLowercaseRule(password)) return false;
        if(failsNumbersRule(password)) return false;
        if(failsSpecialCharacterRule(password)) return false;

        return true;
    }

    private boolean failsUppercaseRule(String password) {
        List<String> passwordLetters = toLetters(password);
        List<String> uppercaseAlphabet = toLetters("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
        return passwordLetters.stream().noneMatch(uppercaseAlphabet::contains);
    }

    private boolean failsLowercaseRule(String password) {
        List<String> passwordLetters = toLetters(password);
        List<String> lowercaseAlphabet = toLetters("abcdefghijklmnopqrstuvwxyz");
        return passwordLetters.stream().noneMatch(lowercaseAlphabet::contains);
    }

    private boolean failsNumbersRule(String password) {
        List<String> passwordLetters = toLetters(password);
        List<String> allowedNumbers = toLetters("0123456789");
        return passwordLetters.stream().noneMatch(allowedNumbers::contains);
    }

    private boolean failsSpecialCharacterRule(String password) {
        List<String> passwordLetters = toLetters(password);
        List<String> specialCharacters = toLetters(".*#@$%&");
        return passwordLetters.stream().noneMatch(specialCharacters::contains);
    }

    private ArrayList<String> toLetters(String password) {
        return new ArrayList<>(Arrays.asList(password.split("")));
    }
}
