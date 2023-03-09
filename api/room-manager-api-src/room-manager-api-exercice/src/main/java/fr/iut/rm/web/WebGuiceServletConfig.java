package fr.iut.rm.web;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import fr.iut.rm.MainModule;
import org.aopalliance.intercept.Interceptor;

import java.util.ArrayList;
import java.util.List;

/**
 * The GuiceServletContextListener is used to bootstrap Guice when the servlet context is initialized.
 */
public class WebGuiceServletConfig extends GuiceServletContextListener {


    /**
     * Current injector
     */
    private Injector currentInjector;

    @Override
    protected final Injector getInjector() {
        final List<Interceptor> interceptors = new ArrayList<Interceptor>();

        this.currentInjector = Guice.createInjector(new MainModule(), new WebModule(interceptors), new RestServletModule());

        // Performs interceptor member injection
        for (Interceptor interceptor : interceptors) {
            this.currentInjector.injectMembers(interceptor);
        }

        return this.currentInjector;
    }
}
