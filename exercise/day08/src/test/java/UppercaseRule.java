import java.util.ArrayList;
import java.util.List;

public class UppercaseRule implements PasswordRule {
    @Override
    public boolean passes(ArrayList<String> passwordLetters) {
        List<String> uppercaseAlphabet = StringSplitter.toLetters("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
        return passwordLetters.stream().anyMatch(uppercaseAlphabet::contains);
    }
}
