FROM openjdk:21
WORKDIR /app
COPY target/GroceryAPI-0.0.1-SNAPSHOT.jar /app/app.jar
EXPOSE 8080
ENV SPRING_DATASOURCE_URL=jdbc:mysql://mysql-host:3306/grocerapi
ENV SPRING_DATASOURCE_USERNAME=root
ENV SPRING_DATASOURCE_PASSWORD=Ashish@12345#
ENV SPRING_DATASOURCE_DRIVER-CLASS-NAME=com.mysql.cj.jdbc.Driver
CMD ["java", "-jar", "app.jar"]
