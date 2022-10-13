package ui.screens.spells;

import domain.modelo.Spell;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTableColumn;
import io.github.palexdev.materialfx.controls.MFXTableView;
import io.github.palexdev.materialfx.controls.cell.MFXTableRowCell;
import jakarta.inject.Inject;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import ui.screens.common.BaseScreenController;

import java.net.URL;
import java.util.ResourceBundle;

public class SpellsController extends BaseScreenController implements Initializable {


    @FXML
    public MFXButton goBackBtn;

    @FXML
    public Label spellsLabel;

    @FXML
    public MFXTableView<Spell> tableViewSpells;

    private SpellsViewModel viewModel;

    @Inject
    public SpellsController(SpellsViewModel spellsViewModel) {
        viewModel = spellsViewModel;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        MFXTableColumn<Spell> nameColumn = new MFXTableColumn<>("Name");

        nameColumn.setRowCellFactory(harryPotterSpell -> new MFXTableRowCell<>(Spell::getName));

        tableViewSpells.getTableColumns().addAll(nameColumn);

        stateChanges();
        viewModel.loadSpells();

    }

    private void stateChanges() {

        viewModel.getState().addListener((observableValue, oldValue, newValue) -> {
            if (newValue.getError() != null) {
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setContentText(newValue.getError());
                a.showAndWait();
            }
            if (newValue.getSpells() != null) {

                tableViewSpells.getItems().clear();
                tableViewSpells.getItems().addAll(newValue.getSpells().stream().toList());
            }
        });


    }

    @FXML
    public void goChoiceScreen() {
        getPrincipalController().doGoChoiceScreen();
    }


}
