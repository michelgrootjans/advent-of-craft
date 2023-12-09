import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class PasswordtValidationTest {
    @Test
    void valid_password() {
        assertThat(isValid("ABCdef1*")).isTrue();
    }

    private boolean isValid(String password) {
        return true;
    }
}
