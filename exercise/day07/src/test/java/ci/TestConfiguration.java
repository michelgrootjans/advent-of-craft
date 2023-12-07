package ci;

import ci.dependencies.Config;

public class TestConfiguration implements Config {
    private final boolean sendEmailSummary;

    public TestConfiguration(Boolean sendMail) {
        sendEmailSummary = true;
    }

    @Override
    public boolean sendEmailSummary() {
        return sendEmailSummary;
    }
}
