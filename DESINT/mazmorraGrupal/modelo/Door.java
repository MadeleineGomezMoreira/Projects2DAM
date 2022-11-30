package modelo;

public class Door {
    private String name;
    private String dest;

    public Door(String name, String description) {
        this.name = name;
        this.dest = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDest() {
        return dest;
    }

    public void setDest(String dest) {
        this.dest = dest;
    }
}
