package ci;

import ci.dependencies.Emailer;
import ci.dependencies.Logger;
import ci.dependencies.Project;

public class Pipeline {
    private final Emailer emailer;
    private final Logger logger;

    public Pipeline(Emailer emailer, Logger logger) {
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
                emailer.send("Tests failed");
            }
        } else {
            logger.info("No tests");
            deploy(project);
        }
    }

    private void deploy(Project project) {
        if (project.deploy().equals("success")) {
            logger.info("Deployment successful");
            emailer.send("Deployment completed successfully");
        } else {
            logger.error("Deployment failed");
            emailer.send("Deployment failed");
        }
    }
}
