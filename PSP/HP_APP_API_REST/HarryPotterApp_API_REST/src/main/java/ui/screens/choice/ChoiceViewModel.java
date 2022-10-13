package ui.screens.choice;

import jakarta.inject.Inject;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;

public class ChoiceViewModel {


    private ObjectProperty<ChoiceState> _state;

    public ReadOnlyObjectProperty<ChoiceState> getState() {
        return _state;
    }

    @Inject
    public ChoiceViewModel() {

    }



}
