FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app

# Copy from the nested project directory
COPY E-Commerce-Thymeleaf-SpringBoot-Project-master/pom.xml ./
COPY E-Commerce-Thymeleaf-SpringBoot-Project-master/src ./src

RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]