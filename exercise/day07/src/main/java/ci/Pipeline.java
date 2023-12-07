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
            boolean testsPassed;
            boolean deploySuccessful;

            if (project.runTests().equals("success")) {
                log.info("Tests passed");
                testsPassed = true;
                boolean deploySuccessful1;
                if (testsPassed) {
                    if ("success".equals(project.deploy())) {
                        log.info("Deployment successful");
                        deploySuccessful1 = true;
                    } else {
                        log.error("Deployment failed");
                        deploySuccessful1 = false;
                    }
                } else {
                    deploySuccessful1 = false;
                }

                if (config.sendEmailSummary()) {
                    log.info("Sending email");
                    if (testsPassed) {
                        if (deploySuccessful1) {
                            emailer.send("Deployment completed successfully");
                        } else {
                            emailer.send("Deployment failed");
                        }
                    } else {
                        emailer.send("Tests failed");
                    }
                } else {
                    log.info("Email disabled");
                }
            } else {
                log.error("Tests failed");
                testsPassed = false;
                boolean deploySuccessful1;
                if (testsPassed) {
                    if ("success".equals(project.deploy())) {
                        log.info("Deployment successful");
                        deploySuccessful1 = true;
                    } else {
                        log.error("Deployment failed");
                        deploySuccessful1 = false;
                    }
                } else {
                    deploySuccessful1 = false;
                }

                if (config.sendEmailSummary()) {
                    log.info("Sending email");
                    if (testsPassed) {
                        if (deploySuccessful1) {
                            emailer.send("Deployment completed successfully");
                        } else {
                            emailer.send("Deployment failed");
                        }
                    } else {
                        emailer.send("Tests failed");
                    }
                } else {
                    log.info("Email disabled");
                }
            }
        } else {
            boolean deploySuccessful;

            log.info("No tests");

            if ("success".equals(project.deploy())) {
                log.info("Deployment successful");
                deploySuccessful = true;
            } else {
                log.error("Deployment failed");
                deploySuccessful = false;
            }

            if (config.sendEmailSummary()) {
                log.info("Sending email");
                if (deploySuccessful) {
                    emailer.send("Deployment completed successfully");
                } else {
                    emailer.send("Deployment failed");
                }
            } else {
                log.info("Email disabled");
            }
        }
    }
}
