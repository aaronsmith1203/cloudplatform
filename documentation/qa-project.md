# QA Project
###### Resources
- [Jira (Roadmap)](https://aaronsmith1203.atlassian.net/jira/software/projects/CP/boards/3/roadmap)

###### Contents
- [[#Introduction|Introduction]]
- [[#Risk_Assessment|Risk Assessment]]
- [[#Future|Future]]

### Introduction

*Note - this document contains quotations from the repository [readme](../README.md). The readme is aimed at a more general audience, but some of the content is relevant to this documentation.*

Cloud Platform, aka *'ASure Cloud'*, 

*from [README](../README.md):*
> Your objective with this project is to achieve the following:
>
> To create a **REST API** using **Java** and **Spring Boot**, with utilisation of supporting tools, methodologies, and technologies, that encapsulates all fundamental and practical modules covered during training.
>
> Specifically, you are required to create a Spring Boot API using:

object-oriented programming. CRUD funtionality.

An application back-end developed using **Java** and **Spring Boot**.
Using **MySQL** to build a relational-database for application-persistence.

a means of making API calls using **Postman** and a means of checking persistence (Workbench/H2 console)

**Maven** integrated-build tool. Dependency management.

Writing *unit* and *integration tests* using **JUnit**, and the mocking capabilities provided by **Mockito**, and **Spring Tools**. 

**Git**. Using **GitHub Workflows** to require that unit and integration-tests pass before allowing a pull-request into the `development` branch.

##### Why are we doing this?

### Project Management
- *see: [Jira Project](https://aaronsmith1203.atlassian.net/jira/software/projects/CP/boards/3/roadmap)*

You must use **Jira** or similar project tracking software to track your project using Agile Scrum methods. You must make use of:

Used *MoSCoW prioritisation*.

Estimations of effort using *story points*.

Work organised into *Epics*, *User Stories*, and *Tasks*.

### Risk Assessment
You must also produce a risk assessment to identify and analyse any potential risks to your application and infrastructure. This must be evidenced in your documentation.

### Code
- *see: [Git repository (GitHub)](https://github.com/aaronsmith1203/cloudplatform)*

- uses feature-branch model, where a branch is created for each new feature - tagged with Jira issue number, and then merged into the `development` branch. Releases of the application are created when the features and bug fixes from development are merged into the `main` branch.

##### Packages
Java package `info.aaronsmith.demo.cloudplatform`
- Code related to account-management for the cloud-platform is stored in the `accounts` sub-package.
- Code related to the services offered by the cloud-platform is stored in the `services` sub-package.
- Code related to the RESTful API controller is stored in the `api` sub-package.

##### Releases
[Releases](https://github.com/aaronsmith1203/cloudplatform/releases) of the project can be found in the [GitHub repository](https://github.com/aaronsmith1203/cloudplatform/releases).

### Database
##### Entity-Relationship Diagram
<img src="/documentation/erd-diagram.png" width="209" />

### Files
- Jar file
- SQL file
- Postman Collection

### Demo
- See installation.

#### Using API with Postman
Screenshots showing your postman requests and the output from the API.

#### Database Persistence
Screenshots of your database to prove that data is being persisted.

#### Testing
Screenshot of your test results, including coverage report.

### Future


