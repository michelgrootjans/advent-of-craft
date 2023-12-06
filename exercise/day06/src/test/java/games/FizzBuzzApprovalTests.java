package games;

import org.approvaltests.combinations.CombinationApprovals;
import org.approvaltests.reporters.ClipboardReporter;
import org.approvaltests.reporters.UseReporter;
import org.junit.jupiter.api.Test;

import java.util.stream.Stream;

@UseReporter(ClipboardReporter.class)
public class FizzBuzzApprovalTests {
    @Test
    void approvalTests() {
        CombinationApprovals.verifyAllCombinations(FizzBuzz::convert, range(-2, 102));
    }

    private Integer[] range(int from, int to) {
        int size = to - from + 1;
        return Stream.iterate(from, i -> i + 1).limit(size).toArray(Integer[]::new);
    }
}
