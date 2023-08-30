# Use a imagem oficial do OpenJDK como base
FROM eclipse-temurin:20-jdk

# Variáveis de ambiente para o serviço
ARG JAR_FILE=target/*.jar
ENV APP_HOME=/app

# Copie o arquivo JAR do seu projeto para a imagem Docker
COPY ${JAR_FILE} ${APP_HOME}/app.jar

# Defina o diretório de trabalho e a porta padrão
WORKDIR ${APP_HOME}
EXPOSE 8080

# Comando para iniciar o serviço
ENTRYPOINT ["java", "-jar", "app.jar"]