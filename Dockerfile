# Start with a base image containing Java runtime
FROM openjdk:8-jdk-alpine

# Add Maintainer Info
LABEL maintainer="akramul@gmail.com"

# Add a volume pointing to /tmp
VOLUME /tmp

# Make port 8080 available to the world outside this container
EXPOSE 9000

ADD ftp://dhaka:1234@192.168.0.104/roomtypes-service.jar roomtypes-service.jar

# The application's jar file
#ARG JAR_FILE=roomtypes-service.jar

RUN ls

# Add the application's jar to the container
#ADD ${JAR_FILE} app.jar

# Run the jar file
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/roomtypes-service.jar"]