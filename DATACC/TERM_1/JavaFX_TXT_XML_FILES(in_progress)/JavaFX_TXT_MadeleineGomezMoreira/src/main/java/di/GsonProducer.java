package di;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jakarta.enterprise.inject.Produces;

public class GsonProducer {

    @Produces
    public Gson gson() {

        return new GsonBuilder().create();
    }
}
