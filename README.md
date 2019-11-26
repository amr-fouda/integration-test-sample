# integration-test-sample
Spring boot project that demonstrate integration tests using a docker container for postgres RDBMS , flyway for database propagation and fabric8 maven plugin

## Prerequisite
* JDK 10
* Maven 3+
* Docker 19+

## Maven Plugins
| Plugin artifact id| Overview |
| :-------------: |:-------------|
| jacoco-maven-plugin |Start jacoco agent , generate report and check that code coverage metrics are being met|
| maven-failsafe-plugin | Detecting and running integration tests|
| docker-maven-plugin | (Open/ later Close) database and messaging containers for integration testing|
| maven-antrun-plugin | sleep time for docker containers to be init|
| flyway-maven-plugin | migrate goal to be executed manually & before running integration tests|


## Screenshots
![alt text](https://github.com/amr-fouda/integration-test-sample/blob/master/1_StartingContainers.png)
![alt text](https://github.com/amr-fouda/integration-test-sample/blob/master/2_StoppingContainers.png)
