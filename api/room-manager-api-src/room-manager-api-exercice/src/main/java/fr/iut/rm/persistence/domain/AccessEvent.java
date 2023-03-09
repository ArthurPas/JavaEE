package fr.iut.rm.persistence.domain;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Defines access events input/output of a room
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

    /**
     * Event target user name.
     */
    @Column
    private String userName;

    /**
     * Event type in / out
     */
    @Column
    @Enumerated(EnumType.STRING)
    private EventType type;

    /**
     * Event date time
     */
    @Column
    private LocalDateTime dateTime;


    /**
     * Associated room
     */
    @ManyToOne
    private Room room;

    /**
     * Gets id.
     *
     * @return value of id
     */
    public long getId() {
        return id;
    }

    /**
     * Sets new id.
     *
     * @param id new value of id
     */
    public void setId(final long id) {
        this.id = id;
    }

    /**
     * Gets userName.
     *
     * @return value of userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Sets new userName.
     *
     * @param userName new value of userName
     */
    public void setUserName(final String userName) {
        this.userName = userName;
    }

    /**
     * Gets type.
     *
     * @return value of type
     */
    public EventType getType() {
        return type;
    }

    /**
     * Sets new type.
     *
     * @param type new value of type
     */
    public void setType(final EventType type) {
        this.type = type;
    }

    /**
     * Gets dateTime.
     *
     * @return value of dateTime
     */
    public LocalDateTime getDateTime() {
        return dateTime;
    }

    /**
     * Sets new dateTime.
     *
     * @param dateTime new value of dateTime
     */
    public void setDateTime(final LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    /**
     * Gets room.
     *
     * @return value of room
     */
    public Room getRoom() {
        return room;
    }

    /**
     * Sets new room.
     *
     * @param room new value of room
     */
    public void setRoom(final Room room) {
        this.room = room;
    }
}
