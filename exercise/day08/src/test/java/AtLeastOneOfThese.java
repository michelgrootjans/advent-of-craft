import java.util.ArrayList;
import java.util.List;

class AtLeastOneOfThese implements PasswordRule {
    private final List<String> allowedCharacters;

    public AtLeastOneOfThese(String allowedLetters) {
        allowedCharacters = StringSplitter.split(allowedLetters);
    }

    @Override
    public boolean passes(ArrayList<String> passwordLetters) {
        return passwordLetters.stream().anyMatch(allowedCharacters::contains);
    }
}
