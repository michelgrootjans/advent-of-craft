import java.util.ArrayList;

public class LengthRule implements PasswordRule {
    private int min_password_length = 8;

    public LengthRule(int min_password_length) {
        this.min_password_length = min_password_length;
    }

    public boolean passes(ArrayList<String> passwordLetters) {
        return min_password_length <= passwordLetters.size();
    }
}
