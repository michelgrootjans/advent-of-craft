import java.util.ArrayList;
import java.util.Arrays;

public class StringSplitter {
    static ArrayList<String> split(String letters) {
        return new ArrayList<>(Arrays.asList(letters.split("")));
    }
}
