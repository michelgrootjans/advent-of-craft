import java.util.List;

public class PasswordValidator {

    private final List<PasswordRule> rules;

    public PasswordValidator() {
        rules = List.of(
            new AtLeastThisLong(8),
            new AtLeastOneOfThese("ABCDEFGHIJKLMNOPQRSTUVWXYZ"),
            new AtLeastOneOfThese("abcdefghijklmnopqrstuvwxyz"),
            new AtLeastOneOfThese("0123456789"),
            new AtLeastOneOfThese(".*#@$%&")
        );
    }

    boolean validate(String password) {
        return rules.stream().allMatch(rule -> rule.passes(password));
    }
}
