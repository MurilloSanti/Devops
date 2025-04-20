# Etapa de compilação
FROM eclipse-temurin:17-jdk AS build

WORKDIR /app

# Copia os fontes
COPY ./src /app/src

# Compila os arquivos para a pasta bin
RUN mkdir /app/bin && javac -d /app/bin /app/src/sisfinan/*.java

# Etapa final para execução
FROM eclipse-temurin:17-jdk

WORKDIR /app

# Copia os .class compilados
COPY --from=build /app/bin /app/bin

# Define o ponto de entrada
CMD ["sh", "-c", "java -cp /app/bin sisfinan.main && sleep 3600"]

