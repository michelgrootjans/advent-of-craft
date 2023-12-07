package ci;

import ci.dependencies.Config;
import ci.dependencies.Emailer;
import ci.dependencies.Logger;
import ci.dependencies.Project;

public class Pipeline {
    private final Config config;
    private final Emailer emailer;
    private final Logger log;

    public Pipeline(Config config, Emailer emailer, Logger log) {
        this.config = config;
        this.emailer = emailer;
        this.log = log;
    }

    public void run(Project project) {
        if (project.hasTests()) {
            if (project.runTests().equals("success")) {
                log.info("Tests passed");
                deploy(project);
            } else {
                log.error("Tests failed");

                sendMail("Tests failed");
            }
        } else {
            log.info("No tests");
            deploy(project);
        }
    }

    private void deploy(Project project) {
        if (project.deploy().equals("success")) {
            log.info("Deployment successful");
            sendMail("Deployment completed successfully");
        } else {
            log.error("Deployment failed");
            sendMail("Deployment failed");
        }
    }

    private void sendMail(String Deployment_completed_successfully) {
        if (config.sendEmailSummary()) {
            log.info("Sending email");
            emailer.send(Deployment_completed_successfully);
        } else {
            log.info("Email disabled");
        }
    }
}
