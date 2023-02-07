package fr.iut.rm.control;

import com.google.inject.Inject;
import com.google.inject.persist.UnitOfWork;
import fr.iut.rm.persistence.dao.AccessEventDao;
import fr.iut.rm.persistence.dao.RoomDao;
import fr.iut.rm.persistence.domain.AccessEvent;
import fr.iut.rm.persistence.domain.EntryLeave;
import fr.iut.rm.persistence.domain.Room;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by cjohnen on 02/02/17.
 */
public class ControlRoom {
    /**
     * Logger
     */
    private static final Logger logger = LoggerFactory.getLogger(ControlRoom.class);

    /**
     * Unit of work is used to drive DB Connection
     */
    @Inject
    UnitOfWork unitOfWork;

    /**
     * Data Access Object for rooms
     */
    @Inject
    RoomDao roomDao;
    @Inject
    AccessEventDao accessEvent;
    /*
     * * Displays all the rooms content in DB
     */
    public void showRooms() {
        unitOfWork.begin();

        List<Room> rooms = roomDao.findAll();
        if (rooms.isEmpty()) {
            System.out.println("No room");
        } else {
            System.out.println("Rooms :");
            System.out.println("--------");
            for (Room room : rooms) {
                System.out.println(String.format("   [%d], name '%s', description '%s'", room.getId(), room.getName(), room.getDescription()));
            }
        }

        unitOfWork.end();
    }

    /**
     * Creates a room in DB
     *
     * @param name the name of the room
     */
    public void createRoom(final String name, String description) {
        unitOfWork.begin();
        if (roomDao.findByName(name) != null) {
            Room room = new Room();
            room.setName(name);
            room.setDescription(description);
            roomDao.saveOrUpdate(room);
        } else {
            logger.warn("Nom déjà présent");
        }
        unitOfWork.end();
    }

    /**
     * Delete a room in DB
     *
     * @param name the name of the room
     */
    public void deleteRoom(final String name) {
        unitOfWork.begin();
        roomDao.removeRoom(name);
        unitOfWork.end();
    }

    public void addAccess(String roomName, String namePerson, EntryLeave status) {
        Room room = roomDao.findByName(roomName);
        if (room!= null) {
            AccessEvent ae = new AccessEvent();
            ae.setAccessName(namePerson);
            ae.setEntryLeave(status);
            ae.setTheRoom(room);
            ae.setDate(new Date());
            accessEvent.saveOrUpdate(ae);
        } else {
            logger.warn("room not found");
        }
    }
    /*
     * * Displays all the access by the name of the room
     */
    public void showAccessByRoom(String roomName) {
        unitOfWork.begin();

        List<AccessEvent> ae = accessEvent.findAccessByRoom(roomName);
        if (ae.isEmpty()) {
            System.out.println("No access");
        } else {
            System.out.println("Acces Event :");
            System.out.println("--------");
            for (AccessEvent access : ae) {
                System.out.println(String.format("   [%d], name '%s',room '%s', entry or leave '%s', date '%s'", access.getId(),
                        access.getAccessName(),access.getTheRoom().getName(), access.getEntryLeave(),access.getDate().toString()));
            }
        }

        unitOfWork.end();
    }
    /*
     * * Displays all the access by the name of the person
     */
    public void showAccessByPerson(String personName) {
        unitOfWork.begin();

        List<AccessEvent> ae = accessEvent.findAccessByPerson(personName);
        if (ae.isEmpty()) {
            System.out.println("No access");
        } else {
            System.out.println("Acces Event :");
            System.out.println("--------");
            for (AccessEvent access : ae) {
                System.out.println(String.format("   [%d], name '%s',room '%s', entry or leave '%s', date '%s'", access.getId(),
                        access.getAccessName(),access.getTheRoom().getName(), access.getEntryLeave(),access.getDate().toString()));
            }
        }

        unitOfWork.end();
    }
}
