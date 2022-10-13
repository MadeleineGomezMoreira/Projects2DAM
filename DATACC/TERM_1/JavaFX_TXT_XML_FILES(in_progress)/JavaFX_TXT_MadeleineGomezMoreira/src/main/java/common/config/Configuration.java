package common.config;


import jakarta.inject.Singleton;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.util.Properties;

@Getter
@Log4j2
@Singleton
public class Configuration {


    private String pathNewspapers;
    private String pathArticles;

    public Configuration() {

        try {
            Properties p = new Properties();
            p.load(getClass().getClassLoader().getResourceAsStream("config/config.properties"));
            this.pathNewspapers = p.getProperty("pathNewspapers");
            this.pathArticles = p.getProperty("pathArticles");
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }

    }

}
