package com.drazzib.metrics;

import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.Timer;
import com.codahale.metrics.servlets.MetricsServlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "helloServlet", urlPatterns = {"/"})
public class HelloWorldServlet extends HttpServlet {

    private Timer requestsTimer;
    private MetricRegistry registry;

    @Override
    public void init(ServletConfig config) throws ServletException {
        getRegistry(config);
        this.requestsTimer = this.registry.timer("hello-requests");
    }

    private void getRegistry(ServletConfig config) {
        final ServletContext context = config.getServletContext();
        this.registry = (MetricRegistry) context.getAttribute(MetricsServlet.METRICS_REGISTRY);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Timer.Context time = requestsTimer.time();
        try {
            response.getWriter().write("Hello World!");
        } finally {
            time.stop();
        }
    }
}
