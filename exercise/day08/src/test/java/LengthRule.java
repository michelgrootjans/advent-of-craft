import java.util.ArrayList;

public class LengthRule {
    public boolean passes(ArrayList<String> passwordLetters) {
        return 8 <= passwordLetters.size();
    }
}
