import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PasswordValidator {

    private final List<PasswordRule> rules;

    public PasswordValidator() {
        rules = List.of(new LengthRule());
    }

    boolean validate(String password) {
        ArrayList<String> passwordLetters = toLetters(password);

        return rules.stream().allMatch(rule -> rule.passes(passwordLetters))
            && passesUppercaseRule(passwordLetters)
            && passesLowercaseRule(passwordLetters)
            && passesNumberRule(passwordLetters)
            && passesSpecialCharacterRule(passwordLetters);
    }

    private boolean passesSpecialCharacterRule(ArrayList<String> passwordLetters) {
        List<String> specialCharacters = toLetters(".*#@$%&");
        return passwordLetters.stream().anyMatch(specialCharacters::contains);
    }

    private boolean passesNumberRule(ArrayList<String> passwordLetters) {
        return !failsNumbersRule(
            passwordLetters);
    }

    private boolean passesLowercaseRule(ArrayList<String> passwordLetters) {
        List<String> lowercaseAlphabet = toLetters("abcdefghijklmnopqrstuvwxyz");
        return passwordLetters.stream().anyMatch(lowercaseAlphabet::contains);
    }

    private boolean passesUppercaseRule(ArrayList<String> passwordLetters) {
        List<String> uppercaseAlphabet = toLetters("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
        return passwordLetters.stream().anyMatch(uppercaseAlphabet::contains);
    }

    private boolean failsNumbersRule(List<String> passwordLetters) {
        List<String> allowedNumbers = toLetters("0123456789");
        return passwordLetters.stream().noneMatch(allowedNumbers::contains);
    }

    private ArrayList<String> toLetters(String password) {
        return new ArrayList<>(Arrays.asList(password.split("")));
    }

}
