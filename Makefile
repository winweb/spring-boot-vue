.PHONY: build-vue
build-vue:
	cd client && yarn install && yarn build

.PHONY: build
build: build-vue
	./gradlew build

.PHONY: run
run: build
	java -jar build/libs/todo-app.jar

.PHONY: run-vue
run-vue:
	yarn install && yarn run serve