package ui.screens.houses;

import domain.modelo.HarryPotterCharacter;
import lombok.Data;

import java.util.List;

@Data
public class HousesState {

    private final List<List<HarryPotterCharacter>> houseList;

    private final String error;
}
