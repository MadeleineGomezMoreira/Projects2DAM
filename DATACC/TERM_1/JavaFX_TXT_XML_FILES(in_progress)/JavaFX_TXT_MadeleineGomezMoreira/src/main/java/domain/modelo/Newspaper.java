package domain.modelo;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Newspaper {

    private String name;
    private int newspaperId;
    private LocalDate releaseDate;

    public Newspaper(String name, int newspaperId, LocalDate releaseDate) {
        this.name = name;
        this.newspaperId = newspaperId;
        this.releaseDate = releaseDate;
    }
}
