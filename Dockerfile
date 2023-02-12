FROM openjdk:17-jdk-alpine

HEALTHCHECK --interval=30s --timeout=3s CMD curl -f http://localhost:80/search/actuator/health || exit 1

COPY target/RedLib-search-0.0.1-SNAPSHOT.jar redlib-search.jar
ENTRYPOINT ["java","-jar","/redlib-search.jar"]