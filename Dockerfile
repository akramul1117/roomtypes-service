FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG DEPENDENCY=build/dependency

COPY ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY ${DEPENDENCY}/META-INF /app/META-INF
COPY ${DEPENDENCY}/BOOT-INF/classes /app
RUN touch catalina.home_IS_UNDEFINED/logs/tourbot_room_types_MicroService_debug.log
RUN touch catalina.home_IS_UNDEFINED/logs/tourbot_room_types_MicroService_error.log

EXPOSE 9000
ENTRYPOINT ["java","-cp","app:app/lib/*","com.teamamerica.tourbot.roomtypes.service.RoomtypesServiceApplication"]
