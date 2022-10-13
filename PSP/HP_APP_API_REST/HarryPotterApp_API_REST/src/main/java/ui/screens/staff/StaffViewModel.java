package ui.screens.staff;

import domain.modelo.HarryPotterCharacter;
import domain.servicios.StaffServices;
import jakarta.inject.Inject;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.util.List;

public class StaffViewModel {


    private StaffServices staffServices;

    @Inject
    public StaffViewModel(StaffServices staffServices) {
        this.staffServices = staffServices;
        _state = new SimpleObjectProperty<>(new StaffState(null, null));

    }

    private ObjectProperty<StaffState> _state;

    public ReadOnlyObjectProperty<StaffState> getState() {
        return _state;
    }

    public void loadStaff() {

        List<HarryPotterCharacter> listStaff = staffServices.getStaff().get();
        StaffState state = new StaffState(listStaff, null);
        _state.setValue(state);

    }


}
