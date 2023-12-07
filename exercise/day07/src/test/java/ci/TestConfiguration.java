package ci;

import ci.dependencies.Config;

public record TestConfiguration(Boolean sendEmail) implements Config {
    @Override
    public boolean sendEmailSummary() {
        return sendEmail;
    }

    @Override
    public String toString() {
        return "sendEmail: " + sendEmail;
    }
}
