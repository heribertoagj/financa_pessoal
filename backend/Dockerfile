# Multi-stage build for backend (production)
FROM gradle:8.6-jdk21 AS build
WORKDIR /home/gradle/project
COPY --chown=gradle:gradle . /home/gradle/project
RUN gradle bootJar --no-daemon --stacktrace

FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /home/gradle/project/build/libs/*.jar app.jar
EXPOSE 5050
ENV SPRING_PROFILES_ACTIVE=develop
ENTRYPOINT ["sh","-c","java -jar /app/app.jar"]

