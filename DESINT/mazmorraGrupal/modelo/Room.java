package modelo;

import java.util.List;

public class Room {
    private String id;
    private List<Door> doors;
    private String description;

    public Room(String id, List<Door> doors, String description) {
        this.id = id;
        this.doors = doors;
        this.description = description;
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
}
