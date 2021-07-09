FROM openjdk:11-jre-slim
ENV TZ=Asia/Seoul
RUN mkdir -p /munchkin/log /munchkin/excel /munchkin/static/fonts
WORKDIR /munchkin
COPY ./main/build/libs/*.jar ./app.jar
ENTRYPOINT ["java","-jar","./app.jar"]
