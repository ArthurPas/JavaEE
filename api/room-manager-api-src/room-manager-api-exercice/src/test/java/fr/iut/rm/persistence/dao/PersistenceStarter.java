package fr.iut.rm.persistence.dao;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.persist.PersistService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Used to start and init persistence layer
 */
@Singleton
public class PersistenceStarter {

    private static final Logger logger = LoggerFactory.getLogger(PersistenceStarter.class);
    @Inject
    private PersistService persistService;

    public void start() throws IOException {
        logger.debug("Starting persistence service ({})", this.hashCode());
        persistService.start();
        logger.debug("Persistence service started");
        // At this point JPA is started and ready.
    }

    public void stop() {
        logger.debug("Stopping persistence service ({})", this.hashCode());
        persistService.stop();
        logger.debug("Persistence service stopped");
    }
}
