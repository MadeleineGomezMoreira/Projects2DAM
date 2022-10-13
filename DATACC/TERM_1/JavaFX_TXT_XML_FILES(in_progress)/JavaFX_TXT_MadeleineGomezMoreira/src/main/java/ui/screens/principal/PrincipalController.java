package ui.screens.principal;


import jakarta.enterprise.inject.Instance;
import jakarta.inject.Inject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import lombok.extern.log4j.Log4j2;
import ui.screens.common.BaseScreenController;
import ui.screens.common.ScreenConstants;
import ui.screens.common.Screens;

import java.io.IOException;
import java.util.Optional;

@Log4j2
public class PrincipalController {

    Instance<Object> instance;

    private Stage primaryStage;
    @FXML
    private BorderPane root;

    private Alert alert;


    @Inject
    public PrincipalController(Instance<Object> instance) {
        this.instance = instance;
        alert = new Alert(Alert.AlertType.NONE);

    }

    private void loadScreen(Screens pantalla) {

        switch (pantalla) {
            default:
                changeScreen(loadScreen(pantalla.getRoute()));
                break;
        }
    }

    private Pane loadScreen(String route) {
        Pane screenPane = null;
        try {

            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setControllerFactory(controller -> instance.select(controller).get());
            screenPane = fxmlLoader.load(getClass().getResourceAsStream(route));
            BaseScreenController pantallaController = fxmlLoader.getController();
            pantallaController.setPrincipalController(this);
            pantallaController.loadedPrincipal();


        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        return screenPane;
    }

    private void changeScreen(Pane newScreen) {

        root.setCenter(newScreen);
    }


    public void initialize() {
        loadScreen(Screens.APPSTARTSCREEN);

    }

    private void closeWindowEvent(WindowEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.getButtonTypes().remove(ButtonType.OK);
        alert.getButtonTypes().add(ButtonType.CANCEL);
        alert.getButtonTypes().add(ButtonType.YES);
        alert.setTitle(ScreenConstants.QUIT_APPLICATION);
        alert.setContentText(ScreenConstants.CLOSE_THE_APPLICATION);
        alert.initOwner(primaryStage.getOwner());
        Optional<ButtonType> res = alert.showAndWait();


        res.ifPresent(buttonType -> {
            if (buttonType == ButtonType.CANCEL) {
                event.consume();
            }
        });
    }

    public void exit(ActionEvent actionEvent) {
//        primaryStage.close();
//        Platform.exit();
        primaryStage.fireEvent(new WindowEvent(primaryStage, WindowEvent.WINDOW_CLOSE_REQUEST));
    }



    public void setStage(Stage stage) {
        primaryStage = stage;
        primaryStage.addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, this::closeWindowEvent);
    }

    public double getHeight() {
        return root.getScene().getWindow().getHeight();
    }

    public double getWidth() {
//        return 600;
        return root.getScene().getWindow().getWidth();
    }

    public void goAppStart() {
        loadScreen(Screens.APPSTARTSCREEN);
    }

    public void goNewspapersList() {
        loadScreen(Screens.NEWSPAPERSLISTSCREEN);
    }


}
