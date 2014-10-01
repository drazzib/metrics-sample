FROM java:7

# Import source into container
ADD . /source
WORKDIR /source

# Launch build
RUN ./gradlew assemble

# Expose the HTTP port
EXPOSE 8080

# Define command to launch application
CMD ["./gradlew appRun"]
