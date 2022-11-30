package org.example.componentes;


public class Door {

    private String name;

    private String destination;

    public Door() {
    }

    public Door(String name, String destination) {
        this.name = name;
        this.destination = destination;
    }

    public String getName() {
        return name;
    }

    public String getDestination() {
        return destination;
    }

    @Override
    public String toString() {
        return "Door{" +
                "name='" + name + '\'' +
                ", destination='" + destination + '\'' +
                '}';
    }
}
