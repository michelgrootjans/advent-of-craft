import java.util.ArrayList;

public interface PasswordRule {
    boolean passes(ArrayList<String> passwordLetters);
}
