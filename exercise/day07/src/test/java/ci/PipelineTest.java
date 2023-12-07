package ci;

import static ci.dependencies.TestStatus.FAILING_TESTS;
import static ci.dependencies.TestStatus.NO_TESTS;
import static ci.dependencies.TestStatus.PASSING_TESTS;
import static org.assertj.core.api.Assertions.assertThat;

import ci.dependencies.Project;
import ci.dependencies.TestStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

class PipelineTest {
    private final TestConfiguration config = new TestConfiguration();
    private final CapturingLogger log = new CapturingLogger();
    private final TestEmailer emailer = new TestEmailer();

    private Pipeline pipeline;

    @BeforeEach
    void setUp() {
        pipeline = new Pipeline(config, emailer, log);
    }

    static Stream<Arguments> run_build() {
        return Stream.of(
            Arguments.of(true, PASSING_TESTS, true, List.of("INFO: Tests passed", "INFO: Deployment successful", "INFO: Sending email")),
            Arguments.of(false, PASSING_TESTS, true, List.of("INFO: Tests passed", "INFO: Deployment successful", "INFO: Email disabled"))
        );
    }

    @ParameterizedTest(name = "send email: {0}, expectedLogs: {1}")
    @MethodSource
    void run_build(boolean sendEmails, TestStatus testStatus, boolean buildsSuccessfully, List<String> expectedLogLines) {
        config.enableEmails(sendEmails);

        var project = Project.builder()
            .setTestStatus(testStatus)
            .setDeploysSuccessfully(buildsSuccessfully)
            .build();

        new Pipeline(config, emailer, log).run(project);

        assertThat(log.getLoggedLines()).isEqualTo(expectedLogLines);

        if (sendEmails) {
            assertThat(emailer.sentMails()).containsExactly("Deployment completed successfully");
        } else {
            assertThat(emailer.sentMails()).isEmpty();
        }
    }

    @Test
    void project_with_tests_that_deploys_successfully_with_email_notification() {
        config.enableEmails(true);

        var project = Project.builder()
                .setTestStatus(PASSING_TESTS)
                .setDeploysSuccessfully(true)
                .build();

        pipeline.run(project);

        assertThat(log.getLoggedLines()).containsExactly(
            "INFO: Tests passed",
            "INFO: Deployment successful",
            "INFO: Sending email"
        );

        assertThat(emailer.sentMails()).containsExactly("Deployment completed successfully");
    }

    @Test
    void project_with_tests_that_deploys_successfully_without_email_notification() {
        config.enableEmails(false);

        Project project = Project.builder()
                .setTestStatus(PASSING_TESTS)
                .setDeploysSuccessfully(true)
                .build();

        pipeline.run(project);

        assertThat(log.getLoggedLines()).containsExactly(
            "INFO: Tests passed",
            "INFO: Deployment successful",
            "INFO: Email disabled"
        );

        assertThat(emailer.sentMails()).isEmpty();
    }

    @Test
    void project_without_tests_that_deploys_successfully_with_email_notification() {
        config.enableEmails(true);

        Project project = Project.builder()
                .setTestStatus(NO_TESTS)
                .setDeploysSuccessfully(true)
                .build();

        pipeline.run(project);

        assertThat(log.getLoggedLines()).containsExactly(
            "INFO: No tests",
            "INFO: Deployment successful",
            "INFO: Sending email"
        );

        assertThat(emailer.sentMails()).containsExactly("Deployment completed successfully");
    }

    @Test
    void project_without_tests_that_deploys_successfully_without_email_notification() {
        config.enableEmails(false);

        Project project = Project.builder()
                .setTestStatus(NO_TESTS)
                .setDeploysSuccessfully(true)
                .build();

        pipeline.run(project);

        assertThat(log.getLoggedLines()).containsExactly(
            "INFO: No tests",
            "INFO: Deployment successful",
            "INFO: Email disabled"
        );

        assertThat(emailer.sentMails()).isEmpty();
    }

    @Test
    void project_with_tests_that_fail_with_email_notification() {
        config.enableEmails(true);

        Project project = Project.builder()
                .setTestStatus(FAILING_TESTS)
                .build();

        pipeline.run(project);

        assertThat(log.getLoggedLines()).containsExactly(
            "ERROR: Tests failed",
            "INFO: Sending email"
        );

        assertThat(emailer.sentMails()).containsExactly("Tests failed");
    }

    @Test
    void project_with_tests_that_fail_without_email_notification() {
        config.enableEmails(false);

        Project project = Project.builder()
                .setTestStatus(FAILING_TESTS)
                .build();

        pipeline.run(project);

        assertThat(log.getLoggedLines()).containsExactly(
            "ERROR: Tests failed",
            "INFO: Email disabled"
        );

        assertThat(emailer.sentMails()).isEmpty();
    }

    @Test
    void project_with_tests_and_failing_build_with_email_notification() {
        config.enableEmails(true);

        Project project = Project.builder()
                .setTestStatus(PASSING_TESTS)
                .setDeploysSuccessfully(false)
                .build();

        pipeline.run(project);

        assertThat(log.getLoggedLines()).containsExactly(
            "INFO: Tests passed",
            "ERROR: Deployment failed",
            "INFO: Sending email"
        );

        assertThat(emailer.sentMails()).containsExactly("Deployment failed");
    }

    @Test
    void project_with_tests_and_failing_build_without_email_notification() {
        config.enableEmails(false);

        Project project = Project.builder()
                .setTestStatus(PASSING_TESTS)
                .setDeploysSuccessfully(false)
                .build();

        pipeline.run(project);

        assertThat(log.getLoggedLines()).containsExactly(
            "INFO: Tests passed",
            "ERROR: Deployment failed",
            "INFO: Email disabled"
        );

        assertThat(emailer.sentMails()).isEmpty();
    }

    @Test
    void project_without_tests_and_failing_build_with_email_notification() {
        config.enableEmails(true);

        Project project = Project.builder()
                .setTestStatus(NO_TESTS)
                .setDeploysSuccessfully(false)
                .build();

        pipeline.run(project);

        assertThat(log.getLoggedLines()).containsExactly(
            "INFO: No tests",
            "ERROR: Deployment failed",
            "INFO: Sending email"
        );

        assertThat(emailer.sentMails()).containsExactly("Deployment failed");
    }

    @Test
    void project_without_tests_and_failing_build_without_email_notification() {
        config.enableEmails(false);

        Project project = Project.builder()
                .setTestStatus(NO_TESTS)
                .setDeploysSuccessfully(false)
                .build();

        pipeline.run(project);

        assertThat(log.getLoggedLines()).containsExactly(
            "INFO: No tests",
            "ERROR: Deployment failed",
            "INFO: Email disabled"
        );

        assertThat(emailer.sentMails()).isEmpty();
    }
}