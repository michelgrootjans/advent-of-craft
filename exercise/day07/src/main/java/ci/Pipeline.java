package ci;

import ci.dependencies.Config;
import ci.dependencies.Emailer;
import ci.dependencies.Logger;
import ci.dependencies.Project;

public class Pipeline {
    private final Config config;
    private final Emailer emailer;
    private final Logger logger;

    public Pipeline(Config config, Emailer emailer, Logger logger) {
        this.config = config;
        this.emailer = emailer;
        this.logger = logger;
    }

    public void run(Project project) {
        if (project.hasTests()) {
            if (project.runTests().equals("success")) {
                logger.info("Tests passed");
                deploy(project);
            } else {
                logger.error("Tests failed");

                sendMail("Tests failed");
            }
        } else {
            logger.info("No tests");
            deploy(project);
        }
    }

    private void deploy(Project project) {
        if (project.deploy().equals("success")) {
            logger.info("Deployment successful");
            sendMail("Deployment completed successfully");
        } else {
            logger.error("Deployment failed");
            sendMail("Deployment failed");
        }
    }

    private void sendMail(String Deployment_completed_successfully) {
        if (config.sendEmailSummary()) {
            logger.info("Sending email");
            emailer.send(Deployment_completed_successfully);
        } else {
            logger.info("Email disabled");
        }
    }
}
