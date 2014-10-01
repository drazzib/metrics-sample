package com.drazzib.metrics;

import com.codahale.metrics.health.HealthCheckRegistry;
import com.codahale.metrics.servlets.HealthCheckServlet;

import javax.servlet.annotation.WebListener;

@WebListener
public class HealthCheckCListener extends HealthCheckServlet.ContextListener {

    @Override
    protected HealthCheckRegistry getHealthCheckRegistry() {
        HealthCheckRegistry registry = new HealthCheckRegistry();
        return registry;
    }

}