package ci;

import ci.dependencies.Emailer;

import java.util.ArrayList;
import java.util.List;

public class TestEmailer implements Emailer {
    private final List<String> messages = new ArrayList<>();

    @Override
    public void send(String message) {
        messages.add(message);
    }

    public List<String> sentMails() {
        return messages;
    }
}
