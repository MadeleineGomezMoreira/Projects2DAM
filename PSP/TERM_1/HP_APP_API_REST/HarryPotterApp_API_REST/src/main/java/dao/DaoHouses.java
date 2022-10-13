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

public class DaoHouses {

    private Configuration configuration;

    private HarryPotterAPI harryPotterAPI;

    @Inject
    public DaoHouses(Configuration configuration, HarryPotterAPI harryPotterAPI) {
        this.configuration = configuration;
        this.harryPotterAPI = harryPotterAPI;
    }

    public Single<Either<String, List<HarryPotterCharacter>>> retrofitCallGryffindorSingleAsync(){
        return harryPotterAPI.getGryffindorSingleAsync()
                .map(r -> {
                    List<HarryPotterCharacter> myStudentsList = r.stream().map(studentCharacter -> new HarryPotterCharacter(studentCharacter.getName(), studentCharacter.getSpecies(), studentCharacter.getHouse(), studentCharacter.getDateOfBirth(), studentCharacter.getEyeColour(),
                            studentCharacter.getHairColour(), studentCharacter.getPatronus(), studentCharacter.getImage())).toList();
                    return Either.right(myStudentsList).mapLeft(Object::toString);
                })
                .subscribeOn(Schedulers.io())
                .onErrorReturn(e -> Either.left("There was an error in the request" + e.getMessage()));
    }

    public Single<Either<String, List<HarryPotterCharacter>>> retrofitCallSlytherinSingleAsync(){
        return harryPotterAPI.getSlytherinSingleAsync()
                .map(r -> {
                    List<HarryPotterCharacter> myStudentsList = r.stream().map(studentCharacter -> new HarryPotterCharacter(studentCharacter.getName(), studentCharacter.getSpecies(), studentCharacter.getHouse(), studentCharacter.getDateOfBirth(), studentCharacter.getEyeColour(),
                            studentCharacter.getHairColour(), studentCharacter.getPatronus(), studentCharacter.getImage())).toList();
                    return Either.right(myStudentsList).mapLeft(Object::toString);
                })
                .subscribeOn(Schedulers.io())
                .onErrorReturn(e -> Either.left("There was an error in the request" + e.getMessage()));
    }

    public Single<Either<String, List<HarryPotterCharacter>>> retrofitCallRavenClawSingleAsync(){
        return harryPotterAPI.getRavenclawSingleAsync()
                .map(r -> {
                    List<HarryPotterCharacter> myStudentsList = r.stream().map(studentCharacter -> new HarryPotterCharacter(studentCharacter.getName(), studentCharacter.getSpecies(), studentCharacter.getHouse(), studentCharacter.getDateOfBirth(), studentCharacter.getEyeColour(),
                            studentCharacter.getHairColour(), studentCharacter.getPatronus(), studentCharacter.getImage())).toList();
                    return Either.right(myStudentsList).mapLeft(Object::toString);
                })
                .subscribeOn(Schedulers.io())
                .onErrorReturn(e -> Either.left("There was an error in the request" + e.getMessage()));
    }

    public Single<Either<String, List<HarryPotterCharacter>>> retrofitCallHufflepuffSingleAsync(){
        return harryPotterAPI.getHufflepuffSingleAsync()
                .map(r -> {
                    List<HarryPotterCharacter> myStudentsList = r.stream().map(studentCharacter -> new HarryPotterCharacter(studentCharacter.getName(), studentCharacter.getSpecies(), studentCharacter.getHouse(), studentCharacter.getDateOfBirth(), studentCharacter.getEyeColour(),
                            studentCharacter.getHairColour(), studentCharacter.getPatronus(), studentCharacter.getImage())).toList();
                    return Either.right(myStudentsList).mapLeft(Object::toString);
                })
                .subscribeOn(Schedulers.io())
                .onErrorReturn(e -> Either.left("There was an error in the request" + e.getMessage()));
    }

