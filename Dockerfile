FROM openjdk:8
WORKDIR '/app'

ADD target/Notification-Service-0.0.1-SNAPSHOT.jar Notification-Service-0.0.1-SNAPSHOT.jar
EXPOSE 8086
COPY . .
ENTRYPOINT ["java", "-jar", "Notification-Service-0.0.1-SNAPSHOT.jar"]