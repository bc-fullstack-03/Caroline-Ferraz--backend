FROM eclipse-temurin:17-jdk-alpine
ADD target/parrot-api-*.jar parrot-api.jar
ENTRYPOINT ["java", "-jar", "/parrot-api.jar"]