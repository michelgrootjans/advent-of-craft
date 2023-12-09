import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PasswordValidator {
    boolean validate(String password, PasswordtValidationTest passwordtValidationTest) {
        if (password.length() < 8) return false;
        if(failsUppercaseRule(password)) return false;
        if(failsLowercaseRule(password)) return false;
        if(failsNumbersRule(password)) return false;
        if(failsSpecialCharacterRule(password)) return false;

        return true;
    }


    private boolean failsUppercaseRule(String password) {
        List<String> passwordLetters = toLetters(password);
        List<String> uppercaseAlphabet = toLetters("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
        return passwordLetters.stream().noneMatch(uppercaseAlphabet::contains);
    }

    private boolean failsLowercaseRule(String password) {
        List<String> passwordLetters = toLetters(password);
        List<String> lowercaseAlphabet = toLetters("abcdefghijklmnopqrstuvwxyz");
        return passwordLetters.stream().noneMatch(lowercaseAlphabet::contains);
    }

    private boolean failsNumbersRule(String password) {
        List<String> passwordLetters = toLetters(password);
        List<String> allowedNumbers = toLetters("0123456789");
        return passwordLetters.stream().noneMatch(allowedNumbers::contains);
    }

    private boolean failsSpecialCharacterRule(String password) {
        List<String> passwordLetters = toLetters(password);
        List<String> specialCharacters = toLetters(".*#@$%&");
        return passwordLetters.stream().noneMatch(specialCharacters::contains);
    }

    private ArrayList<String> toLetters(String password) {
        return new ArrayList<>(Arrays.asList(password.split("")));
    }

}
