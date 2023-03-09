package fr.iut.rm.web.api.model;

/**
 * Defines a detailed room for the API layer (Room Visual Object)
 */
public class RoomDetailsVO {
    /**
     * Room's identifier.
     */
    private long id;
    /**
     * Room's name.
     */
    private String name;
    /**
     * Room's description.
     */
    private String description;
    /**
     * The number of access events associated to the room.
     */
    private int accessEventsCount;

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
     * Gets accessEventsCount.
     *
     * @return value of accessEventsCount
     */
    public int getAccessEventsCount() {
        return accessEventsCount;
    }

    /**
     * Sets new accessEventsCount.
     *
     * @param accessEventsCount new value of accessEventsCount
     */
    public void setAccessEventsCount(final int accessEventsCount) {
        this.accessEventsCount = accessEventsCount;
    }

    /**
     * Gets description.
     *
     * @return value of description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets new description.
     *
     * @param description new value of description
     */
    public void setDescription(final String description) {
        this.description = description;
    }

    /**
     * Gets name.
     *
     * @return value of name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets new name.
     *
     * @param name new value of name
     */
    public void setName(final String name) {
        this.name = name;
    }
}
