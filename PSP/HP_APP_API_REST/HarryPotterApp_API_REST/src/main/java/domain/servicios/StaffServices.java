package domain.servicios;

import dao.DaoStaff;
import domain.modelo.HarryPotterCharacter;
import io.vavr.control.Either;
import jakarta.inject.Inject;

import java.util.List;

public class StaffServices {

    private DaoStaff daoStaff;

    @Inject
    public StaffServices(DaoStaff daoStaff) {
        this.daoStaff = daoStaff;
    }

    public Either<String, List<HarryPotterCharacter>> getStaff() {
        return daoStaff.retrofitCallAllStaff();
    }
}
