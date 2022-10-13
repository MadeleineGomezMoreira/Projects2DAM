package common.config;


import jakarta.inject.Named;
import jakarta.inject.Singleton;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.util.Properties;

@Getter
@Log4j2
@Singleton
public class Configuration {

    private String pathUrl;

    public Configuration() {

        try {
            Properties p = new Properties();
            p.load(getClass().getClassLoader().getResourceAsStream("config/config.properties"));
            this.pathUrl = p.getProperty("pathUrl");

        } catch (IOException e) {
            log.error(e.getMessage(),e);
        }
    }


}
