package fr.iut.rm;

import com.google.inject.AbstractModule;
import fr.iut.rm.persistence.dao.AccessEventDao;
import fr.iut.rm.persistence.dao.RoomDao;
import fr.iut.rm.persistence.dao.impl.AccessEventDaoImpl;
import fr.iut.rm.persistence.dao.impl.RoomDaoImpl;
import fr.iut.rm.persistence.domain.AccessEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Core collection of bindings
 */
public class MainModule extends AbstractModule {

    /**
     * Logger
     */
    private static final Logger logger = LoggerFactory.getLogger(MainModule.class);

    @Override
    protected final void configure() {
        logger.debug("MainModule configuration started");
        bind(App.class);
        bind(RoomDao.class).to(RoomDaoImpl.class);
        bind(AccessEventDao.class).to(AccessEventDaoImpl.class);
        logger.debug("MainModule configuration done");
    }

}
