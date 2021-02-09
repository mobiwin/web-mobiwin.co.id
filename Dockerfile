FROM openjdk:11
VOLUME [ "/tmp" ]
EXPOSE 4000
ADD target/websites-0.0.1-SNAPSHOT.jar websites-0.0.1-SNAPSHOT.jar
ENTRYPOINT [ "java" , "-jar" , "/websites-0.0.1-SNAPSHOT.jar"]