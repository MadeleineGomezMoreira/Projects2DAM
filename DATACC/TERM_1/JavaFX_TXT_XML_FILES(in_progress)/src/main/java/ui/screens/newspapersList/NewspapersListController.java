package ui.screens.newspapersList;

import domain.modelo.Newspaper;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTableColumn;
import io.github.palexdev.materialfx.controls.MFXTableView;
import io.github.palexdev.materialfx.controls.cell.MFXTableRowCell;
import jakarta.inject.Inject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import ui.screens.common.BaseScreenController;
import ui.screens.common.ScreenConstants;

import java.net.URL;
import java.util.ResourceBundle;

public class NewspapersListController extends BaseScreenController implements Initializable {

    //aquí solo se ve la lista uwu

    @FXML
    public Label detailsLabel;

    @FXML
    public Label nameLabel;

    @FXML
    public Label releaseDateLabel;

    @FXML
    public Label nameFillLabel;

    @FXML
    public Label releaseDateFillLabel;

    @FXML
    public MFXButton goBackBtn;

    @FXML
    public Label newspapersLabel;

    @FXML
    public MFXButton buttonOne;

    @FXML
    public MFXButton buttonTwo;

    @FXML
    public MFXTableView tableViewNewspapersList;

    private NewspapersListViewModel viewModel;

    @Inject
    public NewspapersListController(NewspapersListViewModel newspapersListViewModel) {
        viewModel = newspapersListViewModel;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        MFXTableColumn<Newspaper> nameColumn = new MFXTableColumn<>("Name");
        MFXTableColumn<Newspaper> idColumn = new MFXTableColumn<>("ID");
        MFXTableColumn<Newspaper> releaseDateColumn = new MFXTableColumn<>("Release Date");

        nameColumn.setRowCellFactory(harryPotterCharacter -> new MFXTableRowCell<>(Newspaper::getName));
        idColumn.setRowCellFactory(harryPotterCharacter -> new MFXTableRowCell<>(Newspaper::getNewspaperId));
        releaseDateColumn.setRowCellFactory(harryPotterCharacter -> new MFXTableRowCell<>(Newspaper::getReleaseDate));


        tableViewNewspapersList.getTableColumns().addAll(nameColumn, idColumn, releaseDateColumn);


        stateChanges();
        viewModel.loadNewspapers();

    }

    private void stateChanges() {
        System.out.println(1);
        viewModel.getState().addListener((observableValue, oldValue, newValue) -> {
            //System.out.println(oldValue.toString()+newValue.toString());
            if(newValue.getError() != null){
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setContentText(newValue.getError());
                a.showAndWait();
            }
            if(newValue.getNewspapers() != null){
                System.out.println(newValue.getNewspapers().toString());
                tableViewNewspapersList.getItems().clear();
                tableViewNewspapersList.getItems().addAll(newValue.getNewspapers());
            }
        });


    }

    @Override
    public void loadedPrincipal() {

    }

    @FXML
    public void goAppStart(ActionEvent actionEvent) {
        getPrincipalController().goAppStart();
    }


}

