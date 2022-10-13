package domain.servicios;

import dao.DaoStudents;
import domain.modelo.HarryPotterCharacter;
import io.vavr.control.Either;
import jakarta.inject.Inject;

import java.util.List;

public class StudentServices {

    private DaoStudents daoStudents;

    @Inject
    public StudentServices(DaoStudents daoStudents) {
        this.daoStudents = daoStudents;
    }

    public Either<String, List<HarryPotterCharacter>> getCharacters() {
        return daoStudents.retrofitCallAllStudents();
    }


}
