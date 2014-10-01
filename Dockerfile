FROM consol/jetty-9

# Import source into container
ADD . /source

# Launch build
RUN cd /source && gradlew assemble
RUN mv build/libs/metrics-sample.war /maven/
