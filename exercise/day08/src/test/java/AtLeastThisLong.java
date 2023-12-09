import java.util.List;

class AtLeastThisLong implements PasswordRule {
    private final int min_password_length;

    public AtLeastThisLong(int min_password_length) {
        this.min_password_length = min_password_length;
    }

    public boolean passes(List<String> passwordLetters) {
        return min_password_length <= passwordLetters.size();
    }
}
