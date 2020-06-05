#!/bin/sh
mvn clean package && docker build -t challenge .
docker rm -f challenge || true && docker run -d -p 9080:9080 -p 9443:9443 --name challenge