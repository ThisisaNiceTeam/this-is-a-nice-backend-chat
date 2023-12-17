FROM khipu/openjdk17-alpine

WORKDIR /usr/src/app

ARG JAR_PATH=./build/libs
ENV SPRING_PROFILES_ACTIVE=dev

COPY ${JAR_PATH}/this-is-a-nice-backend-chat-0.0.1-SNAPSHOT.jar ${JAR_PATH}/this-is-a-nice-backend-chat-0.0.1-SNAPSHOT.jar

EXPOSE 8080
CMD ["java","-jar","./build/libs/this-is-a-nice-backend-chat-0.0.1-SNAPSHOT.jar"]