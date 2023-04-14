package domain.modelo;

import java.time.LocalDate;

public class Reader {

    private int id;
    private String name;
    private LocalDate birth;

    public Reader(String name, int id, LocalDate birth) {
        this.name = name;
        this.id = id;
        this.birth = birth;
    }
}
