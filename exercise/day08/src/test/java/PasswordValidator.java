import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PasswordValidator {
    boolean validate(String password) {
        ArrayList<String> passwordLetters = toLetters(password);

        return passesLengthRule(passwordLetters) && passesUppercaseRule(passwordLetters) && !failsLowercaseRule(passwordLetters) && !failsNumbersRule(
            passwordLetters) && !failsSpecialCharacterRule(passwordLetters);
    }

    private boolean passesUppercaseRule(ArrayList<String> passwordLetters) {
        List<String> uppercaseAlphabet = toLetters("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
        return passwordLetters.stream().anyMatch(uppercaseAlphabet::contains);
    }

    private boolean passesLengthRule(ArrayList<String> passwordLetters) {
        return 8 <= passwordLetters.size();
    }

    private boolean failsLowercaseRule(List<String> passwordLetters) {
        List<String> lowercaseAlphabet = toLetters("abcdefghijklmnopqrstuvwxyz");
        return passwordLetters.stream().noneMatch(lowercaseAlphabet::contains);
    }

    private boolean failsNumbersRule(List<String> passwordLetters) {
        List<String> allowedNumbers = toLetters("0123456789");
        return passwordLetters.stream().noneMatch(allowedNumbers::contains);
    }

    private boolean failsSpecialCharacterRule(List<String> passwordLetters) {
        List<String> specialCharacters = toLetters(".*#@$%&");
        return passwordLetters.stream().noneMatch(specialCharacters::contains);
    }

    private ArrayList<String> toLetters(String password) {
        return new ArrayList<>(Arrays.asList(password.split("")));
    }

}
