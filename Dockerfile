FROM consol/jetty-9

# Import source into container
ADD . /source

# Launch build
RUN cd /source && ./gradlew assemble
RUN mv /source/build/libs/metrics-sample.war /maven/
RUN cd /source && ./gradlew clean
