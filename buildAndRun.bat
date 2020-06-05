@echo off

call mvn -q clean package

call docker pull openliberty/open-liberty:kernel-java8-openj9-ubi

call docker build -t challenge:1.0-SNAPSHOT .

call docker run -d --name challenge -p 9080:9080 challenge:1.0-SNAPSHOT
