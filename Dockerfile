FROM openjdk:17

COPY target/*.jar app.jar

VOLUME /uploads

ENTRYPOINT exec java -jar /app.jar
EXPOSE 8080