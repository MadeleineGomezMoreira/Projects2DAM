package domain.servicios;

import dao.DaoHouses;
import domain.modelo.HarryPotterCharacter;
import io.vavr.control.Either;
import jakarta.inject.Inject;

import java.util.List;

public class HouseServices {

    private DaoHouses daoHouses;

    @Inject
    public HouseServices(DaoHouses daoHouses) {
        this.daoHouses = daoHouses;
    }

    public Either<String, List<HarryPotterCharacter>> getGryffindor() {
        return daoHouses.retrofitCallGryffindor();
    }

    public Either<String, List<HarryPotterCharacter>> getSlytherin() {
        return daoHouses.retrofitCallSlytherin();
    }

    public Either<String, List<HarryPotterCharacter>> getHufflepuff() {
        return daoHouses.retrofitCallHufflePuff();
    }

    public Either<String, List<HarryPotterCharacter>> getRavenclaw() {
        return daoHouses.retrofitCallRavenclaw();
    }

}
