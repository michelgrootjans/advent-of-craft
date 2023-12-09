import java.util.ArrayList;
import java.util.List;

public class UppercaseRule implements PasswordRule {
    private final List<String> allowedCharacters;

    public UppercaseRule(ArrayList<String> allowedLetters) {
        allowedCharacters = allowedLetters;
    }

    @Override
    public boolean passes(ArrayList<String> passwordLetters) {
        return passwordLetters.stream().anyMatch(allowedCharacters::contains);
    }
}
