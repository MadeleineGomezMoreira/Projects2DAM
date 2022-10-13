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

public class DaoStudents {

    private Configuration configuration;

    private HarryPotterAPI harryPotterAPI;

    @Inject
    public DaoStudents(Configuration configuration, HarryPotterAPI harryPotterAPI) {
        this.configuration = configuration;
        this.harryPotterAPI = harryPotterAPI;
    }

    public Single<Either<String, List<HarryPotterCharacter>>> retrofitCallAllStudentsSingleAsync(){
        return harryPotterAPI.getAllStudentsSingleAsync()
                .map(r -> {
                    List<HarryPotterCharacter> myStudentsList = r.stream().map(studentCharacter -> new HarryPotterCharacter(studentCharacter.getName(), studentCharacter.getSpecies(), studentCharacter.getHouse(), studentCharacter.getDateOfBirth(), studentCharacter.getEyeColour(),
                            studentCharacter.getHairColour(), studentCharacter.getPatronus(), studentCharacter.getImage())).toList();
                    return Either.right(myStudentsList).mapLeft(Object::toString);
                })
                .subscribeOn(Schedulers.io())
                .onErrorReturn(e -> Either.left("There was an error in the request" + e.getMessage()));
    }

    public Either<String, List<HarryPotterCharacter>> retrofitCallAllStudents() {

        Either<String, List<HarryPotterCharacter>> response = null;

        Response<List<ResponseCharItem>> r;

        try {
            r = harryPotterAPI.getAllStudents().execute();

            if (r.isSuccessful()) {

                List<ResponseCharItem> studentsList = r.body();
                List<HarryPotterCharacter> myStudentsList;
                if (studentsList != null) {

                    myStudentsList = studentsList.stream()
                            .map(studentCharacter -> new HarryPotterCharacter(studentCharacter.getName(), studentCharacter.getSpecies(), studentCharacter.getHouse(), studentCharacter.getDateOfBirth(), studentCharacter.getEyeColour(), studentCharacter.getHairColour(), studentCharacter.getPatronus(), studentCharacter.getImage())).toList();

                    response = Either.right(myStudentsList);
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
