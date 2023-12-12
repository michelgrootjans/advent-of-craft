class AtLeastThisLong implements PasswordRule {
    private final int min_password_length;

    public AtLeastThisLong(int min_password_length) {
        this.min_password_length = min_password_length;
    }

    public boolean passes(String password) {
        return min_password_length <= password.length();
    }
}
