import java.util.ArrayList;

public class LengthRule implements PasswordRule {
    public boolean passes(ArrayList<String> passwordLetters) {
        return 8 <= passwordLetters.size();
    }
}
