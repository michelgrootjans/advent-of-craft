import java.util.List;

public interface PasswordRule {
    boolean passes(List<String> passwordLetters);
}
