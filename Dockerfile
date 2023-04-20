FROM openjdk:17-alpine
VOLUME /tmp
ADD target/WordScrambleGame-0.0.1-SNAPSHOT.jar word-scramble-game.jar
ENTRYPOINT ["java", "-jar", "/word-scramble-game.jar"]