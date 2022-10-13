package dao.retrofit.llamadas;


import dao.modelo.ResponseCharItem;

import dao.modelo.ResponseSpelltem;
import io.reactivex.rxjava3.core.Single;
import retrofit2.Call;
import retrofit2.http.GET;

import java.util.List;

public interface HarryPotterAPI {

    //subscribeOn es donde se ejecuta la llamada y observeOn es donde se ejecuta el subscribe (post llamada)

    //en vez de que me devuelva un call, que me devuelva un single eg: single List<ResponseCharItem>

    @GET("characters/students")
    Call <List<ResponseCharItem>> getAllStudents();

    @GET("characters/students")
    Single <List<ResponseCharItem>> getAllStudentsSingleAsync();

    @GET("characters/staff")
    Call <List<ResponseCharItem>> getAllStaff();

    @GET("characters/staff")
    Single <List<ResponseCharItem>> getAllStaffSingleAsync();

    @GET("spells")
    Call<List<ResponseSpelltem>> getAllSpells();

    @GET("spells")
    Single <List<ResponseSpelltem>> getAllSpellsSingleAsync();

    @GET("characters/house/gryffindor")
    Call <List<ResponseCharItem>> getGryffindor();

    @GET("characters/house/gryffindor")
    Single <List<ResponseCharItem>> getGryffindorSingleAsync();

    @GET("characters/house/slytherin")
    Call <List<ResponseCharItem>> getSlytherin();

    @GET("characters/house/slytherin")
    Single <List<ResponseCharItem>> getSlytherinSingleAsync();

    @GET("characters/house/hufflepuff")
    Call <List<ResponseCharItem>> getHufflepuff();

    @GET("characters/house/hufflepuff")
    Single <List<ResponseCharItem>> getHufflepuffSingleAsync();

    @GET("characters/house/ravenclaw")
    Call <List<ResponseCharItem>> getRavenclaw();

    @GET("characters/house/ravenclaw")
    Single <List<ResponseCharItem>> getRavenclawSingleAsync();
}
