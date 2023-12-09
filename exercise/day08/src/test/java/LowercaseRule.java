import java.util.ArrayList;
import java.util.List;

public class LowercaseRule implements PasswordRule {
    @Override
    public boolean passes(ArrayList<String> passwordLetters) {
        List<String> lowercaseAlphabet = StringSplitter.toLetters("abcdefghijklmnopqrstuvwxyz");
        return passwordLetters.stream().anyMatch(lowercaseAlphabet::contains);
    }
}
