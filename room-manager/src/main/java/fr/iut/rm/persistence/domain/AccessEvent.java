package fr.iut.rm.persistence.domain;

import javax.persistence.*;
import java.util.Date;

/**
 * A classic room
 */
@Entity
@Table(name = "access_event")
public class AccessEvent {
    /**
     * sequence generated id
     */
    @Id
    @GeneratedValue
    private long id;

    @ManyToOne
    private Room theRoom;
    /**
     * Access name
     */
    private String accessName;

    /**
     * Room's descritpion
     */
    @Column
    private EntryLeave entryLeave;

    /**
     * Room's date
     */
    @Column
    private Date date;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Room getTheRoom() {
        return theRoom;
    }

    public void setTheRoom(Room theRoom) {
        this.theRoom = theRoom;
    }

    public String getAccessName() {
        return accessName;
    }

    public void setAccessName(String accessName) {
        this.accessName = accessName;
    }

    public EntryLeave getEntryLeave() {
        return entryLeave;
    }

    public void setEntryLeave(EntryLeave entryLeave) {
        this.entryLeave = entryLeave;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    public AccessEvent(){

    }
}
