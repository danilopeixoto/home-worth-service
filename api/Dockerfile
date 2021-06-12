FROM openjdk:11

LABEL description "A home appraisal API."
LABEL version "1.0.0"
LABEL maintainer "Danilo Peixoto Ferreira"

WORKDIR /usr/bin/api/

COPY . .

RUN chmod +x gradlew && ./gradlew build

EXPOSE 8080

CMD ["java", "-jar", "-Dspring.profiles.active=production", "build/libs/api-1.0.0.jar"]
