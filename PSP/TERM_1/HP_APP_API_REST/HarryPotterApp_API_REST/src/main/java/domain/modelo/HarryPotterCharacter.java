package domain.modelo;

import lombok.Data;


@Data
public class HarryPotterCharacter {

    private String name;
    private String species;
    private String house;
    private String dateOfBirth;
    private String eyeColour;
    private String hairColour;
    private String patronus;
    private String image;

    public HarryPotterCharacter(String name, String species, String house, String dateOfBirth, String eyeColour, String hairColour, String patronus, String image) {
        this.name = name;
        this.species = species;
        this.house = house;
        this.dateOfBirth = dateOfBirth;
        this.eyeColour = eyeColour;
        this.hairColour = hairColour;
        this.patronus = patronus;
        this.image = image;
    }
}
