package ui.screens.start;

import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import ui.screens.common.BaseScreenController;

import java.net.URL;
import java.util.ResourceBundle;

public class StartController extends BaseScreenController implements Initializable {


    @FXML
    public Label quoteLabel;

    @FXML
    public Label titleLabelOne;

    @FXML
    public Label versionLabel;

    @FXML
    public Label titleLabelTwo;

    @FXML
    public MFXButton startBtn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }


    @FXML
    public void goChoiceScreen(javafx.event.ActionEvent actionEvent) {
        getPrincipalController().doGoChoiceScreen();
    }

}
