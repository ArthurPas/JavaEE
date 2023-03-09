package fr.iut.rm.persistence.dao;

import fr.iut.rm.persistence.domain.AccessEvent;

import java.util.List;

/**
 * Data Access Object of {@link AccessEvent}
 */
public interface AccessEventDao {

    /**
     * Persists an access event.
     *
     * @param event object to persist
     */
    void saveOrUpdate(AccessEvent event);

    /**
     * Retrieve an access event by its identifier.
     *
     * @param id access event identifier
     * @return access event or null if not found
     */
    AccessEvent get(long id);

    /**
     * Search access events.
     *
     * @param roomId   filter on a room identifier
     * @param userName filter on a user name
     * @return access events list that match the given filters
     */
    List<AccessEvent> findBy(Long roomId, String userName);

    /**
     * Remove access event entity from db
     *
     * @param event entity to delete
     */
    void delete(AccessEvent event);
}
