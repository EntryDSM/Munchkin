FROM openjdk:11-jre-slim
ENV TZ=Asia/Seoul
RUN mkdir /munchkin /munchkin/log /munchkin/excel
WORKDIR /munchkin
COPY ./main/build/libs/*.jar ./app.jar
ENTRYPOINT ["java","-jar","./app.jar"]
