import java.util.ArrayList;
import java.util.List;

public class AtLeastOneOfThese implements PasswordRule {
    private final List<String> allowedCharacters;

    public AtLeastOneOfThese(ArrayList<String> allowedLetters) {
        allowedCharacters = allowedLetters;
    }

    @Override
    public boolean passes(ArrayList<String> passwordLetters) {
        return passwordLetters.stream().anyMatch(allowedCharacters::contains);
    }
}
