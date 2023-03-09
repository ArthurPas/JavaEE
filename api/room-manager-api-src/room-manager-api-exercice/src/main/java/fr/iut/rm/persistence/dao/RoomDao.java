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
     * Retrieve a room by its identifier.
     *
     * @param id room identifier
     * @return room or null if not found
     */
    Room get(long id);

    /**
     * @return the full room list
     */
    List<Room> findAll();

    /**
     * Search rooms that match the given name query
     *
     * @param name name query (case sensitive)
     * @return room entities
     */
    List<Room> searchByName(String name);

    /**
     * @param name the searched name, must be case sensitive and exact match.
     *             Null name returns null result
     * @return the room or null if nothing found
     */
    Room findByName(String name);

}
