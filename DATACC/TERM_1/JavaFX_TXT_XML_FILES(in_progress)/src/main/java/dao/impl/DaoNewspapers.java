package dao.impl;

import common.config.Configuration;
import domain.modelo.Newspaper;
import jakarta.inject.Inject;
import lombok.extern.log4j.Log4j2;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import io.vavr.control.Either;

@Log4j2
public class DaoNewspapers {

    private Configuration configuration;
    //private List<Newspaper> newspapers;

    @Inject
    public DaoNewspapers(Configuration configuration) {
        this.configuration = configuration;
    }

/*
    //Esto hará que se carguen los periódicos al inicializar la clase
    public DaoNewspapers() {
        this.newspapers = this.loadNewspapers().getOrElse(new ArrayList<>());
    }

 */
/*
    public List<Newspaper> getNewspapers() {
        return this.newspapers;
    }

 */


    public Either<String, List<Newspaper>> loadNewspapers() {

        Either<String, List<Newspaper>> response;
        List<Newspaper> newspapersList = new ArrayList<>();
        List<String> newspapersString;
        try {
            newspapersString = Files.readAllLines(Paths.get(configuration.getPathNewspapers()));
            newspapersString.forEach(s -> {
                String[] fields = s.split(";");
                Newspaper newspaper = new Newspaper(fields[0], Integer.parseInt(fields[1]), LocalDate.parse(fields[2]));
                newspapersList.add(newspaper);

            });
            response = Either.right(newspapersList);


        } catch (IOException e) {
            response = Either.left(e.getMessage());
        }

        return response;

    }

/*
    public Either<String, List<Newspaper>> loadNewspapers() {

        Either<String, List<Newspaper>> response;
        List<Newspaper> newspapersList = new ArrayList<>();

        try {
            String filePath = new File("").getAbsolutePath();
            System.out.println(filePath.concat("\\" + configuration.getPathNewspapers()));
            BufferedReader reader = new BufferedReader(new FileReader(Paths.get(configuration.getPathNewspapers()).toString()));
            String line;
            while ((line = reader.readLine()) != null){
                String[] fields = line.split(";");
                Newspaper newspaper = new Newspaper(fields[0], Integer.parseInt(fields[1]), LocalDate.parse(fields[2]));
                newspapersList.add(newspaper);
            }
            response = Either.right(newspapersList);
        } catch (IOException e) {
            response = Either.left(e.getMessage());
        }

        System.out.println(response);

        return response;

    }
*/
    //pregunta: trabajo sobre la lista que cojo después o sobre la lista que ya tengo al inicializar?

    /*
    public int deleteNewspapers(int id) {

        int response;
        List<Newspaper> newspaperList = this.loadNewspapers().get();
        try {
            newspaperList.remove(index);
            Files.delete(Path.of(configuration.getPathNewspapers()));

            //new FileOutputStream(configuration.getPathNewspapers()).close();
        } catch (IOException e) {
            response = 0;
        }
        return response;

        try {
            Files.delete(Path.of(configuration.getPathNewspapers()));
            response = 1;
        } catch (IOException e) {
            response = 0;
        }
        return response;

        //Esto de arriba simplemente lo borra completamente, pero no lo vuelve a escribir sin lo que se quiere borrar
    }

     */


/*

    public Newspaper findNewspaper(int id) {

         //Nos devuelve un objecto newspaper con el id que pasamos como parámetro

        Newspaper auxNewspaper = null;
        for (int i = 0; i < this.newspapers.size(); i++) {
            if (this.newspapers.get(i).getNewspaperId() == id) {
                auxNewspaper = this.newspapers.get(i);
                break;
            }
        }
        return auxNewspaper;
    }

 */





    //Insert update delete devuelven un Integer, que luego en servicios se transforma en un Either

}
