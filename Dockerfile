FROM openjdk:8-jdk-alpine
EXPOSE 8081
COPY target/OnlineBookStore-1.0-SNAPSHOT.jar OnlineBookStore-1.0-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/OnlineBookStore-1.0-SNAPSHOT.jar"]