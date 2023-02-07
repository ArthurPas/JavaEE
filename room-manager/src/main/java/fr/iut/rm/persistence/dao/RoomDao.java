package fr.iut.rm.persistence.dao;

import fr.iut.rm.persistence.domain.Room;

import java.util.List;

/**
 * Data Access Object of {@link fr.iut.rm.persistence.domain.Room}
 */
public interface RoomDao {

    /**
     * Persists room.
     *
     * @param room object to persist
     */
    void saveOrUpdate(Room room);

    /**
     * @return the full room list
     */
    List<Room> findAll();

    /**
     * @param name the searched name, must be case sensitive and exact match.
     *             Null name returns null result
     * @return the room or null if nothing found
     */
    Room findByName(final String name);

}
