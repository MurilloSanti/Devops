# Use uma imagem base do OpenJDK
FROM openjdk:17-jdk-slim

# Defina o diretório de trabalho no contêiner
WORKDIR /app

# Copie os arquivos Java para o contêiner
COPY financiamento.java main.java ./

# Compile os arquivos Java
RUN javac main.java

# Comando para executar a aplicação
CMD ["java", "Main"]
