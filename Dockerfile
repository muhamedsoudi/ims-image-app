FROM openjdk:12
COPY IMS-ImageApp/target/IMS-ImageApp.jar IMS-ImageApp.jar
EXPOSE 8009
ENTRYPOINT ["java", "-jar", "IMS-ImageApp.jar"]