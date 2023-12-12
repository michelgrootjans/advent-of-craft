public class AtLeastOneOfThese2 implements PasswordRule {
    private String pattern;

    public AtLeastOneOfThese2(String pattern) {
        this.pattern = pattern;
    }

    @Override
    public boolean passes(String password) {
        return password.matches(".*[" + pattern + "].*");
    }
}
