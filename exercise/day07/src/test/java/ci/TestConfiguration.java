package ci;

import ci.dependencies.Config;

public class TestConfiguration implements Config {
    private boolean sendEmailSummary;

    @Override
    public boolean sendEmailSummary() {
        return sendEmailSummary;
    }

    public void enableEmails(boolean sendEmails) {
        sendEmailSummary = sendEmails;
    }
}
