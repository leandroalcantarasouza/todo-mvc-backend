FROM maven:3.6.0-jdk-8 as maven
WORKDIR usr/src/app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src src
RUN mvn package

FROM openjdk:8-jre
MAINTAINER Leandro Souza <leandro.alcantara.souza@gmail.com>
VOLUME /tmp
WORKDIR /usr/src/app
COPY --from=maven /usr/src/app/target/todo-backend.jar todo-backend.jar
ENV JAVA_OPTIONS=""
ENV LOGGING_CONFIG=""
EXPOSE 8080
ENTRYPOINT java $JAVA_OPTIONS -Djava.security.egd=file:/dev/./urandom -jar todo-backend.jar
