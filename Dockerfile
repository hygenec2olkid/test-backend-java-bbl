FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/test_backend_java_bbl-0.0.1-SNAPSHOT.jar test_backend_java_bbl.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","test_backend_java_bbl.jar"]



