FROM openjdk:11-jre-slim
COPY ./main/build/libs/*.jar app.jar
ENV TZ=Asia/Seoul
ENTRYPOINT ["java","-jar","/app.jar"]
