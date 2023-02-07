package fr.iut.rm.dao.guice;

import com.google.inject.AbstractModule;
import fr.iut.rm.dao.PersistenceStarter;

/**
 * Guice Module used for test (only)
 */
public class TestModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(PersistenceStarter.class);

    }
}
