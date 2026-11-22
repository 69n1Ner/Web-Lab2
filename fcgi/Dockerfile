FROM eclipse-temurin:21-jre
WORKDIR /app
COPY target/fcgi-lab2.jar fcgi-lab2.jar
EXPOSE 1011
CMD ["java", "-DFCGI_PORT=1011", "-jar", "fcgi-lab2.jar"]