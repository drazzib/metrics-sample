FROM consol/jetty-9

RUN mkdir -p $DEPLOY_DIR

# Import source into container
ADD . /source

# Launch build AND cleanup
RUN cd /source \
 && ./gradlew assemble \
 && cp build/libs/metrics-sample.war $DEPLOY_DIR/ \
 && ./gradlew clean \
 && rm -Rf ~/.gradle
