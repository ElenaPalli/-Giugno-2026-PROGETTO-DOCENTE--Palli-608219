# ==========================================
# Stage 1: Build dell'applicazione
# ==========================================
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app

# Copia il pom.xml e scarica le dipendenze
# (questo step sfrutta la cache di Docker per non riscaricare tutto ogni volta)
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copia il codice sorgente e compila l'applicazione
COPY src ./src
RUN mvn package -DskipTests

# ==========================================
# Stage 2: Run dell'applicazione
# ==========================================
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

# Copia il jar compilato dallo stage precedente
COPY --from=build /app/target/calcio-0.0.1-SNAPSHOT.jar app.jar

# Espone la porta di default (Beanstalk mapperà il traffico web su questa porta)
EXPOSE 8080

# Comando di avvio dell'applicazione
ENTRYPOINT ["java", "-jar", "app.jar"]
