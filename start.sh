#!/bin/bash
kill -9 `lsof -i:8081`
mvn clean install -DskipTests
docker build --tag=book-store:latest .
docker run -p8081:8081 book-store:latest