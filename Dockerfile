FROM openjdk:11
VOLUME [ "/tmp" ]
ADD target/websites-0.0.1-SNAPSHOT.jar websites-0.0.1-SNAPSHOT.jar
ENTRYPOINT [ "java" , "-jar" , "/websites-0.0.1-SNAPSHOT.jar"]