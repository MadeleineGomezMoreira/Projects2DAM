package domain.services;

import dao.impl.DaoNewspapers;
import domain.modelo.Newspaper;
import io.vavr.control.Either;
import jakarta.inject.Inject;

import java.util.List;

public class NewspaperServices {

    private DaoNewspapers daoNewspapers;

    @Inject
    public NewspaperServices(DaoNewspapers daoNewspapers) {
        this.daoNewspapers = daoNewspapers;
    }


    public Either<String, List<Newspaper>> loadNewspapers() {
        return daoNewspapers.loadNewspapers();
    }


}