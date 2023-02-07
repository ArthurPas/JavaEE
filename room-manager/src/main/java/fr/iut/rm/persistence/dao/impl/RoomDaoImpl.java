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

    /**
     * @param room room to persist
     */
    @Override
    @Transactional
    public void saveOrUpdate(final Room room) {
        this.em.get().persist(room);
        logger.debug("Room '{}' saved", room.getName());
    }

    /**
     * @return the entire db room list
     */
    @Override
    @Transactional
    public List<Room> findAll() {
        StringBuilder query = new StringBuilder("from ");
        query.append(Room.class.getName());
        List<Room> rooms = em.get().createQuery(query.toString()).getResultList();
        logger.debug("{} rooms found", rooms);
        return rooms;
    }


    /**
     * @param name  of the room
     * @return the corresponding room or null if nothing found
     */
    @Override
    @Transactional
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
