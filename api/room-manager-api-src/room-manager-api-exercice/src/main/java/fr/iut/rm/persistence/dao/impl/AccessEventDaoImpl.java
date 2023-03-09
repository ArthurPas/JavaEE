package fr.iut.rm.persistence.dao.impl;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.persist.Transactional;
import fr.iut.rm.persistence.dao.AccessEventDao;
import fr.iut.rm.persistence.domain.AccessEvent;
import fr.iut.rm.persistence.domain.AccessEvent_;
import fr.iut.rm.persistence.domain.Room_;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Provider;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Implementation of {@link AccessEventDao}
 */
@Singleton
public class AccessEventDaoImpl implements AccessEventDao {

    /**
     * Logger
     */
    private static final Logger logger = LoggerFactory.getLogger(AccessEventDaoImpl.class);

    /**
     * Entity Manager used to perform database operations
     */
    @Inject
    private Provider<EntityManager> em;

    /**
     * Save an access event in db.
     *
     * @param event object to persist
     */
    @Override
    @Transactional
    public void saveOrUpdate(final AccessEvent event) {
        this.em.get().persist(event);
        logger.debug("AccessEvent '{}' of room '{}' saved", event.getType(), event.getRoom().getName());
    }

    @Override
    public AccessEvent get(final long id) {
        final AccessEvent entity = this.em.get().find(AccessEvent.class, id);
        logger.debug("AccessEvent with id {} {}", id, entity == null ? "not found" : "found");
        return entity;
    }

    @Transactional
    @Override
    public void delete(final AccessEvent event) {
        logger.debug("Removing access event with id {}", event == null ? "null" : event.getId());
        this.em.get().remove(event);
    }

    @Override
    public List<AccessEvent> findBy(final Long roomId, final String userName) {
        final String query = "from " + AccessEvent.class.getName();

        final Map<String, Object> queryParameters = new HashMap<>();
        String extraQuery = "";
        if (roomId != null || userName != null) {
            if (roomId != null) {
                extraQuery += " where ";
                extraQuery += AccessEvent_.room.getName() + "." + Room_.id.getName() + " = :roomId";
                queryParameters.put("roomId", roomId);

            }
            if (userName != null) {
                extraQuery += extraQuery.isEmpty() ? " where " : " and ";
                extraQuery += AccessEvent_.userName.getName() + " = :userName";
                queryParameters.put("userName", userName);
            }
        }

        final Query jpql = em.get().createQuery(query + extraQuery);

        // Bind defined query parameters
        for (Map.Entry<String, Object> parameter : queryParameters.entrySet()) {
            jpql.setParameter(parameter.getKey(), parameter.getValue());
        }

        // execute query
        List<AccessEvent> events = jpql.getResultList();

        logger.debug("{} events found", events.size());
        return events;
    }

}
