package dao.modelo;

import lombok.Data;

import java.util.List;

@Data
public class ResponseCharItem {
    private String patronus;
    private boolean hogwartsStudent;
    private String image;
    private String ancestry;
    private String gender;
    private boolean alive;
    private String hairColour;
    private String dateOfBirth;
    private String house;
    private boolean hogwartsStaff;
    private List<Object> alternateNames;
    private String actor;
    private List<Object> alternateActors;
    private String species;
    private Wand wand;
    private String name;
    private boolean wizard;
    private String eyeColour;
    private String yearOfBirth;

    public String getPatronus() {
        return patronus;
    }

    public boolean isHogwartsStudent() {
        return hogwartsStudent;
    }

    public String getImage() {
        return image;
    }

    public String getAncestry() {
        return ancestry;
    }

    public String getGender() {
        return gender;
    }

    public boolean isAlive() {
        return alive;
    }

    public String getHairColour() {
        return hairColour;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getHouse() {
        return house;
    }

    public boolean isHogwartsStaff() {
        return hogwartsStaff;
    }

    public List<Object> getAlternateNames() {
        return alternateNames;
    }

    public String getActor() {
        return actor;
    }

    public List<Object> getAlternateActors() {
        return alternateActors;
    }

    public String getSpecies() {
        return species;
    }

    public Wand getWand() {
        return wand;
    }

    public String getName() {
        return name;
    }

    public boolean isWizard() {
        return wizard;
    }

    public String getEyeColour() {
        return eyeColour;
    }

    public String getYearOfBirth() {
        return yearOfBirth;
    }
}