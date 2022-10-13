package dao.retrofit;

import com.squareup.moshi.Moshi;
import common.config.Configuration;
import dao.retrofit.llamadas.HarryPotterAPI;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Singleton;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;


public class ProducesRetrofit {


    @Produces
    @Singleton
    public Moshi getMoshi() {
        return new Moshi.Builder().build();
    }


    //@Named("cantantes") lo llamaría distinto para distintos APIs
    @Produces
    @Singleton
    public Retrofit retrofit(Moshi moshi, Configuration config) {

        OkHttpClient clientOK = new OkHttpClient.Builder()
                .connectionPool(new okhttp3.ConnectionPool(1, 1, java.util.concurrent.TimeUnit.SECONDS))
                //.protocols(java.util.Arrays.asList(Protocol.HTTP_2,Protocol.H2_PRIOR_KNOWLEDGE))
                .build();

        return new Retrofit.Builder()
                .baseUrl(config.getPathUrl())
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                //.addConverterFactory(GsonConverterFactory.create(gson))
                .client(clientOK)
                .build();
    }

    @Produces
    public HarryPotterAPI getHarryPotterAPI(Retrofit retrofit) {
        return retrofit.create(HarryPotterAPI.class);
    }


}
