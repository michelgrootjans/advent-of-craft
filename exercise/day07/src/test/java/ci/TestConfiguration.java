package ci;

import ci.dependencies.Config;

public class TestConfiguration implements Config {
    private boolean sendEmailSummary;

    @Override
    public boolean sendEmailSummary() {
        return sendEmailSummary;
    }

    void sendEmails() {
        sendEmailSummary = true;
    }

    void dontSentEmails() {
        sendEmailSummary = false;
    }
}
