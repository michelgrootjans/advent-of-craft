public class AtLeastOneOfThese implements PasswordRule {
    private final String pattern;

    public AtLeastOneOfThese(String pattern) {
        this.pattern = ".*[" + pattern + "].*";
    }

    @Override
    public boolean passes(String password) {
        return password.matches(pattern);
    }
}
