FROM openjdk:11-jre-slim
COPY .main/build/libs/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]