package ui.screens.houses;

import domain.modelo.HarryPotterCharacter;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTableColumn;
import io.github.palexdev.materialfx.controls.MFXTableView;
import io.github.palexdev.materialfx.controls.cell.MFXTableRowCell;
import jakarta.inject.Inject;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import ui.screens.common.BaseScreenController;
import ui.screens.common.ScreenConstants;

import java.net.URL;
import java.util.ResourceBundle;

public class HousesController extends BaseScreenController implements Initializable {

    @FXML
    public MFXButton goBackBtn;
    @FXML
    public Label housesLabel;
    @FXML
    public MFXTableView<HarryPotterCharacter> tableViewGryff;
    @FXML
    public MFXTableView<HarryPotterCharacter> tableViewSlyth;
    @FXML
    public MFXTableView<HarryPotterCharacter> tableViewHuff;
    @FXML
    public MFXTableView<HarryPotterCharacter> tableViewRaven;
    @FXML
    public Pane logoPaneGryff;
    @FXML
    public Pane logoPaneSlyth;
    @FXML
    public Pane logoPaneHuff;
    @FXML
    public Pane logoPaneRaven;


    private HousesViewModel viewModel;

    @Inject
    public HousesController(HousesViewModel housesViewModel) {
        viewModel = housesViewModel;
    }

    public void initialize(URL url, ResourceBundle resourceBundle) {

        MFXTableColumn<HarryPotterCharacter> nameColumnGryff = new MFXTableColumn<>(ScreenConstants.NAME_FIELD);
        MFXTableColumn<HarryPotterCharacter> nameColumnSlyth = new MFXTableColumn<>(ScreenConstants.NAME_FIELD);
        MFXTableColumn<HarryPotterCharacter> nameColumnHuff = new MFXTableColumn<>(ScreenConstants.NAME_FIELD);
        MFXTableColumn<HarryPotterCharacter> nameColumnRaven = new MFXTableColumn<>(ScreenConstants.NAME_FIELD);

        nameColumnGryff.setRowCellFactory(harryPotterCharacter -> new MFXTableRowCell<>(HarryPotterCharacter::getName));
        nameColumnSlyth.setRowCellFactory(harryPotterCharacter -> new MFXTableRowCell<>(HarryPotterCharacter::getName));
        nameColumnHuff.setRowCellFactory(harryPotterCharacter -> new MFXTableRowCell<>(HarryPotterCharacter::getName));
        nameColumnRaven.setRowCellFactory(harryPotterCharacter -> new MFXTableRowCell<>(HarryPotterCharacter::getName));

        tableViewGryff.getTableColumns().addAll(nameColumnGryff);
        tableViewSlyth.getTableColumns().addAll(nameColumnSlyth);
        tableViewHuff.getTableColumns().addAll(nameColumnHuff);
        tableViewRaven.getTableColumns().addAll(nameColumnRaven);

        stateChanges();
        viewModel.loadHouses();

        logoPaneGryff.setStyle("-fx-background-image: url('" + ScreenConstants.GRYFFINDOR_CREST + "');");
        logoPaneSlyth.setStyle("-fx-background-image: url('" + ScreenConstants.SLYTHERIN_CREST + "');");
        logoPaneHuff.setStyle("-fx-background-image: url('" + ScreenConstants.HUFFLEPUFF_CREST + "');");
        logoPaneRaven.setStyle("-fx-background-image: url('" + ScreenConstants.RAVENCLAW_CREST + "');");

    }

    private void stateChanges() {

        viewModel.getState().addListener((observableValue, oldValue, newValue) -> {
            if (newValue.getError() != null) {
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setContentText(newValue.getError());
                a.showAndWait();
            }
            if (newValue.getHouseList() != null) {

                tableViewGryff.getItems().clear();
                tableViewGryff.getItems().addAll(newValue.getHouseList().get(0));
                tableViewSlyth.getItems().clear();
                tableViewSlyth.getItems().addAll(newValue.getHouseList().get(1));
                tableViewHuff.getItems().clear();
                tableViewHuff.getItems().addAll(newValue.getHouseList().get(2));
                tableViewRaven.getItems().clear();
                tableViewRaven.getItems().addAll(newValue.getHouseList().get(3));


            }
        });


    }

    @FXML
    public void goChoiceScreen() {
        getPrincipalController().doGoChoiceScreen();
    }
}
