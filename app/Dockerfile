#stage 1
#Start with a base image containing Java runtime
FROM maven:3.8.3-openjdk-17 as build

# Add Maintainer Info
LABEL maintainer="Aleksei Petrov <peta0982@gmail.com>"

ADD app .

# build app 
RUN mvn -Dmaven.test.failure.ignore=true -f pom.xml clean package

RUN ls && ls target

#stage 2
#Same Java runtime
FROM openjdk:17-slim

COPY --from=build app.jar app.jar

#execute the application
ENTRYPOINT ["java","-cp","app:app/lib/*","ru.wakeupneo.recruiting.RecruitingApplication"]