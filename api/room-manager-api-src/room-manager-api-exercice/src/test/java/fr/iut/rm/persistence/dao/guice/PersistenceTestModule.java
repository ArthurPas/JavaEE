package fr.iut.rm.persistence.dao.guice;

import com.google.inject.AbstractModule;
import fr.iut.rm.persistence.dao.PersistenceStarter;

/**
 * Guice Module used for test (only)
 */
public class PersistenceTestModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(PersistenceStarter.class);

    }
}
