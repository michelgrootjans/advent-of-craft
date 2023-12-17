package blog;

import java.util.List;
import java.util.stream.Stream;

public class ListHelper {
    static <T> List<T> append(List<T> list, T comment) {
        return Stream.concat(list.stream(), Stream.of(comment)).toList();
    }
}
