package domain.modelo;

import lombok.Data;

@Data
public class Spell {
    private String name;
    private String description;

    public Spell(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
