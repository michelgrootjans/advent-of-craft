import java.util.ArrayList;
import java.util.List;

public class NumberRule implements PasswordRule {
    @Override
    public boolean passes(ArrayList<String> passwordLetters) {
        List<String> allowedNumbers = StringSplitter.toLetters("0123456789");
        return passwordLetters.stream().anyMatch(allowedNumbers::contains);
    }
}