    public Either<String, List<HarryPotterCharacter>> retrofitCallGryffindor() {

        Either<String, List<HarryPotterCharacter>> response = null;

        Response<List<ResponseCharItem>> r;

        try {
            r = harryPotterAPI.getGryffindor().execute();

            if (r.isSuccessful()) {

                List<ResponseCharItem> houseList = r.body();
                List<HarryPotterCharacter> myHouseList;
                if (houseList != null) {

                    myHouseList = houseList.stream()
                            .map(staffCharacter -> new HarryPotterCharacter(staffCharacter.getName(), staffCharacter.getSpecies(), staffCharacter.getHouse(), staffCharacter.getDateOfBirth(), staffCharacter.getEyeColour(), staffCharacter.getHairColour(), staffCharacter.getPatronus(), staffCharacter.getImage())).toList();

                    response = Either.right(myHouseList);
                } else {
                    response = Either.left(r.message());
                }
            }

        } catch (IOException e) {
            response = Either.left(e.getMessage());
        }
        return response;
    }

    public Either<String, List<HarryPotterCharacter>> retrofitCallSlytherin() {

        Either<String, List<HarryPotterCharacter>> response = null;

        Response<List<ResponseCharItem>> r;

        try {
            r = harryPotterAPI.getSlytherin().execute();

            if (r.isSuccessful()) {

                List<ResponseCharItem> houseList = r.body();
                List<HarryPotterCharacter> myHouseList;
                if (houseList != null) {

                    myHouseList = houseList.stream()
                            .map(staffCharacter -> new HarryPotterCharacter(staffCharacter.getName(), staffCharacter.getSpecies(), staffCharacter.getHouse(), staffCharacter.getDateOfBirth(), staffCharacter.getEyeColour(), staffCharacter.getHairColour(), staffCharacter.getPatronus(), staffCharacter.getImage())).toList();

                    response = Either.right(myHouseList);
                } else {
                    response = Either.left(r.message());
                }
            }

        } catch (IOException e) {
            response = Either.left(e.getMessage());
        }
        return response;
    }

    public Either<String, List<HarryPotterCharacter>> retrofitCallHufflePuff() {

        Either<String, List<HarryPotterCharacter>> response = null;

        Response<List<ResponseCharItem>> r;

        try {
            r = harryPotterAPI.getHufflepuff().execute();

            if (r.isSuccessful()) {

                List<ResponseCharItem> houseList = r.body();
                List<HarryPotterCharacter> myHouseList;
                if (houseList != null) {

                    myHouseList = houseList.stream()
                            .map(staffCharacter -> new HarryPotterCharacter(staffCharacter.getName(), staffCharacter.getSpecies(), staffCharacter.getHouse(), staffCharacter.getDateOfBirth(), staffCharacter.getEyeColour(), staffCharacter.getHairColour(), staffCharacter.getPatronus(), staffCharacter.getImage())).toList();

                    response = Either.right(myHouseList);
                } else {
                    response = Either.left(r.message());
                }
            }

        } catch (IOException e) {
            response = Either.left(e.getMessage());
        }
        return response;
    }

    public Either<String, List<HarryPotterCharacter>> retrofitCallRavenclaw() {

        Either<String, List<HarryPotterCharacter>> response = null;

        Response<List<ResponseCharItem>> r;

        try {
            r = harryPotterAPI.getRavenclaw().execute();

            if (r.isSuccessful()) {

                List<ResponseCharItem> houseList = r.body();
                List<HarryPotterCharacter> myHouseList;
                if (houseList != null) {

                    myHouseList = houseList.stream()
                            .map(staffCharacter -> new HarryPotterCharacter(staffCharacter.getName(), staffCharacter.getSpecies(), staffCharacter.getHouse(), staffCharacter.getDateOfBirth(), staffCharacter.getEyeColour(), staffCharacter.getHairColour(), staffCharacter.getPatronus(), staffCharacter.getImage())).toList();

                    response = Either.right(myHouseList);
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
