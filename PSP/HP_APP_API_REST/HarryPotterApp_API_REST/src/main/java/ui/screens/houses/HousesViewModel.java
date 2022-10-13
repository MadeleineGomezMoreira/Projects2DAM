package ui.screens.houses;

import domain.modelo.HarryPotterCharacter;
import domain.servicios.HouseServices;
import jakarta.inject.Inject;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.util.ArrayList;
import java.util.List;

public class HousesViewModel {

    private HouseServices houseServices;

    @Inject
    public HousesViewModel(HouseServices houseServices) {
        this.houseServices = houseServices;
        _state = new SimpleObjectProperty<>(new HousesState(null, null));
    }

    private ObjectProperty<HousesState> _state;

    public ReadOnlyObjectProperty<HousesState> getState() {
        return _state;
    }

    public void loadHouses(){

        List<List<HarryPotterCharacter>> houseLists = new ArrayList<>();

            List<HarryPotterCharacter> listGryffindor = houseServices.getGryffindor().get();
            List<HarryPotterCharacter> listSlytherin = houseServices.getSlytherin().get();
            List<HarryPotterCharacter> listHufflepuff = houseServices.getHufflepuff().get();
            List<HarryPotterCharacter> listRavenclaw = houseServices.getRavenclaw().get();

            houseLists.add(listGryffindor);
            houseLists.add(listSlytherin);
            houseLists.add(listHufflepuff);
            houseLists.add(listRavenclaw);

            HousesState state = new HousesState(houseLists, null);
            _state.setValue(state);

    }

}
