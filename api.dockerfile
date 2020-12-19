FROM openjdk:8
LABEL maintainer="yurikua11@gmail.com"
ADD target/docker-spring-api.jar docker-spring-api.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "docker-spring-api.jar"]
