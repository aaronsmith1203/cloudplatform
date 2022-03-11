<<<<<<< HEAD
![GitHub last commit (branch)](https://img.shields.io/github/last-commit/aaronsmith1203/cloudplatform/development)
# Cloud Platform 
=======
# ASure Cloud Platform
>>>>>>> development

Demo Cloud Platform *(aka 'ASure Cloud')*

Backend controller for a fictional Cloud Platform.
- Demonstrate learned Java and SpringBoot (Spring Framework)
- Consolidate my understanding of cloud computing by replicating some of the elements demonstrated by cloud providers, in particular Azure.
- Improve my software development skills via a demo project where I can lean on my existing experience and knowledge of hosting on-premises IT infrastructure experience in the field of IT operations with my 
Use my experience of hosting on-premises IT infrastructure in IT-operations roles to attempt to replicate my interpretation on how a cloud-provider operates

- currently provides the customer-layer providing account-management, and subscription-management, where customers can use their accounts to query the services offered, and their costs. And subscribe to services over a period of 1/6/12/24-monthly period, where they save money in the long-term if they commit to a longer renewal period.

##### QA Training
- see: [[/documentation/qa-project|QA Project Documentation]]

Final project as part of the Software Development Bootcamp provided by QA Training that I attended (Jan-Mar 2022)

##### Future

- Implement a service-layer that can provide infrastructure services (IaaS) such as *virtual machines*, *network-segments*, *load-balancing*.

- Front-end user-interface, it is a lower-priority.

### Installation
- Java Runtime Environment version
- The  can be `java -jar 

### Usage
- Backend controller that provides a RESTful API on `localhost:8080`
- Swagger URL

#### Working with Accounts
#####Creating a new Account
Endpoint:
```txt
[POST] /createAccount
```

Request body (JSON):
```json
{
    "title": "Mr",
    "firstName": "Aaron",
    "lastName": "Smith",
    "tenantName": "asuretenant",
    "addressLine1": "22a Road Avenue",
    "addressLine2": "",
    "city": "Mockiton",
    "county": "Javashire",
    "postCode": "MO22 8JA",
    "telephoneNumber": "01234 567890",
    "emailAddress": "aaronsmith@mydomain.com"
}
```

#### READ - Get all Accounts
Endpoint:
```txt
[GET] /getAccounts
```

#### READ - Get an Account by ID
Endpoint:
```txt
[GET] /getAccount/{id}
```

#### UPDATE - Update an Account by ID
Endpoint:
```txt
[PUT] /updateAccount/{id}
```
Request body (JSON):

Response body:
```
```

#### Working with Services

#### Working with Subscriptions
