package domain.modelo;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Newspaper {

    private int id;
    private String name;
    private LocalDate releaseDate;

    public Newspaper(String name, int id, LocalDate releaseDate) {
        this.name = name;
        this.id = id;
        this.releaseDate = releaseDate;
    }
}
