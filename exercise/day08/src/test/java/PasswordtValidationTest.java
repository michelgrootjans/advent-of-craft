import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class PasswordtValidationTest {
    @Test
    void valid_password() {
        assertThat(isValid("ABCdef1*")).isTrue();
    }

    @Test
    void less_than_8_characters() {
        assertThat(isValid("ABCde1*")).isFalse();
    }

    @Test
    void no_uppercase() {
        assertThat(isValid("abcdef1*")).isFalse();
    }

    @Test
    void no_lowercase() {
        assertThat(isValid("ABCDEF1*")).isFalse();
    }

    private boolean isValid(String password) {
        if (password.length() < 8) return false;
        if(failsUppercaseRule(password)) return false;
        if(failsLowercaseRule(password)) return false;

        return true;
    }

    private boolean failsLowercaseRule(String password) {
        List<String> passwordLetters = toLetters(password);
        List<String> lowercaseAlphabet = toLetters("abcdefghijklmnopqrstuvwxyz");
        return passwordLetters.stream().noneMatch(lowercaseAlphabet::contains);
    }

    private boolean failsUppercaseRule(String password) {
        List<String> passwordLetters = toLetters(password);
        List<String> uppercaseAlphabet = toLetters("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
        return passwordLetters.stream().noneMatch(uppercaseAlphabet::contains);
    }

    private ArrayList<String> toLetters(String password) {
        return new ArrayList<>(Arrays.asList(password.split("")));
    }
}
