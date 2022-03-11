# QA Project
###### Resources
- [Jira (Roadmap)](https://aaronsmith1203.atlassian.net/jira/software/projects/CP/boards/3/roadmap)

###### Contents
- [[#Introduction|Introduction]]
- [[#Risk_Assessment|Risk Assessment]]
- [[#Future|Future]]

### Introduction

*Note - this document contains quotations from the repository [readme](../README.md). The readme is aimed at a more general audience, but some of the content is relevant to this documentation.*

**Cloud Platform** (aka **ASure Cloud**), 


##### Why?
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

#### Risk Assessment
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
- [v0.0.1](..)


### Getting Started with the Application
Application files can be found in the `resources/` directory within the project repository.

#### Installation of database
The application will attempt to connect to a **MySQL** database with the following connection details:
- url: `mysql://localhost:3306/cloudplatform`
- username: `root`
- password: `password`

Install the `cloudplatform` database before attempting to run the application. This can be done by running the `database_init.sql` script located in the `resources/` directory within the repository. 

```bash
$ mysql -u root -p

mysql> source <path-to-repo>/resources/database_init.sql
```

#### Running application
The latest build of the **Java** application `cloudplatform-0.0.1.jar` file is located within the `resources/` directory within the repository.

```bash
$ java -jar <path-to-repo>/resources/cloudplatform-0.0.1.jar
```

#### Testing with Postman
A collection of **Postman** requests have been exported to a file called `Cloud Platform.postman_collection.json`, which is located in the `resources/` directory within the repository. 

This collection of request definitions can be imported into Postman and used against the REST API.

### Application Demo
- overview
- **Account** and **CloudService** entities.

#### API - Working with Accounts
##### API - Creating an Account
<img src="images/create_account_success.png" />

<img src="images/create_account_tenant_exception.png" />

<img src="images/delete_account_exception.png" />

##### API - Reading an Account
get all accounts
<img src="images/get_allaccounts_success.png" />

get an account by id
<img src="images/get_account_by_id_success.png" />

get an account by id, exception
<img src="images/get_account_by_id_exception.png" />

get an account by email address
<img src="images/get_account_by_email_success.png" />

get an account by email, exception
<img src="images/get_account_by_id_exception.png" />


##### API - Updating an Account
<img src="images/update_account_success.png" />
<img src="images/update_account_tenant_exception.png" />
<img src="images/update_account_exception.png" />

##### API - Deleting an Account
<img src="images/delete_account_success.png" />
<img src="images/delete_account_exception.png" />

#### API - Working with Cloud Services
##### API - Creating a Cloud Service
<img src="images/create_service_success.png" />

##### API - Reading a Cloud Service
<img src="images/get_allservices_success.png" />
<img src="images/get_service_by_id_success.png" />
<img src="images/get_service_by_id_exception.png" />

##### API - Updating a Cloud Service
<img src="images/update_service_success.png" />
<img src="images/update_service_exception.png" />

##### API - Deleting a Cloud Service
<img src="images/delete_service_success.png" />
<img src="images/delete_service_exception.png" />

### Database
- `localhost:3306/cloudplatform`

##### Entity-Relationship Diagram
<img src="images/erd-diagram.png" width="435" />

##### Persistence
<img src="images/account_table_persistence.png" />
<img src="images/cloud_service_table_persistence.png" />

### Test Coverage
- `Account` class `hash()` method untested.
- `CloudServive` class `hash()` method untested.
- `CloudPlatformApplication` untested.

<img src="images/test-coverage.png" />

### Future


