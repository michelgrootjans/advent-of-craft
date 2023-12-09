import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PasswordValidator {

    private final List<PasswordRule> rules;

    public PasswordValidator() {
        rules = List.of(
            new AtLeastThisLong(8),
            new AtLeastOneOfThese(split("ABCDEFGHIJKLMNOPQRSTUVWXYZ")),
            new AtLeastOneOfThese(split("abcdefghijklmnopqrstuvwxyz")),
            new AtLeastOneOfThese(split("0123456789")),
            new AtLeastOneOfThese(split(".*#@$%&"))
        );
    }

    boolean validate(String password) {
        ArrayList<String> passwordLetters = split(password);
        return rules.stream().allMatch(rule -> rule.passes(passwordLetters));
    }

    private ArrayList<String> split(String letters) {
        return new ArrayList<>(Arrays.asList(letters.split("")));
    }
}
