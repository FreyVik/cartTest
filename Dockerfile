FROM openjdk:19-jdk-alpine

COPY "./target/cart-0.0.1-SNAPSHOT.jar" "app.jar"

EXPOSE 9000

ENTRYPOINT ["java", "-jar", "app.jar"]