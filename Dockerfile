FROM eclipse-temurin:25-jdk-alpine

WORKDIR /app


COPY target/*.war app.war

# Comando para rodar
ENTRYPOINT ["java", "-jar", "app.war"]