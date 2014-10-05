This sample project demonstrates usage of Metrics + Graphite + Graphana.
         
Presentation
============
This project wire together 3 monitoring components :

+ [Metrics](https://dropwizard.github.io/metrics/) which is a Java library which gives you insight into **what your code does in production environment**.
It's a powerful toolkit which provide glues metrics for common libraries like _Jetty_, _Logback_, _Log4j_, _Apache HttpClient_, _Ehcache_, _JDBI_, _Jersey_.
You can report to backends like _Ganglia_ and _Graphite_.

+ [Graphite](http://graphite.wikidot.com/) is a highly scalable real-time graphing system.
It will collects numeric time-series data into Graphite's processing backend, _Carbon_.

+ [Graphana](http://grafana.org/) is metrics dashboard and graph editor for Graphite, InfluxDB & OpenTSDB.
It's forked version from Kibana reworked for more generic usage. 

Source code
===========
Current project provides a sample integration between JEE Servlet and Metrics. You can check sample code :

+ [HelloWorldServlet](/drazzib/metrics-sample/blob/master/src/main/java/com/drazzib/metrics/HelloWorldServlet.java) compute some metrics about received requests
+ [MetricRegistryCListener](/drazzib/metrics-sample/blob/master/src/main/java/com/drazzib/metrics/MetricRegistryCListener.java) create MetricRegistry and
  register some reporter for JMX and Graphite.

Usage
=====
Graphite and Graphana are a bit complex components to deploy, so I provide some pre-build containers using :

+ [Docker](https://www.docker.com/) provide easy to deploy containers: it provide a portable, lightweight runtime and packaging tool.
+ [Fig](http://www.fig.sh) is a fast and isolated development environments using Docker.

Once you have Docker and Fig installed and configured, you can run :

```
fig up graphite
```

It will download & start a full-blown [graphite+graphana](https://registry.hub.docker.com/u/kamon/grafana_graphite/) container.

You can then submit your metrics to localhost:2003 (Graphite UDP port).

