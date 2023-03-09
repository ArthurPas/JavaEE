package fr.iut.rm.web.api.model;

/**
 * Defines a room for the API layer (Room Visual Object)
 */
public class RoomVO {
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
