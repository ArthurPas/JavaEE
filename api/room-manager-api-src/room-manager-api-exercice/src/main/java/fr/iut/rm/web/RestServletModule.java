package fr.iut.rm.web;

import com.google.inject.persist.PersistFilter;
import com.google.inject.persist.jpa.JpaPersistModule;
import com.sun.jersey.guice.JerseyServletModule;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServlet;
import java.util.HashMap;
import java.util.Map;

/**
 * Servlet module used to serve REST resources
 */
public class RestServletModule extends JerseyServletModule {

    /**
     * Logger
     */
    private static final Logger logger = LoggerFactory.getLogger(RestServletModule.class);

    @Override
    protected void configureServlets() {
        logger.debug("Configuration started");

        //JPA management
        install(new JpaPersistModule("room-manager"));
        filter("/*").through(PersistFilter.class);

        // Route all requests through GuiceContainer
        serve("/*").with((Class<? extends HttpServlet>) GuiceContainer.class, initParams());

        logger.debug("Configuration done");
    }


    /**
     * Creates init parameters
     *
     * @return parameter map
     */
    private Map<String, String> initParams() {
        Map<String, String> ret = new HashMap<String, String>();

        ret.put("com.sun.jersey.spi.container.ContainerRequestFilters", "com.sun.jersey.api.container.filter.LoggingFilter");
        ret.put("com.sun.jersey.spi.container.ContainerResponseFilters", "com.sun.jersey.api.container.filter.LoggingFilter");
        logger.debug("HTTP log enabled");

        return ret;
    }
}
