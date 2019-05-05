from openjdk:8
ADD build/libs/users-develop.20190505.1.0.0.jar users-develop.20190505.1.0.0.jar
EXPOSE 8085
ENTRYPOINT ["java", "-jar", "users-develop.20190505.1.0.0.jar"]