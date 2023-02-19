FROM openjdk:17-jdk-alpine

COPY target/RedLib-search-0.0.1-SNAPSHOT.jar redlib-search.jar
ENTRYPOINT ["java","-jar","/redlib-search.jar"]