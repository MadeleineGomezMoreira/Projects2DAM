package ui.screens.newspapersList;

import domain.modelo.Newspaper;
import domain.services.NewspaperServices;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.util.ArrayList;
import java.util.List;

public class NewspapersListViewModel {

    private NewspaperServices newspaperServices;


    @Inject
    public NewspapersListViewModel(NewspaperServices newspaperServices) {
        this.newspaperServices = newspaperServices;
        _state = new SimpleObjectProperty<>(new NewspapersListState(null, null));
    }



    public void loadNewspapers() {

        List<Newspaper> newspapers = newspaperServices.loadNewspapers().getOrElse(new ArrayList<>());


        NewspapersListState state = new NewspapersListState(newspapers, null);
        _state.setValue(state);

    }




    private ObjectProperty<NewspapersListState> _state;

    public ReadOnlyObjectProperty<NewspapersListState> getState() {
        System.out.println(1);
        return _state;
    }


}
