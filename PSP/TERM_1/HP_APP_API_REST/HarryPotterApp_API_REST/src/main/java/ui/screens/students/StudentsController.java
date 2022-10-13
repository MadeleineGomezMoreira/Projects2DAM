package ui.screens.students;

import domain.modelo.HarryPotterCharacter;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTableColumn;
import io.github.palexdev.materialfx.controls.MFXTableView;
import io.github.palexdev.materialfx.controls.cell.MFXTableRowCell;
import jakarta.inject.Inject;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import ui.screens.common.BaseScreenController;
import ui.screens.common.ScreenConstants;

import java.net.URL;
import java.util.ResourceBundle;

public class StudentsController extends BaseScreenController implements Initializable {

    @FXML
    public MFXTableView<HarryPotterCharacter> tableViewStudents;

    @FXML
    public MFXButton goBackBtn;


    private StudentsViewModel viewModel;

    @Inject
    public StudentsController(StudentsViewModel studentsViewModel) {
        viewModel = studentsViewModel;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        MFXTableColumn<HarryPotterCharacter> nameColumn = new MFXTableColumn<>(ScreenConstants.NAME_FIELD);
        MFXTableColumn<HarryPotterCharacter> houseColumn = new MFXTableColumn<>(ScreenConstants.HOUSE_FIELD);
        MFXTableColumn<HarryPotterCharacter> birthColumn = new MFXTableColumn<>(ScreenConstants.BIRTH_FIELD);
        MFXTableColumn<HarryPotterCharacter> patronusColumn = new MFXTableColumn<>(ScreenConstants.PATRONUS_FIELD);
        MFXTableColumn<HarryPotterCharacter> speciesColumn = new MFXTableColumn<>(ScreenConstants.SPECIES_FIELD);

        nameColumn.setRowCellFactory(harryPotterCharacter -> new MFXTableRowCell<>(HarryPotterCharacter::getName));
        houseColumn.setRowCellFactory(harryPotterCharacter -> new MFXTableRowCell<>(HarryPotterCharacter::getHouse));
        birthColumn.setRowCellFactory(harryPotterCharacter -> new MFXTableRowCell<>(HarryPotterCharacter::getDateOfBirth));
        patronusColumn.setRowCellFactory(harryPotterCharacter -> new MFXTableRowCell<>(HarryPotterCharacter::getPatronus));
        speciesColumn.setRowCellFactory(harryPotterCharacter -> new MFXTableRowCell<>(HarryPotterCharacter::getSpecies));


        tableViewStudents.getTableColumns().addAll(nameColumn, houseColumn, birthColumn, patronusColumn, speciesColumn);

        stateChanges();
        viewModel.loadStudents();

    }

    private void stateChanges() {

        viewModel.getState().addListener((observableValue, oldValue, newValue) -> {
            if(newValue.getError() != null){
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setContentText(newValue.getError());
                a.showAndWait();
            }
            if(newValue.getStudents() != null){

                tableViewStudents.getItems().clear();
                tableViewStudents.getItems().addAll(newValue.getStudents().stream().toList());
            }
        });

         
    }

    @FXML
    public void goChoiceScreen() {
        getPrincipalController().doGoChoiceScreen();
    }


}

