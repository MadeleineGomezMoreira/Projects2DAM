package ui.screens.appStart;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import ui.screens.common.BaseScreenController;

import java.net.URL;
import java.util.ResourceBundle;

public class AppStartController extends BaseScreenController implements Initializable {

    @FXML
    private Button loginBtn;
    @FXML
    private Button registerBtn;
    @FXML
    private Label orLabel;
    @FXML
    private Label versionLabel;
    @FXML
    private Label pokemonPCLabel;


    public AppStartController() {
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }


    @Override
    public void loadedPrincipal() {
    }

    public void goViewList(javafx.event.ActionEvent actionEvent) {
        getPrincipalController().goNewspapersList();
    }

}
