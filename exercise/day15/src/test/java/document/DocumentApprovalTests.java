package document;

import static org.approvaltests.combinations.CombinationApprovals.verifyAllCombinations;

import org.approvaltests.reporters.ClipboardReporter;
import org.approvaltests.reporters.Junit5Reporter;
import org.approvaltests.reporters.UseReporter;
import org.junit.jupiter.api.Test;

@UseReporter({ClipboardReporter.class, Junit5Reporter.class})
class DocumentApprovalTests {
    @Test
    void approval_test_of_document_and_record_type() {
        verifyAllCombinations(
            DocumentTemplateType::fromDocumentTypeAndRecordType,
            new String[]{"DEER", "AUTP", "AUTM", "SPEC", "GLPP", "GLPM", "INVALID"},
            new String[]{"INDIVIDUAL_PROSPECT", "LEGAL_PROSPECT", "ALL", "INVALID"}
        );
    }
}
