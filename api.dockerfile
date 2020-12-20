FROM openjdk:8
LABEL maintainer="yurikua11@gmail.com"
VOLUME /home/server
WORKDIR /home/server
COPY ./ /home/server/
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/home/server/target/docker-spring-api.jar"]
