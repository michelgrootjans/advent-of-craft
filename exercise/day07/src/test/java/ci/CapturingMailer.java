package ci;

import ci.dependencies.Config;
import ci.dependencies.Emailer;

import java.util.ArrayList;
import java.util.List;

public class CapturingMailer implements Emailer {
    private final List<String> messages = new ArrayList<>();
    private final CapturingLogger logger;
    private final Config config;

    public CapturingMailer(Config config, CapturingLogger logger) {
        this.config = config;
        this.logger = logger;
    }

    @Override
    public void send(String message) {
        if (config.sendEmailSummary()) {
            logger.info("Sending email");
            messages.add(message);
        } else {
            logger.info("Email disabled");
        }
    }

    public List<String> sentMails() {
        return messages;
    }
}
