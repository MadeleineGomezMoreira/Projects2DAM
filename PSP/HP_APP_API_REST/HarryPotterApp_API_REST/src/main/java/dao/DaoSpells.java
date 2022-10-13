package dao;

import common.config.Configuration;
import dao.modelo.ResponseSpelltem;
import dao.retrofit.llamadas.HarryPotterAPI;
import domain.modelo.HarryPotterCharacter;
import domain.modelo.Spell;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import retrofit2.Response;

import java.io.IOException;
import java.util.List;

public class DaoSpells {
    private Configuration configuration;

    private HarryPotterAPI harryPotterAPI;

    @Inject
    public DaoSpells(Configuration configuration, HarryPotterAPI harryPotterAPI) {
        this.configuration = configuration;
        this.harryPotterAPI = harryPotterAPI;
    }

    public Single<Either<String, List<Spell>>> retrofitCallAllSpellsSingleAsync(){
        return harryPotterAPI.getAllSpellsSingleAsync()
                .map(r -> {
                    List<Spell> mySpellsList = r.stream().map(spell -> new Spell(spell.getName(), spell.getDescription())).toList();
                    return Either.right(mySpellsList).mapLeft(Object::toString);
                })
                .subscribeOn(Schedulers.io())
                .onErrorReturn(e -> Either.left("There was an error in the request" + e.getMessage()));
    }

    public Either<String, List<Spell>> retrofitCallAllSpells() {

        Either<String,List<Spell>> response = null;

        Response<List<ResponseSpelltem>> r;

        try {
            r = harryPotterAPI.getAllSpells().execute();

            if (r.isSuccessful()) {

                List<ResponseSpelltem> spellsList = r.body();
                List<Spell> mySpellsList;

                if (spellsList != null) {

                    mySpellsList = spellsList.stream()
                            .map(spell -> new Spell(spell.getName(), spell.getDescription())).toList();

                    response = Either.right(mySpellsList);
                } else {
                    response = Either.left(r.message());
                }

            }

        } catch (IOException e) {
            response= Either.left(e.getMessage());
        }
        return response;
    }
}
