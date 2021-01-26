FROM openjdk:11
VOLUME [ "/tmp" ]
ADD target/mobiwin-website.jar mobiwin-website.jar
ENTRYPOINT [ "java" , "-jar" , "/mobiwin-website.jar"]