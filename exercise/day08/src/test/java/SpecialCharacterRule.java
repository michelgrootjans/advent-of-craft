import java.util.ArrayList;
import java.util.List;

class SpecialCharacterRule implements PasswordRule {
    private static final List<String> validCharacters = StringSplitter.toLetters(".*#@$%&");

    @Override
    public boolean passes(ArrayList<String> passwordLetters) {
        return passwordLetters.stream().anyMatch(validCharacters::contains);
    }
}
