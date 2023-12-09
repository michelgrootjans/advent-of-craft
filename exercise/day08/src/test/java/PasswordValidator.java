import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PasswordValidator {
    boolean validate(String password) {
        ArrayList<String> passwordLetters = toLetters(password);

        return !failsLengthRule(passwordLetters) && !failsUppercaseRule(passwordLetters) && !failsLowercaseRule(passwordLetters) && !failsNumbersRule(
            passwordLetters) && !failsSpecialCharacterRule(passwordLetters);
    }

    private boolean failsLengthRule(ArrayList<String> passwordLetters) {
        return passwordLetters.size() < 8;
    }

    private boolean failsUppercaseRule(List<String> passwordLetters) {
        List<String> uppercaseAlphabet = toLetters("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
        return passwordLetters.stream().noneMatch(uppercaseAlphabet::contains);
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
