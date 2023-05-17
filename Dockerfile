FROM openjdk:8-jre-slim
WORKDIR /software-engineering-clean-architecture
COPY target/*.war /software-engineering-clean-architecture/clean-architecture-0.0.1-SNAPSHOT.war
EXPOSE 9090
CMD java -XX:+UseContainerSupport -Xmx512m -Dserver.port=9090 -jar clean-architecture-0.0.1-SNAPSHOT.war 


