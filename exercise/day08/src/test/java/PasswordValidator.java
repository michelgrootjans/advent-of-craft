import java.util.ArrayList;
import java.util.List;

public class PasswordValidator {

    private final List<PasswordRule> rules;

    public PasswordValidator() {
        rules = List.of(
            new LengthRule(),
            new UppercaseRule(),
            new LowercaseRule()
        );
    }

    boolean validate(String password) {
        ArrayList<String> passwordLetters = StringSplitter.toLetters(password);

        return rules.stream().allMatch(rule -> rule.passes(passwordLetters))
            && passesNumberRule(passwordLetters)
            && passesSpecialCharacterRule(passwordLetters);
    }

    private boolean passesSpecialCharacterRule(ArrayList<String> passwordLetters) {
        List<String> specialCharacters = StringSplitter.toLetters(".*#@$%&");
        return passwordLetters.stream().anyMatch(specialCharacters::contains);
    }

    private boolean passesNumberRule(ArrayList<String> passwordLetters) {
        return !failsNumbersRule(
            passwordLetters);
    }

    private boolean failsNumbersRule(List<String> passwordLetters) {
        List<String> allowedNumbers = StringSplitter.toLetters("0123456789");
        return passwordLetters.stream().noneMatch(allowedNumbers::contains);
    }
}
