package domain.modelo;

import lombok.Data;

@Data
public class Article {

    private int id;
    private String name;
    private int typeId;
    private int newspaperId;

    public Article(String name, int id, int newspaperId, int typeId) {
        this.name = name;
        this.id = id;
        this.newspaperId = newspaperId;
        this.typeId = typeId;
    }
}
