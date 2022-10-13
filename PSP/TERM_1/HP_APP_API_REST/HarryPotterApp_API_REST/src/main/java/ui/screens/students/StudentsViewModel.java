package ui.screens.students;

import domain.modelo.HarryPotterCharacter;
import domain.servicios.StudentServices;
import jakarta.inject.Inject;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.util.List;

public class StudentsViewModel {

    private StudentServices studentServices;

    @Inject
    public StudentsViewModel(StudentServices studentServices) {
        this.studentServices = studentServices;
        _state = new SimpleObjectProperty<>(new StudentsState(null, null));

    }


    private ObjectProperty<StudentsState> _state;

    public ReadOnlyObjectProperty<StudentsState> getState() {
        return _state;
    }

    public void loadStudents() {

        List<HarryPotterCharacter> listStudents = studentServices.getCharacters().get();
        StudentsState state = new StudentsState(listStudents, null);
        _state.setValue(state);


    }


}
