# Simple CRUD with Vue.js and Spring Boot

This example app shows how to build a basic CRUD app with Spring Boot 2.1, Spring Data, and Vue.js.

Please read [Build a Simple CRUD App with Spring Boot and Vue.js](https://developer.okta.com/blog/2018/11/20/build-crud-spring-and-vue) to see how this app was created.

**Prerequisites:** [Java 8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html), [Node.js](https://nodejs.org/), and [Yarn](https://yarnpkg.com/).

## Getting Started

To run the server, run:

	./gradlew bootRun

To run the client, cd into the `client` folder and run:

	yarn install && yarn run serve

## Deployment

To build client and server package for prepare deployment

### Build Client

To build client, cd into the `client` folder and run:

	yarn build

Will generate output at folder `client/dist`

### Build Jar

To build an execute jar run:

	./gradlew build

Will create jar file at `build/libs/todo-app.jar`

## Try

	java -jar build/libs/todo-app.jar
