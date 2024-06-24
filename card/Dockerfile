FROM openjdk:11

ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} card.jar

ENTRYPOINT ["java","-jar","/card.jar"]