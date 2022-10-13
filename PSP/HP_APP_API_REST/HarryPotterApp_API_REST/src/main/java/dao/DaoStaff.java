package dao;

import common.config.Configuration;
import dao.modelo.ResponseCharItem;
import dao.retrofit.llamadas.HarryPotterAPI;
import domain.modelo.HarryPotterCharacter;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import retrofit2.Response;

import java.io.IOException;
import java.util.List;

public class DaoStaff {

    private Configuration configuration;

    private HarryPotterAPI harryPotterAPI;

    @Inject
    public DaoStaff(Configuration configuration, HarryPotterAPI harryPotterAPI) {
        this.configuration = configuration;
        this.harryPotterAPI = harryPotterAPI;
    }

    public Single<Either<String, List<HarryPotterCharacter>>> retrofitCallAllStaffSingleAsync(){
        return harryPotterAPI.getAllStaffSingleAsync()
                .map(r -> {
                    List<HarryPotterCharacter> myStaffList = r.stream().map(studentCharacter -> new HarryPotterCharacter(studentCharacter.getName(), studentCharacter.getSpecies(), studentCharacter.getHouse(), studentCharacter.getDateOfBirth(), studentCharacter.getEyeColour(),
                            studentCharacter.getHairColour(), studentCharacter.getPatronus(), studentCharacter.getImage())).toList();
                    return Either.right(myStaffList).mapLeft(Object::toString);
                })
                .subscribeOn(Schedulers.io())
                .onErrorReturn(e -> Either.left("There was an error in the request" + e.getMessage()));
    }

    public Either<String, List<HarryPotterCharacter>> retrofitCallAllStaff() {

        Either<String, List<HarryPotterCharacter>> response = null;

        Response<List<ResponseCharItem>> r;

        try {
            r = harryPotterAPI.getAllStaff().execute();

            if (r.isSuccessful()) {

                List<ResponseCharItem> staffList = r.body();
                List<HarryPotterCharacter> myStaffList;
                if (staffList != null) {

                    myStaffList = staffList.stream()
                            .map(staffCharacter -> new HarryPotterCharacter(staffCharacter.getName(), staffCharacter.getSpecies(), staffCharacter.getHouse(), staffCharacter.getDateOfBirth(), staffCharacter.getEyeColour(), staffCharacter.getHairColour(), staffCharacter.getPatronus(), staffCharacter.getImage())).toList();

                    response = Either.right(myStaffList);
                } else {
                    response = Either.left(r.message());
                }
            }

        } catch (IOException e) {
            response = Either.left(e.getMessage());
        }
        return response;
    }
}
