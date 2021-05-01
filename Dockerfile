FROM tomcat:9.0

RUN apt-get update
RUN apt-get install -y maven

COPY pom.xml /app/pom.xml
COPY src /app/src

WORKDIR /app

RUN mvn install -DskipTests
RUN cp target/movie-catalog.war /usr/local/tomcat/webapps/ROOT.war

EXPOSE 8080
CMD ["catalina.sh", "run"]
