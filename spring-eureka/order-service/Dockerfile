FROM openjdk:20
LABEL authors="anuma"

COPY target/order-service-0.0.1-SNAPSHOT.jar /opt/order-service-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java", "-jar", "/opt/order-service-0.0.1-SNAPSHOT.jar"]
