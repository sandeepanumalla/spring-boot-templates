FROM openjdk:20
LABEL authors="anuma"

COPY target/inventory-service-0.0.1-SNAPSHOT.jar /opt/inventory-service-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java", "-jar", "/opt/inventory-service-0.0.1-SNAPSHOT.jar"]
