package fr.iut;

public class Room {
    String name;
    int occupation;
    int capacity;

    public Room(String name, int occupation, int capacity) {
        this.name = name;
        this.occupation = occupation;
        this.capacity = capacity;
    }

    public String getName() {
        return name;
    }

    public int getOccupation() {
        return occupation;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOccupation(int occupation) {
        this.occupation = occupation;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
