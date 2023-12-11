.DEFAULT_GOAL := build-run

setup:
	.app/gradlew wrapper --gradle-version 8.5

clean:
	.app/gradlew clean

build:
	.app/gradlew clean build

install:
	.app/gradlew clean install

run-dist:
	.app/build/install/app/bin/app

run:
	.app/gradlew run

test:
	.app/gradlew test

report:
	.app/gradlew jacocoTestReport

lint:
	.app/gradlew checkstyleMain

lint-test:
	.app/gradlew checkstyleTest

check-deps:
	.app/gradlew dependencyUpdates -Drevision=release


build-run: build run

.PHONY: build