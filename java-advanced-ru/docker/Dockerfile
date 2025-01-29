FROM eclipse-temurin:20-jdk
EXPOSE 3000
WORKDIR /project
COPY ./app/build/libs/*.jar ./spring.jar
ENTRYPOINT ["java", "-jar", "spring.jar"]