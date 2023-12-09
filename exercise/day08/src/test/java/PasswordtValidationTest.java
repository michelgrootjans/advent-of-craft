import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class PasswordtValidationTest {
    @Test
    void valid_password() {
        assertThat(isValid("ABCdef1*")).isTrue();
    }

    @Test
    void less_than_8_characters() {
        assertThat(isValid("ABCde1*")).isFalse();
    }

    private boolean isValid(String password) {
        if (password.length() < 8) return false;

        return true;
    }
}
