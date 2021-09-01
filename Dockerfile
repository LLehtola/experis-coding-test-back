FROM openjdk:15
VOLUME tmp
ADD build/libs/experis-coding-test-back-0.0.1-SNAPSHOT.jar experis-coding-test-back.jar
ENTRYPOINT ["java", "-jar", "/experis-coding-test-back.jar"]