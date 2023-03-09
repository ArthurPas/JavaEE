package fr.iut.rm.web.api.model;

/**
 * Defines a room to create for the API layer (Room Visual Object)
 */
public class SaveRoomVO {
    // TODO add necessary fields
    String name;
    String description;

    public void setTheName(String theName) {
        this.name = name;
    }

    public void setTheDescription(String theDescription) {
        this.description = theDescription;
    }

    public String getTheName() {
        return name;
    }

    public String getTheDescription() {
        return description;
    }
}
