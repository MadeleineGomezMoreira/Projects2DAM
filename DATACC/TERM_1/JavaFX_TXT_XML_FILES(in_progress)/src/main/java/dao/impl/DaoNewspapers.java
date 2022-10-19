package dao.impl;

import common.config.Configuration;
import domain.modelo.Newspaper;
import jakarta.inject.Inject;
import lombok.extern.log4j.Log4j2;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import io.vavr.control.Either;

@Log4j2
public class DaoNewspapers {

    private Configuration configuration;
    private List<Newspaper> newspapers;

    @Inject
    public DaoNewspapers(Configuration configuration) {
        this.configuration = configuration;
    }


    //Esto hará que se carguen los periódicos al inicializar la clase
    public DaoNewspapers() {
        this.newspapers = this.loadNewspapers().getOrElse(new ArrayList<>());
    }



    public List<Newspaper> getNewspapers() {
        return this.newspapers;
    }




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

/*  //ANOTHER WAY TO LOAD NEWSPAPERS
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
/*
//LUEGO CAMBIAMOS ESTO TENGO SUEÑITO
    public int add(Newspaper newspaper) {
        int code = 0;
        if (!getNewspapers().stream().anyMatch(article1 -> Objects.equals(newspaper1.getName_article(), article1.getName_article()) && article1.getId_newspaper() == article1.getId_newspaper())) {
            String newLine = ServicesConstants.BREAK + article.getId_article() + DAOConstants.SEP + article.getName_article() + DAOConstants.SEP + article.getId_type() + DAOConstants.SEP + article.getId_newspaper();
            try {
                Files.write(Paths.get(configuration.getPathArticles()), newLine.getBytes(), StandardOpenOption.APPEND);
            } catch (IOException io) {
                code = -1;
            }
        }
        else {
            code = 1;
        }
        return code;
    }

 */

    //BUFFERED WRITER (mirar documentación oficial)

/*
    public int deleteNewspapers(int id) {

        int response;
        List<Newspaper> newspaperList = this.loadNewspapers().getOrElse(new ArrayList<>());
        FileWriter fileWritter = null;
        try {
            fileWritter = new FileWriter("newspapers.txt");
        } catch (IOException e) {
            throw new RuntimeException(e);
            response = -1;
        }
        BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
        bufferWritter.write(data);
        bufferWritter.close();
        try {
            newspaperList.remove(index);
            Files.delete(Path.of(configuration.getPathNewspapers()));

            //new FileOutputStream(configuration.getPathNewspapers()).close();
        } catch (IOException e) {
            response = -1;
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







    //Insert update delete devuelven un Integer, que luego en servicios se transforma en un Either

}
