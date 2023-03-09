package fr.iut.rm.persistence.dao.impl;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.persist.Transactional;
import fr.iut.rm.persistence.dao.RoomDao;
import fr.iut.rm.persistence.domain.Room;
import fr.iut.rm.persistence.domain.Room_;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Provider;
import javax.persistence.EntityManager;
import java.util.List;

/**
 * Implementation of {@link fr.iut.rm.persistence.dao.RoomDao}
 */
@Singleton
public class RoomDaoImpl implements RoomDao {

    /**
     * Logger
     */
    private static final Logger logger = LoggerFactory.getLogger(RoomDaoImpl.class);

    /**
     * Entity Manager used to perform database operations
     */
    @Inject
    private Provider<EntityManager> em;

    @Override
    @Transactional
    public void saveOrUpdate(final Room room) {
        this.em.get().persist(room);
        logger.debug("Room '{}' saved", room.getName());
    }

    @Override
    public Room get(final long id) {
        final Room entity = this.em.get().find(Room.class, id);
        logger.debug("Room with id {} {}", id, entity == null ? "not found" : "found");
        return entity;
    }


    @Override
    @Transactional
    public List<Room> findAll() {
        StringBuilder query = new StringBuilder("from ");
        query.append(Room.class.getName());
        List<Room> rooms = em.get().createQuery(query.toString()).getResultList();
        logger.debug("{} rooms found", rooms);
        return rooms;
    }


    @Override
    public List<Room> searchByName(final String name) {
        String query = "from " + Room.class.getName() +
                " where " + Room_.name.getName() + " LIKE CONCAT('%',:name,'%')";

        final List<Room> results = em.get().createQuery(query)
                .setParameter("name", name)
                .getResultList();

        logger.debug("{} rooms matching name query {}", results.size(), name);
        return results;
    }


    @Override
    public Room findByName(final String name) {
        StringBuilder query = new StringBuilder("from ");
        query.append(Room.class.getName()).append(" as room");
        query.append(" where room.").append(Room_.name.getName()).append(" = :name");

        List<Room> resultList = em.get().createQuery(query.toString()).setParameter("name", name).getResultList();

        if (resultList.size() > 0) {
            logger.debug("Room  with name '{}' found", name);
            return (Room) resultList.get(0);
        }
        logger.debug("No room with name '{}' found", name);
        return null;
    }

}
