package org.example.componentes;


import java.util.List;

public class Room {

    private String id;

    private String description;

    private List<Door> doors;

    public Room(String id, String description, List<Door> doors) {
        this.id = id;
        this.description = description;
        this.doors = doors;
    }

    public Room() {
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Door> getDoors() {
        return doors;
    }

    public void setDoors(List<Door> doors) {
        this.doors = doors;
    }

    @Override
    public String toString() {
        return "Room{" +
                "id='" + id + '\'' +
                ", description='" + description + '\'' +
                ", doors=" + doors +
                '}';
    }
}
