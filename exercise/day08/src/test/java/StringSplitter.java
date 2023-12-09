import java.util.ArrayList;
import java.util.Arrays;

public class StringSplitter {
    static ArrayList<String> toLetters(String password) {
        return new ArrayList<>(Arrays.asList(password.split("")));
    }
}
