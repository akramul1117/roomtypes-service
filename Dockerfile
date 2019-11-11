FROM java:8-jdk-alpine
ADD /home/akadir/repos/github/roomtypes-service/build/libs/roomtypes-service-0.0.1-SNAPSHOT.jar app.jar
WORKDIR /usr/app
EXPOSE 9000
ENTRYPOINT ["java", "-jar", "app.jar"]