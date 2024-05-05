FROM ubuntu:latest
LABEL authors="anuma"

RUN apt-get update && \
    apt-get install -y openjdk-17-jdk-headless


WORKDIR /usr/app

COPY ./target/demo-spring-cicd-0.0.1-SNAPSHOT.jar ./

CMD ["java", "-jar", "demo-spring-cicd-0.0.1-SNAPSHOT.jar"]
