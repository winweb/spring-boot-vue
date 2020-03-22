# Dockerfile

# 1st Stage - Build Vue App
FROM node:10.16.3 AS vue-builder
RUN mkdir -p /app
WORKDIR /app
COPY client/package.json .
COPY client/yarn.lock .
RUN yarn install --ignore-platform
COPY client .
RUN yarn build

# 2nd Stage - Build Spring Boot App
FROM gradle:jdk8 as java-builder
COPY --chown=gradle:gradle . /home/gradle/app
COPY --from=vue-builder /app/dist /home/gradle/app/client/dist
WORKDIR /home/gradle/app
RUN gradle build

# 3rd Stage - Build Final Image
FROM openjdk:8-jre-slim
EXPOSE 8080
COPY --from=java-builder /home/gradle/app/build/libs/todo-app.jar /app/
WORKDIR /app
ENTRYPOINT ["java","-jar","/app/todo-app.jar"]  
