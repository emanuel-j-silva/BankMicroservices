# Base image of Java Runtime
FROM openjdk:17-jdk-slim

LABEL maintainer="emanuel-j-silva"

# Add jar to the image
COPY target/accounts-0.0.1-SNAPSHOT.jar accounts-0.0.1-SNAPSHOT.jar

# Execute the application
ENTRYPOINT ["java", "-jar", "accounts-0.0.1-SNAPSHOT.jar"]
