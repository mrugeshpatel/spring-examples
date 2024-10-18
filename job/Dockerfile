FROM eclipse-temurin:21-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]

## Stage 1: Build the application
#FROM eclipse-temurin:21-alpine AS build
#
## Set the working directory in the container
#WORKDIR /app
#
## Copy the pom.xml and download dependencies
#COPY pom.xml ./
#RUN mvn dependency:go-offline -B
#
## Copy the source code and build the application
#COPY src ./src
#RUN rm -rf application.properties
#RUN mvn package -DskipTests
#
## Stage 2: Create the runtime image
#FROM openjdk:21-jdk-slim
#
## Set the working directory in the container
#WORKDIR /app
#
## Copy the jar file from the build stage
#COPY --from=build /app/target/*.jar app.jar
#
## Expose the port the application runs on
##EXPOSE 8080
#
## Command to run the application
#ENTRYPOINT ["java", "-jar", "app.jar", "--spring.config.location=optional:classpath:/,optional:file:config/"]