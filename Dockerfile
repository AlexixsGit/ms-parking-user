from openjdk:8
ADD build/users-develop.20190504.1.0.0.jar users-develop.20190504.1.0.0.jar
EXPOSE 8085
ENTRYPOINT ["java", "-jar", "users-develop.20190504.1.0.0.jar"]