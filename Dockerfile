FROM consol/jetty-9

# Import source into container
ADD . /source

# Launch build
RUN cd /source && ./gradlew assemble
RUN mkdir -p $DEPLOY_DIR
RUN cd /source && mv build/libs/metrics-sample.war $DEPLOY_DIR/
RUN cd /source && ./gradlew clean
