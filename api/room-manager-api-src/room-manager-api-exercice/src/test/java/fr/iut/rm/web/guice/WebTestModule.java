package fr.iut.rm.web.guice;

import com.google.inject.AbstractModule;
import com.google.inject.persist.UnitOfWork;
import fr.iut.rm.Configuration;
import fr.iut.rm.persistence.dao.AccessEventDao;
import fr.iut.rm.persistence.dao.RoomDao;

import static org.mockito.Mockito.mock;

/**
 * Guice Module used for test (only)
 */
public class WebTestModule extends AbstractModule {

    @Override
    protected void configure() {

        // Disable configuration bean
        bind(Configuration.class).toInstance(mock(Configuration.class));

        // Disable DAOs
        bind(RoomDao.class).toInstance(mock(RoomDao.class));
        bind(AccessEventDao.class).toInstance(mock(AccessEventDao.class));

        // Disable Unit of work (aka entity manager transactions)
        bind(UnitOfWork.class).toInstance(mock(UnitOfWork.class));
    }
}
