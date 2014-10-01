package com.drazzib.metrics;

import com.codahale.metrics.JmxReporter;
import com.codahale.metrics.MetricFilter;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.graphite.Graphite;
import com.codahale.metrics.graphite.GraphiteReporter;
import com.codahale.metrics.servlets.MetricsServlet;

import javax.servlet.annotation.WebListener;
import java.net.InetSocketAddress;
import java.util.concurrent.TimeUnit;

@WebListener
public class MetricRegistryCListener extends MetricsServlet.ContextListener {

    @Override
    protected MetricRegistry getMetricRegistry() {
        MetricRegistry registry = new MetricRegistry();

        // Parameters
        String graphiteHost = getGraphiteHost();
        int graphitePort = 2003;

        // Configure
        final JmxReporter jmx = prepareJMX(registry);
        final GraphiteReporter graphite = prepareGraphite(registry, graphiteHost, graphitePort);

        // Start sending reports !
        graphite.start(10, TimeUnit.SECONDS);
        jmx.start();

        return registry;
    }

    private String getGraphiteHost() {
        // Use Docker environment to get Graphite port
        String dockerGraphiteIP = System.getenv("GRAPHITE_PORT_2003_TCP_ADDR");
        return dockerGraphiteIP != null ? dockerGraphiteIP : "localhost";
    }

    // https://dropwizard.github.io/metrics/3.1.0/manual/graphite/
    private GraphiteReporter prepareGraphite(MetricRegistry registry, String host, int port) {
        final Graphite graphite = new Graphite(new InetSocketAddress(host, port));
        return GraphiteReporter.forRegistry(registry)
                .prefixedWith("web1.example.com")
                .convertRatesTo(TimeUnit.SECONDS)
                .convertDurationsTo(TimeUnit.MILLISECONDS)
                .filter(MetricFilter.ALL)
                .build(graphite);
    }

    private JmxReporter prepareJMX(MetricRegistry registry) {
        return JmxReporter.forRegistry(registry).build();
    }

}
