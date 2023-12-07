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
        if (project.runTests().equals("success")) {
            if (project.hasTests()) {
                logger.info("Tests passed");
            } else {
                logger.info("No tests");
            }
        } else{
            logger.error("Tests failed");
            emailer.send("Tests failed");
            return;
        }

        if (project.deploy().equals("success")) {
            logger.info("Deployment successful");
            emailer.send("Deployment completed successfully");
        } else {
            logger.error("Deployment failed");
            emailer.send("Deployment failed");
        }
    }
}
