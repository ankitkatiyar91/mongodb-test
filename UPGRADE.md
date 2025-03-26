### Expectations

Migrate a specific legacy JBoss Java application to a more modern platform according to the following directives:

● The application for you to migrate is the ‘kitchensink’ JBoss application available in the Red Hat JBoss EAP Quickstarts GitHub repository (no need to migrate any of the other applications listed there)

● The migrated application runtime you must target is the latest stable version of Spring Boot or Quakus (your choice) based on Java 21 (you can host the running application on your laptop, in the cloud, or wherever - you don’t need to target OpenShift as the host environment necessarily, which the application’s README discusses in places).

● Create a new personal public GitHub project and host the source code for your new migrated application there (for just ‘kitchensink’ only). Include a new README where you outline the steps a developer would need to take to build and run your migrated application.

● Try to approach this migration process in the way you might do if the legacy application codebase was far larger, in terms of how you break up the problem into more management tasks, addressing the sort of infrastructure, scaffolding, and software engineering principles you would need to apply to help mitigate risk in the migration work and ensure the quality of what is migrated (during the subsequent playback session, you will be asked questions on the approach you took).

● OPTIONAL STRETCH GOAL: Modify the application to work against a MongoDB database rather than the existing relational target database.

Playback Interview Presentation

1.5-hour presentation on Zoom to a few MongoDB staff where you will:

● Deliver a live demo of the running migrated application.

● Show what you did under the covers to perform the migration with examples of components you changed to highlight the processes you took.

● Outline any interesting aspects you learned from this process that would inform your approach to a future migration project.

● Answer questions from the interviewers on what you achieved in the migration, how you went about it, and other insights you’ve learned.

●Towardtheendofthesession, respondtoanyothermoregeneral interviewquestions youareasked.

### Steps followed
* Source 
* Moved initial code to git
* Install `brew install jboss-forge`
* Found that JBOSS EAP is not available for public download, So moving to migration directly.
* Install Spring and other dependencies and make tests work. Verifies with `mvn clean install`
* Ensure local `mvn spring-boot:run`