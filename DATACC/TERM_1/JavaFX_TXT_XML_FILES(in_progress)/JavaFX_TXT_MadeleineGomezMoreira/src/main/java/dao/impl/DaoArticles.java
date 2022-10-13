package dao.impl;

import common.config.Configuration;
import domain.modelo.Article;
import domain.modelo.Newspaper;
import io.vavr.control.Either;
import jakarta.inject.Inject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DaoArticles {

    private Configuration configuration;

    @Inject
    public DaoArticles(Configuration configuration) {
        this.configuration = configuration;
    }

    public Either<String, List<Article>> loadArticles(){

        Either<String, List<Article>> response;
        List<Article> ArticlesList = new ArrayList<>();
        List<String> articlesString;
        try {
            articlesString = Files.readAllLines(Path.of(configuration.getPathArticles()));
            articlesString.forEach(s -> {
                String[] fields = s.split(";");
                Article newspaper = new Article(fields[0],Integer.parseInt(fields[1]), Integer.parseInt(fields[2]),Integer.parseInt(fields[3]));
                ArticlesList.add(newspaper);

            });
            response = Either.right(ArticlesList);
        } catch (IOException e) {
            response = Either.left(e.getMessage());
        } return response;
    }
}
