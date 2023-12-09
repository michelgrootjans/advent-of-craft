import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StringSplitter {
    static List<String> split(String letters) {
        return new ArrayList<>(Arrays.asList(letters.split("")));
    }
}
