package fr.iut.rm.web;

import com.google.common.base.Preconditions;
import com.google.inject.AbstractModule;
import fr.iut.rm.web.api.RoomResource;
import fr.iut.rm.web.providers.GsonJsonProvider;
import org.aopalliance.intercept.Interceptor;

import java.util.List;

/**
 * Main web binding class
 */
public class WebModule extends AbstractModule {

    /**
     * Guice interceptors whose members must be injected after bindings
     */
    private final List<Interceptor> interceptors;

    /**
     * Constructs web module
     *
     * @param interceptors interceptor list
     */
    public WebModule(final List<Interceptor> interceptors) {
        Preconditions.checkArgument(interceptors != null);
        this.interceptors = interceptors;
    }

    @Override
    protected final void configure() {
        bind(GsonJsonProvider.class);

        // API resources
        bind(RoomResource.class);
    }
}

