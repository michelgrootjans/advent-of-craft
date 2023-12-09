import java.util.ArrayList;
import java.util.List;

public class PasswordValidator {

    private final List<PasswordRule> rules;

    public PasswordValidator() {
        rules = List.of(
            new LengthRule(8),
            new UppercaseRule(),
            new LowercaseRule(),
            new NumberRule(),
            new SpecialCharacterRule()
        );
    }

    boolean validate(String password) {
        ArrayList<String> passwordLetters = StringSplitter.toLetters(password);
        return rules.stream().allMatch(rule -> rule.passes(passwordLetters));
    }
}
