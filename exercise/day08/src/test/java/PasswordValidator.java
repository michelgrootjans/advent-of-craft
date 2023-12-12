import java.util.List;

public class PasswordValidator {

    private final List<PasswordRule> rules;

    public PasswordValidator() {
        rules = List.of(
            new AtLeastThisLong(8),
            new AtLeastOneOfThese2("ABCDEFGHIJKLMNOPQRSTUVWXYZ"),
            new AtLeastOneOfThese2("abcdefghijklmnopqrstuvwxyz"),
            new AtLeastOneOfThese2("0123456789"),
            new AtLeastOneOfThese2(".*#@$%&")
        );
    }

    boolean validate(String password) {
        List<String> passwordLetters = StringSplitter.split(password);
        return rules.stream().allMatch(rule -> rule.passes(password));
    }
}
