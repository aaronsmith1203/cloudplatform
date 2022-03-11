![GitHub last commit (branch)](https://img.shields.io/github/last-commit/aaronsmith1203/cloudplatform/development)
# Cloud Platform 

### QA Training
- *see: [QA Project Documentation](/documentation/qa-project.md)*

This application forms the final assessment for the Software Development Bootcamp provided by QA Training that I attended (Jan-Mar 2022).

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
