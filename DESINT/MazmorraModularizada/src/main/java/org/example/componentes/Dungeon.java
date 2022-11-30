package org.example.componentes;


import java.util.List;

public class Dungeon {

    private List<Room> rooms;

    public Dungeon() {
    }

    public Dungeon(List<Room> rooms) {
        this.rooms = rooms;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public Room getRoom(String id) {
        for (Room room : rooms) {
            if (room.getId().equals(id)) {
                return room;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "Dungeon{" +
                "rooms=" + rooms +
                '}';
    }
}
