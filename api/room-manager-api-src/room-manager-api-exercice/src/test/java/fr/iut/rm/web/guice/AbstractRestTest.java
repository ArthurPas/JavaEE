package fr.iut.rm.web.guice;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceFilter;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.util.Modules;
import com.sun.jersey.guice.JerseyServletModule;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;
import com.sun.jersey.test.framework.AppDescriptor;
import com.sun.jersey.test.framework.WebAppDescriptor;
import fr.iut.rm.MainModule;
import fr.iut.rm.web.FastJerseyTest;
import fr.iut.rm.web.WebModule;
import fr.iut.rm.web.providers.GsonJsonProvider;
import org.aopalliance.intercept.Interceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServlet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class used for web test in order to obtain injector
 */
public abstract class AbstractRestTest extends FastJerseyTest {

    /**
     * Logger
     */
    private static final Logger logger = LoggerFactory.getLogger(AbstractRestTest.class);

    protected static Injector injector;


    @Override
    protected AppDescriptor configure() {

        final List<Interceptor> interceptors = new ArrayList<Interceptor>();

        injector = Guice.createInjector(Modules.override(new MainModule(), new WebModule(interceptors)).with(new WebTestModule()), new JerseyServletModule() {
            @Override
            protected void configureServlets() {
                // Route all requests through GuiceContainer
                serve("/*").with((Class<? extends HttpServlet>) GuiceContainer.class, initParams());
            }
        });

        // Performs interceptor member injection
        for (Interceptor interceptor : interceptors) {
            injector.injectMembers(interceptor);
        }


        WebAppDescriptor wad = new WebAppDescriptor.Builder().contextListenerClass(GuiceTestConfig.class)
                .filterClass(GuiceFilter.class)
                .servletPath("/")
                .build();

        // Register the Gson provider to the client configuration
        wad.getClientConfig().getClasses().add(GsonJsonProvider.class);

        return wad;

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

    public static class GuiceTestConfig extends GuiceServletContextListener {

        @Override
        public Injector getInjector() {
            return injector;
        }
    }
}
