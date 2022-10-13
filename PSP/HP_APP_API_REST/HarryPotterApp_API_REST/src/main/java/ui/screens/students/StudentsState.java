package ui.screens.students;

import domain.modelo.HarryPotterCharacter;
import lombok.Data;

import java.util.List;

@Data
public class StudentsState {

    private final List<HarryPotterCharacter> students;

    private final String error;

}
