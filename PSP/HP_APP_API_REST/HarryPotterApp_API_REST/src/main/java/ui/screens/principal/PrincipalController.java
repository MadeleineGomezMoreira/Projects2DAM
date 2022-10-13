package ui.screens.principal;


import jakarta.enterprise.inject.Instance;
import jakarta.inject.Inject;
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

    @FXML
    public MenuBar mainMenuBar;

    @FXML
    public Menu optionsMenu;

    @FXML
    public MenuItem menuItemStudents;

    @FXML
    public MenuItem menuItemStaff;

    @FXML
    public MenuItem menuItemSpells;

    @FXML
    public MenuItem menuItemHouses;

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

    private void loadScreen(Screens screen) {

        switch (screen) {
            default:
                changeScreen(loadScreen(screen.getRoute()));
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
        loadScreen(Screens.START);

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


    public void exit() {
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
        return root.getScene().getWindow().getWidth();
    }

    public void setSprites(String name, Pane pane) {
    /*
        switch (name) {
            case ScreenConstants.BULBASAUR:
                pane.setStyle("-fx-background-image: url('https://img.pokemondb.net/sprites/home/normal/bulbasaur.png');");
                break;
            case ScreenConstants.IVYSAUR:
                pane.setStyle("-fx-background-image: url('https://img.pokemondb.net/sprites/home/normal/ivysaur.png');");

                break;
            case ScreenConstants.VENUSAUR:
                pane.setStyle("-fx-background-image: url('https://img.pokemondb.net/sprites/home/normal/venusaur.png');");

                break;
            case ScreenConstants.CHARMANDER:
                pane.setStyle("-fx-background-image: url('https://img.pokemondb.net/sprites/home/normal/charmander.png');");

                break;
            case ScreenConstants.CHARMELEON:
                pane.setStyle("-fx-background-image: url('https://img.pokemondb.net/sprites/home/normal/charmeleon.png');");

                break;
            case ScreenConstants.CHARIZARD:
                pane.setStyle("-fx-background-image: url('https://img.pokemondb.net/sprites/home/normal/charizard.png');");

                break;
            case ScreenConstants.SQUIRTLE:
                pane.setStyle("-fx-background-image: url('https://img.pokemondb.net/sprites/home/normal/squirtle.png');");

                break;
            case ScreenConstants.WARTORTLE:
                pane.setStyle("-fx-background-image: url('https://img.pokemondb.net/sprites/home/normal/wartortle.png');");

                break;
            case ScreenConstants.BLASTOISE:
                pane.setStyle("-fx-background-image: url('https://img.pokemondb.net/sprites/home/normal/blastoise.png');");

                break;
        }

     */
    }

    public void doGoChoiceScreen() {
        loadScreen(Screens.CHOICE);
    }

    public void doGoBack() {
        loadScreen(Screens.START);
    }

    public void doGoAllStudents() {
        loadScreen(Screens.STUDENTS);
    }

    public void goStart() {
        loadScreen(Screens.START);
    }

    public void doGoSeeSpells() {
        loadScreen(Screens.SPELLS);
    }

    public void doGoSeeStaff() {
        loadScreen(Screens.STAFF);
    }

    public void doGoSeeHouses() {
        loadScreen(Screens.HOUSES);
    }

}

