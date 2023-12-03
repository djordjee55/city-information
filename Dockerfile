FROM bellsoft/liberica-openjdk-debian:17-35 AS builder
COPY target/city-information-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]