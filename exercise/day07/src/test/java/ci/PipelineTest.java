package ci;

import ci.dependencies.Project;
import ci.dependencies.TestStatus;
import org.approvaltests.combinations.CombinationApprovals;
import org.approvaltests.reporters.ClipboardReporter;
import org.approvaltests.reporters.Junit5Reporter;
import org.approvaltests.reporters.UseReporter;
import org.junit.jupiter.api.Test;

import java.util.List;

@UseReporter({ClipboardReporter.class, Junit5Reporter.class})
class PipelineTest {
    @Test
    void run_approval_tests() {
        Boolean[] sendMail = {true, false};
        TestStatus[] testStatuses = {TestStatus.PASSING_TESTS, TestStatus.FAILING_TESTS, TestStatus.NO_TESTS};
        Boolean[] deploySuccedful = {true, false};

        CombinationApprovals.verifyAllCombinations(
            this::run,
            sendMail,
            testStatuses,
            deploySuccedful
        );
    }

    private RunResult run(Boolean sendMail, TestStatus testStatus, boolean deploySuccesful) {
        CapturingLogger logger = new CapturingLogger();
        CapturingMailer mailer = new CapturingMailer();
        Pipeline pipeline = new Pipeline(new TestConfiguration(sendMail), mailer, logger);
        pipeline.run(
            Project.builder()
            .setTestStatus(testStatus)
            .setDeploysSuccessfully(deploySuccesful)
            .build()
        );
        return new RunResult(logger.getLoggedLines(), mailer.sentMails());
    }

    private record RunResult(List<String> loggedLines, List<String> sentMails) {
    }

}
