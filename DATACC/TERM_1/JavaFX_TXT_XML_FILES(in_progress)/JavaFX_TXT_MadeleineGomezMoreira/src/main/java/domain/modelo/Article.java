package domain.modelo;

import lombok.Data;

@Data
public class Article {

    private String title;
    private int articleId;
    private int newspaperId;
    private int typeId;

    public Article(String title, int articleId, int newspaperId, int typeId) {
        this.title = title;
        this.articleId = articleId;
        this.newspaperId = newspaperId;
        this.typeId = typeId;
    }
}
