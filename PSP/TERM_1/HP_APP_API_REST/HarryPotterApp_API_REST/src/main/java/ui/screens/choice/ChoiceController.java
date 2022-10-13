package ui.screens.choice;

import io.github.palexdev.materialfx.controls.MFXButton;
import jakarta.inject.Inject;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import ui.screens.common.BaseScreenController;

import java.net.URL;
import java.util.ResourceBundle;

public class ChoiceController extends BaseScreenController implements Initializable {

    @FXML
    public Label greetLabelOne;
    @FXML
    public MFXButton goListBtn;
    @FXML
    public MFXButton goBackBtn;
    @FXML
    public MFXButton goStaffBtn;
    @FXML
    public MFXButton goHousesBtn;
    @FXML
    public MFXButton goSpellsBtn;

    private ChoiceViewModel viewModel;


    @Inject
    public ChoiceController(ChoiceViewModel choiceViewModel) {
        viewModel = choiceViewModel;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    public void goBack() {
        getPrincipalController().doGoBack();
    }
    @FXML
    public void goAllStudents() {
        getPrincipalController().doGoAllStudents();
    }
    @FXML
    public void goStaff() {
        getPrincipalController().doGoSeeStaff();
    }
    @FXML
    public void goHouses() {
        getPrincipalController().doGoSeeHouses();
    }
    @FXML
    public void goSpells() {
        getPrincipalController().doGoSeeSpells();
    }
}
