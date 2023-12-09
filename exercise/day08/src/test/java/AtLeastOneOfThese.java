import java.util.List;

class AtLeastOneOfThese implements PasswordRule {
    private final List<String> allowedCharacters;

    public AtLeastOneOfThese(String allowedLetters) {
        allowedCharacters = StringSplitter.split(allowedLetters);
    }

    @Override
    public boolean passes(List<String> passwordLetters) {
        return passwordLetters.stream().anyMatch(allowedCharacters::contains);
    }
}
