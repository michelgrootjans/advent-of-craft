import java.util.ArrayList;
import java.util.List;

public class SpecialCharacterRule implements PasswordRule {
    @Override
    public boolean passes(ArrayList<String> passwordLetters) {
        List<String> specialCharacters = StringSplitter.toLetters(".*#@$%&");
        return passwordLetters.stream().anyMatch(specialCharacters::contains);
    }
}
