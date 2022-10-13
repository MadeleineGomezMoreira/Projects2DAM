package ui.screens.staff;

import domain.modelo.HarryPotterCharacter;
import lombok.Data;

import java.util.List;

@Data
public class StaffState {

    private final List<HarryPotterCharacter> staff;

    private final String error;
}
