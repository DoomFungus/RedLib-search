# Book Management and Search System (Search Microservice)
## Metrics   

[![Bugs](https://sonarcloud.io/api/project_badges/measure?project=DoomFungus_RedLib-search&metric=bugs)](https://sonarcloud.io/summary/new_code?id=DoomFungus_RedLib-search)
[![Code Smells](https://sonarcloud.io/api/project_badges/measure?project=DoomFungus_RedLib-search&metric=code_smells)](https://sonarcloud.io/summary/new_code?id=DoomFungus_RedLib-search)
[![Lines of Code](https://sonarcloud.io/api/project_badges/measure?project=DoomFungus_RedLib-search&metric=ncloc)](https://sonarcloud.io/summary/new_code?id=DoomFungus_RedLib-search)
[![Maintainability Rating](https://sonarcloud.io/api/project_badges/measure?project=DoomFungus_RedLib-search&metric=sqale_rating)](https://sonarcloud.io/summary/new_code?id=DoomFungus_RedLib-search)
[![Security Rating](https://sonarcloud.io/api/project_badges/measure?project=DoomFungus_RedLib-search&metric=security_rating)](https://sonarcloud.io/summary/new_code?id=DoomFungus_RedLib-catalogue)
[![Technical Debt](https://sonarcloud.io/api/project_badges/measure?project=DoomFungus_RedLib-search&metric=sqale_index)](https://sonarcloud.io/summary/new_code?id=DoomFungus_RedLib-search)
[![Vulnerabilities](https://sonarcloud.io/api/project_badges/measure?project=DoomFungus_RedLib-search&metric=vulnerabilities)](https://sonarcloud.io/summary/new_code?id=DoomFungus_RedLib-catalogue)
[![Reliability Rating](https://sonarcloud.io/api/project_badges/measure?project=DoomFungus_RedLib-search&metric=reliability_rating)](https://sonarcloud.io/summary/new_code?id=DoomFungus_RedLib-search)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=DoomFungus_RedLib-search&metric=coverage)](https://sonarcloud.io/summary/new_code?id=DoomFungus_RedLib-search)

## General

This repository contains a search microservice of a book management and search system developed for Advanced Software Engineering course in the BHT.
It's sister repository containing catalogue microservice can be found [here](https://github.com/DoomFungus/RedLib-catalogue). The documentation on the projectas a whole can also be found there

The purpose of this system is to provide a simple way to catalogue and search books as part of larger application, such as an online library or a bookstore.

Running the project:
1. Download sources
2. Compile and package the sources
3. Use dockerfile to create a docker container
4. Run both microservices in docker containers

For packaging a project and running containers you need to provide the  MongoDB datasource. Use DATASOURCE_URI env variable for providing URI, and DATASOURCE_DATABASE for providing database name.

By default, catalogue microservice will be listening on port 8081, and search on on 8082

### 1 UML
As part of the documentation, 3 diagrams have been created:
- The [use case diagram](https://github.com/DoomFungus/RedLib-catalogue/blob/master/documentation/diagrams/usecase-redlib.png) shows scenarios in which system can be used as well as involved actors. Relevant PlantUML markup can be found [here](https://github.com/DoomFungus/RedLib-catalogue/blob/master/documentation/diagrams/usecase.txt)
- The [component diagram](https://github.com/DoomFungus/RedLib-catalogue/blob/master/documentation/diagrams/component.png) shows the ways different microservices interact with each other and the external systems. Relevant PlantUML markup can be found [here](https://github.com/DoomFungus/RedLib-catalogue/blob/master/documentation/diagrams/component.txt)
- NoSQL database MongoDB is used for persisting data. ERD diagram explaining the used entities can be found [here](https://github.com/DoomFungus/RedLib-catalogue/blob/master/documentation/diagrams/erd.png)

### 2 DDD

As part of design process, the following domain diagram was created:
[Domain Diagram](https://github.com/DoomFungus/RedLib-catalogue/blob/master/documentation/diagrams/DDD.png)

### 3 Event Storming
Event storming diagram can be found [here](https://github.com/DoomFungus/RedLib-catalogue/blob/master/documentation/diagrams/event_storming.png), and core domain chart can be found [here](https://github.com/DoomFungus/RedLib-catalogue/blob/master/documentation/diagrams/core_domain_chart.png)

### 4 Metrics

SonarQube is used in the project for evaluation, vulnerability detection and metrics generation. The analysis is automatic, occurs as part of the CI/CD. Sonar analysis can be found here:
- [Catalogue service](https://sonarcloud.io/project/overview?id=DoomFungus_RedLib-catalogue)
- [Search service](https://sonarcloud.io/project/overview?id=DoomFungus_RedLib-search)

### 5 Clean Code Development

- [Clear function and parameter names](https://github.com/DoomFungus/RedLib-catalogue/blob/master/src/main/java/edu/bht/ase/redlib/service/impl/BookServiceImpl.java#L25)
- [Exception messages should provide enough information](https://github.com/DoomFungus/RedLib-catalogue/blob/master/src/main/java/edu/bht/ase/redlib/exception/codes/CatalogueExceptionCodes.java#L9)
- [Unit tests should check only 1 condition, it should be reflected in test name](https://github.com/DoomFungus/RedLib-catalogue/blob/master/src/test/java/edu/bht/ase/redlib/unittests/service/BookServiceTest.java#L54) \(Not necessarily 1 assert, but 1 logical condition)
- [Literals, such as error messages, should be reusable](https://github.com/DoomFungus/RedLib-catalogue/blob/master/src/main/java/edu/bht/ase/redlib/exception/codes/CatalogueExceptionCodes.java#L9)
- [Aspect logic, such as top-level exception handling, should be decoupled from business logic](https://github.com/DoomFungus/RedLib-catalogue/blob/master/src/main/java/edu/bht/ase/redlib/exception/ExceptionHandlerAdvice.java#L18)

Clean Code Cheatsheet can be found [here](https://github.com/DoomFungus/RedLib-catalogue/blob/master/documentation/ccd.md)


### 6 Build Management
Maven was used as a build management tool. A convention-over-configuration tool, it provides good support for prototyping new projects with Spring, giving the ability to rely on a convention in most cases and reducing boilerplate code.

Maven files (pom.xml) can be found here:
- [Catalogue Service](https://github.com/DoomFungus/RedLib-catalogue/blob/master/pom.xml)
- [Search Service](https://github.com/DoomFungus/RedLib-search/blob/master/pom.xml)


### 7 Testing
The project relies on unit tests, with only 1 test per service being integration test (context loading test). The test coverage of more than 75% percent was achieved on both services, with SonarQube and JaCoCo providing coverage report (can be viewed in detail in Sonarcloud).

Test value data, test objects and tests are separated into different classes, to increase test data reusability.

The most interesting test can be found [here](https://github.com/DoomFungus/RedLib-search/blob/master/src/test/java/edu/bht/ase/redlib/unittests/service/SearchCriteriaBuilderTest.java)

### 8 Continuous Delivery

Github Actions are used to create CI/CD pipeline for automatic test running and building, as well as triggering SonarQube analysis.

CI/CD is configured to run after each push to the master branch.

Github Actions provides an easy-to-setup, free for noncommercial use way to setup CI/CD in Github environment, and is easy to integrate with other services, such as AWS deploy or SonarQube analysis.

CI/CD workflow can be found [here](https://github.com/DoomFungus/RedLib-search/blob/master/.github/workflows/build.yml), while the results of the runs can be found in Actions tab 

### 9 DSL

To give more flexibility in searching books, a simple JSON-based DSL was created. The documentation for it can be found [here](https://github.com/DoomFungus/RedLib-catalogue/blob/master/documentation/dsl.md)

### 10 Functional Programming

- [Use final data structures](https://github.com/DoomFungus/RedLib-catalogue/blob/master/src/main/java/edu/bht/ase/redlib/exception/ex/AbstractException.java#L11)
- [Use side effect-free functions](https://github.com/DoomFungus/RedLib-search/blob/master/src/main/java/edu/bht/ase/redlib/service/impl/SearchCriteriaBuilder.java#L33)
- [Use higher-order finctions](https://github.com/DoomFungus/RedLib-search/blob/master/src/main/java/edu/bht/ase/redlib/service/impl/SearchCriteriaBuilder.java#L45)

### 11 IDE
Intellij IDEA was used during development of this project
  
The most used shortcuts:
- ```cmd + f``` (find)
- ```cmd + r``` (replace) 
- ```option + c/v/x``` (copy/paste/cut)
- ```cmd + /``` (comment (out))  

