package fr.iut.rm.persistence.dao;

import fr.iut.rm.persistence.domain.AccessEvent;
import fr.iut.rm.persistence.domain.EntryLeave;
import fr.iut.rm.persistence.domain.Room;

import java.util.List;

public interface AccessEventDao {

    /**
     * List all the access of a room
     * @param name the name of the room
     * @return the access of the room by this person
     */
    List<AccessEvent> findAccessByRoom(String name);
    /**
     * List all the access of a person
     * @param name the name of the person
     * @return the access of the room by this person
     */
    List<AccessEvent> findAccessByPerson(String name);

    void saveOrUpdate(AccessEvent accessEvent);
}
